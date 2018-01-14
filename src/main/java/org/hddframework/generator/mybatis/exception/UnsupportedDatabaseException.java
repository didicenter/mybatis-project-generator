package org.hddframework.generator.mybatis.exception;

/**
 * Created by SongWei on 10/01/2018.
 */
public class UnsupportedDatabaseException extends RuntimeException {

    public UnsupportedDatabaseException() {
    }

    public UnsupportedDatabaseException(String message) {
        super(message);
    }

    public UnsupportedDatabaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnsupportedDatabaseException(Throwable cause) {
        super(cause);
    }


}
