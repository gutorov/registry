package test.task.exception;

public class DistrictAlreadyExistsException extends RuntimeException {
    public DistrictAlreadyExistsException(String name) {
        super("District with the name " + name + " already  exists");
    }
}
