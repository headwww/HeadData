package com.head.http.exception;

public class ErrorResponse {
    private int errorCode;
    private String errorText;
    private String throwable;
    private boolean success;

    public ErrorResponse() {
    }

    public ErrorResponse(int errorCode, String errorText, String throwable, boolean success) {
        this.errorCode = errorCode;
        this.errorText = errorText;
        this.throwable = throwable;
        this.success = success;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorText() {
        return errorText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }

    public String getThrowable() {
        return throwable;
    }

    public void setThrowable(String throwable) {
        this.throwable = throwable;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
