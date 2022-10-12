package com.polixis.task2.exception;

/**
 * @author Hovhannes Gevorgyan on 12.10.2022
 */
public class FileProcessingException extends BaseException {

  public FileProcessingException(Error error) {
    super(error);
  }
}
