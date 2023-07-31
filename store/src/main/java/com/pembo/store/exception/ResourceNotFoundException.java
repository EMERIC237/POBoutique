package com.pembo.store.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String notFoundObject, long id) {
        super(notFoundObject + " with id " + id + " Not Found");
    }
}
