package test.task.exception;

import test.task.handler.ErrorResponse;

public class DistrictAlreadyExistsException extends RuntimeException {
    public DistrictAlreadyExistsException(String name) {
        super("District with the name " + name + " already  exists");
    }
}
