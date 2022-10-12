package com.polixis.task2.service.impl;

import com.polixis.task2.dto.ProcessingResponse;
import com.polixis.task2.service.AddressFilesService;
import com.polixis.task2.service.ProcessingHistoryService;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Hovhannes Gevorgyan on 12.10.2022
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(isolation = Isolation.REPEATABLE_READ, noRollbackFor = Exception.class)
public class AddressFilesServiceImpl implements AddressFilesService {

  private final ProcessingHistoryService processingHistoryService;

  @Override
  public ProcessingResponse countUniqueAddresses() {
    List<Path> unprocessedFiles = processingHistoryService.getUnprocessedFiles();
    AtomicInteger uniqueAddressCount = new AtomicInteger(0);

    int unprocessedFilesCount = unprocessedFiles.size();

    if (unprocessedFilesCount > 0) {
      ExecutorService executorService = Executors.newFixedThreadPool(unprocessedFilesCount);
      unprocessedFiles.forEach(
          path -> {
            Callable<Integer> callable =
                () -> {
                  String fileName = path.toFile().getName();
                  int uniqueAddressCountPerFile = 0;
                  try (Stream<String> lines = Files.lines(path)) {
                    uniqueAddressCountPerFile =
                        lines.filter(line -> !line.isEmpty()).distinct().toList().size();
                    processingHistoryService.saveSucceededHistory(fileName);
                    log.info("{} file from resources was successfully processed", fileName);
                  } catch (Exception ex) {
                    processingHistoryService.saveFailedHistory(fileName, ex.getMessage());
                    log.info("{} file from resources was failed to process", fileName);
                  }
                  return uniqueAddressCountPerFile;
                };

            try {
              uniqueAddressCount.addAndGet(executorService.submit(callable).get());
            } catch (InterruptedException | ExecutionException e) {
              Thread.currentThread().interrupt();
            }
          });
    }
    return ProcessingResponse.builder()
        .filesCount(unprocessedFilesCount)
        .uniqueIPv4Count(uniqueAddressCount.get())
        .processedAt(LocalDateTime.now())
        .build();
  }
}
