package com.yifan.wx.expection;

import com.yifan.wx.util.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Set;

/**
 * 统一异常处理
 * @author JE哥
 * @author yifan
 */
@ControllerAdvice
@ResponseBody
public class CommonExceptionAdvice {

  private static Logger logger = LoggerFactory.getLogger(CommonExceptionAdvice.class);

  /**
   * 400 - Bad Request
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MissingServletRequestParameterException.class)
  public ModelMap handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
    logger.error("缺少请求参数", e);
    return ResultUtils.makeModel(HttpStatus.BAD_REQUEST.value(), "required_parameter_is_not_present");
  }

  /**
   * 400 - Bad Request
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ModelMap handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
    logger.error("参数解析失败", e); 
    return ResultUtils.makeModel(HttpStatus.BAD_REQUEST.value(), "could_not_read_json");
  }

  /**
   * 400 - Bad Request
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ModelMap handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    logger.error("参数验证失败", e);
    BindingResult result = e.getBindingResult();
    String message = getArgumentErrorMessage(result);
    return ResultUtils.makeModel(HttpStatus.BAD_REQUEST.value(), message);
  }

  private String getArgumentErrorMessage(BindingResult result) {
    FieldError error = result.getFieldError();
    String field = error.getField();
    String code = error.getDefaultMessage();
    return String.format("%s:%s", field, code);
  }

  /**
   * 400 - Bad Request
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(BindException.class)
  public ModelMap handleBindException(BindException e) {
    logger.error("参数绑定失败", e);
    BindingResult result = e.getBindingResult();
    String message = getArgumentErrorMessage(result);
    return ResultUtils.makeModel(HttpStatus.BAD_REQUEST.value(), message);
  }

  /**
   * 400 - Bad Request
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(ConstraintViolationException.class)
  public ModelMap handleServiceException(ConstraintViolationException e) {
    logger.error("参数验证失败", e);
    Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
    ConstraintViolation<?> violation = violations.iterator().next();
    String message = violation.getMessage();
    return ResultUtils.makeModel(HttpStatus.BAD_REQUEST.value(), message);
  }

  /**
   * 400 - Bad Request
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(ValidationException.class)
  public ModelMap handleValidationException(ValidationException e) {
    logger.error("参数验证失败", e);
    return ResultUtils.makeModel(HttpStatus.BAD_REQUEST.value(), "validation_exception");
  }

  /**
   * 404 - Not Found
   * 404时，spring-boot并不会抛出异常，而是重定向到error/
   * 需要application.properties中配置spring.mvc.throw-exception-if-no-handler-found=true（出现错误时, 直接抛出异常）
   * 和spring.resources.add-mappings=false（不要为我们工程中的资源文件建立映射）
   */
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(value = NoHandlerFoundException.class)
  public ModelMap handleNoHandlerFoundException(NoHandlerFoundException e) {
    logger.error("404", e);
    return ResultUtils.makeModel(HttpStatus.BAD_REQUEST.value(), "not-found");
  }

  /**
   * 405 - Method Not Allowed
   */
  @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public ModelMap handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
    logger.error("不支持当前请求方法", e);
    return ResultUtils.makeModel(HttpStatus.BAD_REQUEST.value(), "request_method_not_supported");
  }

  /**
   * 415 - Unsupported Media Type
   */
  @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
  @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
  public ModelMap handleHttpMediaTypeNotSupportedException(Exception e) {
    logger.error("不支持当前媒体类型", e);
    return ResultUtils.makeModel(HttpStatus.BAD_REQUEST.value(), "content_type_not_supported");
  }

  /**
   * 500 - Internal Server Error
   */
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(ServiceException.class)
  public ModelMap handleServiceException(ServiceException e) {
    logger.error("业务逻辑异常", e);
    return ResultUtils.makeModel(HttpStatus.BAD_REQUEST.value(), e.getMessage());
  }

  /**
   * 500 - Internal Server Error
   */
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(Exception.class)
  public ModelMap handleException(Exception e) {
    logger.error("通用异常", e);
    return ResultUtils.makeModel(HttpStatus.BAD_REQUEST.value(), "通用异常：" + e.getMessage());
  }

}
