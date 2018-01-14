package org.hddframework.generator.mybatis.exception;

/**
 * Created by SongWei on 10/01/2018.
 */
public class UnknownDatabaseException extends IllegalArgumentException {

    public UnknownDatabaseException() {
        super();
    }

    public UnknownDatabaseException(String s) {
        super(s);
    }

    public UnknownDatabaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownDatabaseException(Throwable cause) {
        super(cause);
    }

}
