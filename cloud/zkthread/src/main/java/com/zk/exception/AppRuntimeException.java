package com.zk.exception;

public class AppRuntimeException extends RuntimeException {
    private static final long serialVersionUID = -4085206034008188969L;

    public AppRuntimeException(Throwable e, String format, Object... otherMsg) {

    }

    public AppRuntimeException() {
    }

    public AppRuntimeException(String format, Object... otherMsg) {

    }
    @Override
    public String toString() {
        return ((AppException) this.getCause()).toString();
    }
}
