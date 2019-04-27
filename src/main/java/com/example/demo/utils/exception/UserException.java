package com.example.demo.utils.exception;

/**
 * @author zhuhaitao
 * @date 2019/4/27 15:59
 */
public class UserException extends RuntimeException {

  private static final long serialVersionUID = -1204554764428942136L;

  public UserException(Throwable e) {
    super(e);
  }

  public UserException(String e) {
    super(e);
  }

  public UserException(String stringFormat, Object... param) {
    super(String.format(stringFormat, param));
  }
}
