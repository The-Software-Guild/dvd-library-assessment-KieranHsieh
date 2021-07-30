package com.kieran.dvd_library.util;

import java.text.ParseException;

/**
 * A functional interface for converting a value from one type to another.
 * This is essentailly an aliased Function&lt;From, To&gt; that throws a ParseException
 * @param <From> The type to convert from
 * @param <To> The type to convert to
 */
@FunctionalInterface
public interface ConversionOp<From, To> {
    /**
     * Converts a value from one type to another
     * @param val The value to convert
     * @return The value converted to To
     * @throws ParseException thrown when something goes wrong converting the value
     */
    To convert(From val) throws ParseException;
}
