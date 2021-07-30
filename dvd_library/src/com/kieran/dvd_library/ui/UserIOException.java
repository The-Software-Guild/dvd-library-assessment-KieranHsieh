package com.kieran.dvd_library.ui;

/**
 * An exception thrown when something goes wrong when retrieving or outputting
 * user input or messages
 */
public class UserIOException extends Exception {
    /**
     * Constructs a new UserIOException with no message
     */
    public UserIOException() {
        super();
    }

    /**
     * Constructs a new UserIOException with a given message
     * @param msg The message of the exception
     */
    public UserIOException(String msg) {
        super(msg);
    }
}
