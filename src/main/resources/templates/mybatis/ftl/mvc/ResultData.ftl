package ${packageName}.configuration.mvc;

import lombok.Data;


@Data
public class ResultData {

    public ResultData() {
        this.statusCode = Status.SUCCESS.value;
        this.status = Status.SUCCESS;
    }

    private int statusCode = Status.SUCCESS.value;
    private Status status = Status.SUCCESS;
    private Object data;

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
        if (Status.SUCCESS.value == statusCode) {
            this.setStatus(Status.SUCCESS);
        }
    }

    public void setStatus(Status status) {
        this.status = status;
        this.setStatusCode(status.value);
    }

    public enum Status {
        SUCCESS(0), FAIL(1);
        int value;

        public int getValue() {
            return this.value;
        }

        Status(int value) {
            this.value = value;
        }
    }

}
