package com.polixis.task2.service;

import java.nio.file.Path;
import java.util.List;

/**
 * @author Hovhannes Gevorgyan on 12.10.2022
 */
public interface ProcessingHistoryService {

  void saveSucceededHistory(String fileName);

  void saveFailedHistory(String fileName, String reason);

  List<Path> getUnprocessedFiles();
}
