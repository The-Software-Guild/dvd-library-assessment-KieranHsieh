package com.kieran.dvd_library.ui;

import com.kieran.dvd_library.util.ConversionOp;

import java.text.NumberFormat;
import java.util.Scanner;
import java.util.function.Predicate;

/**
 * The console implementation of the UserIO interface
 */
public class UserIOConsoleImpl implements UserIO {
    /**
     * Displays a message
     * @param msg The message to display, msg MUST be a valid String object
     * @throws UserIOException thrown when an error occurs when displaying the message
     */
    @Override
    public void displayMessage(String msg) throws UserIOException {
        System.out.println(msg);
    }
    /**
     * Stalls the application until an input string has been received.
     *
     * @param msg The message to prompt the user with. If msg is null, the parameter is ignored
     * @return The String inputted by the user
     * @throws UserIOException thrown when an error occurs retrieving the user's input
     */
    @Override
    public String getInputString(String msg) throws UserIOException {
        if(msg != null) {
            System.out.print(msg);
        }
        return getUserInput(input -> true, strVal -> strVal);
    }
    /**
     * Stalls the application until an input string has been received.
     *
     * @param msg The message to prompt the user with. If msg is null, the parameter is ignored
     * @param validateFnc The function to use when validating user input. Upon validateFnc returning false,
     *                    User input is asked for again until validateFnc returns true
     * @return The String inputted by the user
     * @throws UserIOException thrown when an error occurs retrieving the user's input
     */
    @Override
    public String getInputString(String msg, Predicate<String> validateFnc) throws UserIOException {
        if(msg != null) {
            System.out.print(msg);
        }
        return getUserInput(validateFnc, strVal -> strVal);
    }
    /**
     * Stalls the application until an input number has been received.
     * @param msg The message to prompt the user with. If msg is null, the parameter is ignored
     * @return The resulting inputted number
     * @throws UserIOException thrown when an error occurs retrieving the user's input
     */
    @Override
    public Number getInputNumber(String msg) throws UserIOException {
        if(msg != null) {
            System.out.print(msg);
        }
        return getUserInput(input -> true, strVal -> NumberFormat.getInstance().parse(strVal));
    }
    /**
     * Stalls the application until an input number has been received.
     * @param msg The message to prompt the user with. If msg is null, the parameter is ignored
     * @param validateFnc The function to use when validating user input. Upon validateFnc returning false,
     *                    User input is asked for again until validateFnc returns true
     * @return The resulting inputted number
     * @throws UserIOException thrown when an error occurs retrieving the user's input
     */
    @Override
    public Number getInputNumber(String msg, Predicate<Number> validateFnc) throws UserIOException {
        if(msg != null) {
            System.out.print(msg);
        }
        return getUserInput(validateFnc, strVal -> NumberFormat.getInstance().parse(strVal));
    }

    /**
     * Generically retrieve user input.
     * This function will ask for user input until validateFnc is satisfied
     * @param validateFnc The function used to validate the user input
     * @param conversionOp The operation converting the inputted string to the resulting input type
     * @param <T> The type of input to retrieve
     * @return The retrieved input
     * @throws UserIOException thrown when something goes wrong retrieving user input
     */
    private <T> T getUserInput(Predicate<T> validateFnc, ConversionOp<String, T> conversionOp) throws UserIOException {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            // Read input
            String strValue = scanner.nextLine();

            // Convert to final value
            T val;
            try {
                val = conversionOp.convert(strValue);
            }
            catch(Exception e) {
                throw new UserIOException("Invalid input");
            }
            if(validateFnc.test(val)) {
                return val;
            }
            System.out.println("Invalid Input, please try again");
        }
    }
}
