package org.abpira.identity.exceptions;

public class AddressMustNotBeEmptyException extends RuntimeException {
    public AddressMustNotBeEmptyException(String message) {
        super(message);
    }
}
