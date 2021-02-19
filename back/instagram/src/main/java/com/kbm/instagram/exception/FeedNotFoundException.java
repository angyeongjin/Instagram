package com.kbm.instagram.exception;

public class FeedNotFoundException extends RuntimeException {
    public FeedNotFoundException(Long id) {
        super("Could not find feed " + id);
    }
}
