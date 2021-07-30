package com.kieran.dvd_library.controller;

/**
 * An exception thrown when something goes wrong in the DVDLibraryController
 */
public class ControllerException extends Exception {
    /**
     * Constructs an empty ControllerException with a null message
     */
    public ControllerException() {
        super();
    }

    /**
     * Constructs a ControllerException with a given message
     * @param msg The message retrieved by the getMessage() function
     */
    public ControllerException(String msg) {
        super(msg);
    }
}
