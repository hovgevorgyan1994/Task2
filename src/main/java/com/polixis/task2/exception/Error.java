package com.polixis.task2.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * @author Hovhannes Gevorgyan on 12.10.2022
 */
@Getter
@RequiredArgsConstructor
public enum Error {
  DIRECTORY_NOT_FOUND(4041, HttpStatus.NOT_FOUND, "Directory not found");

  private final Integer code;
  private final HttpStatus httpStatus;
  private final String message;
}
