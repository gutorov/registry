package test.task.errors;

public enum MessageError {
    DISTRICT_NOT_FOUNT_EXCEPTION("District was not found"),
    ;

    MessageError(String message){
        this.message = message;
    }

    private String message;

    public String getMessage() {
        return message;
    }
}
