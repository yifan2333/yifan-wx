package com.yifan.wx.expection;

/**
 * @author JE哥
 */
public class ServiceException extends RuntimeException {
  private static final long serialVersionUID = 1597879702806978221L;

  public ServiceException(String msg) {
    super(msg);
  }
}
