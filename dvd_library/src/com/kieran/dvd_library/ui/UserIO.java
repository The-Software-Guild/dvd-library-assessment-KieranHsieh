package com.kieran.dvd_library.ui;

import java.util.function.Predicate;

/**
 * An interface used to query for user input
 *
 * All functions prefixed with get are blocking functions, and
 * will prevent the program from continuation until they return.
 */
public interface UserIO {
    /**
     * Displays a message
     * @param msg The message to display, msg MUST be a valid String object
     * @throws UserIOException thrown when an error occurs when displaying the message
     */
    void displayMessage(String msg) throws UserIOException;

    /**
     * Stalls the application until an input string has been received.
     *
     * @param msg The message to prompt the user with. If msg is null, the parameter is ignored
     * @return The String inputted by the user
     * @throws UserIOException thrown when an error occurs retrieving the user's input
     */
    String getInputString(String msg) throws UserIOException;
    /**
     * Stalls the application until an input string has been received.
     *
     * @param msg The message to prompt the user with. If msg is null, the parameter is ignored
     * @param validateFnc The function to use when validating user input. Upon validateFnc returning false,
     *                    User input is asked for again until validateFnc returns true
     * @return The String inputted by the user
     * @throws UserIOException thrown when an error occurs retrieving the user's input
     */
    String getInputString(String msg, Predicate<String> validateFnc) throws UserIOException;
    /**
     * Stalls the application until an input number has been received.
     * @param msg The message to prompt the user with. If msg is null, the parameter is ignored
     * @return The resulting inputted number
     * @throws UserIOException thrown when an error occurs retrieving the user's input
     */
    Number getInputNumber(String msg) throws UserIOException;
    /**
     * Stalls the application until an input number has been received.
     * @param msg The message to prompt the user with. If msg is null, the parameter is ignored
     * @param validateFnc The function to use when validating user input. Upon validateFnc returning false,
     *                    User input is asked for again until validateFnc returns true
     * @return The resulting inputted number
     * @throws UserIOException thrown when an error occurs retrieving the user's input
     */
    Number getInputNumber(String msg, Predicate<Number> validateFnc) throws UserIOException;
}
