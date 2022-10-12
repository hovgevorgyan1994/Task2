package com.polixis.task2.service;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.polixis.task2.parameters.AddressFilesTestParameters;
import com.polixis.task2.service.impl.AddressFilesServiceImpl;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author Hovhannes Gevorgyan on 12.10.2022
 */

@ExtendWith(MockitoExtension.class)
class AddressFilesServiceImplTest {

  @Mock private ProcessingHistoryService processingHistoryService;
  @InjectMocks private AddressFilesServiceImpl addressFilesService;
  private final AddressFilesTestParameters testParameters = new AddressFilesTestParameters();

  @Test
  void successfullyCountUniqueAddressesWithValidFiles() {
    List<Path> validFiles = testParameters.getValidUnprocessedFiles();

    doReturn(validFiles).when(processingHistoryService).getUnprocessedFiles();

    addressFilesService.countUniqueAddresses();

    verify(processingHistoryService, times(validFiles.size())).saveSucceededHistory(anyString());
  }

  @Test
  void successfullyCountUniqueAddressesWithInValidFiles() {
    doReturn(testParameters.getUnprocessedFilesWithInvalidFile())
        .when(processingHistoryService)
        .getUnprocessedFiles();

    addressFilesService.countUniqueAddresses();

    verify(processingHistoryService).saveSucceededHistory(anyString());
    verify(processingHistoryService).saveFailedHistory(anyString(), anyString());
  }
}
