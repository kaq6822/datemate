package com.datemate.common;

public class ServiceException extends Exception {

    String code = "99999";

    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String format, Object... args) {
        super(String.format(format.replaceAll("\\{\\}", "%s"), args),
                (Exception)(args[args.length - 1] instanceof Throwable ? args[args.length - 1] : null));
    }

    public ServiceException(Throwable cause , String format, Object... args) {
        super(String.format(format.replaceAll("\\{\\}", "%s"), args),cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException setCause(Exception e) {
        initCause(e);
        return this;
    }

    public ServiceException setErrorCode(String code) {
        this.code = code;

        return this;
    }

    public String getCode() {
        return code;
    }
}
