package org.informatics.domain;

public class EmployeeSerializationException extends Exception {
    public EmployeeSerializationException(String message) {
        super(message);
    }

    public EmployeeSerializationException(String message, Throwable cause) {
        super(message, cause);
    }
}