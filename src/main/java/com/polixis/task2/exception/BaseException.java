package com.polixis.task2.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Hovhannes Gevorgyan on 12.10.2022
 */
@Getter
@Setter
public class BaseException extends RuntimeException {

  protected final Error error;

  public BaseException(Error error) {
    this.error = error;
  }
}
