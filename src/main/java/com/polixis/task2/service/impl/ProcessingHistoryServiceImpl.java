package com.polixis.task2.service.impl;

import com.polixis.task2.entity.ProcessingHistory;
import com.polixis.task2.exception.Error;
import com.polixis.task2.exception.FileProcessingException;
import com.polixis.task2.repository.ProcessingHistoryRepository;
import com.polixis.task2.service.ProcessingHistoryService;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author Hovhannes Gevorgyan on 12.10.2022
 */
@Service
@RequiredArgsConstructor
public class ProcessingHistoryServiceImpl implements ProcessingHistoryService {

  private final ProcessingHistoryRepository processingHistoryRepository;

  @Value("${addresses.directory}")
  private String addressesDirectory;

  @Override
  public void saveSucceededHistory(String fileName) {
    save(fileName, null, null);
  }

  @Override
  public void saveFailedHistory(String fileName, String reason) {
    save(fileName, LocalDateTime.now(), reason);
  }

  public List<Path> getUnprocessedFiles() {
    File directory = Paths.get(addressesDirectory).toFile();

    if (!directory.exists() || directory.listFiles() == null) {
      throw new FileProcessingException(Error.DIRECTORY_NOT_FOUND);
    }

    return Arrays.stream(Objects.requireNonNull(directory.listFiles()))
        .filter(file -> !processingHistoryRepository.existsByFileName(file.getName()))
        .map(file -> Paths.get(file.getAbsolutePath()))
        .toList();
  }

  private void save(String fileName, LocalDateTime failedAt, String failedReason) {
    processingHistoryRepository.save(
        ProcessingHistory.builder()
            .fileName(fileName)
            .failedAt(failedAt)
            .failedReason(failedReason)
            .build());
  }
}
