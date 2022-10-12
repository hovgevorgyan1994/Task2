package com.polixis.task2.parameters;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author Hovhannes Gevorgyan on 12.10.2022
 */
public class AddressFilesTestParameters {

  private static final String DIRECTORY = "src/main/resources/addresses/";

  public List<Path> getValidUnprocessedFiles() {
    return List.of(Paths.get(DIRECTORY + "file.txt"), Paths.get(DIRECTORY + "file1.txt"));
  }

  public List<Path> getUnprocessedFilesWithInvalidFile() {
    return List.of(Paths.get(DIRECTORY + "file.txt"),Paths.get(DIRECTORY + "not_found.txt"));
  }
}
