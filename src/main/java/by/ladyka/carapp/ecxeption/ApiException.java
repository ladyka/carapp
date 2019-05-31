package by.ladyka.carapp.ecxeption;

import java.util.function.Supplier;

public class ApiException extends RuntimeException {

    public ApiException(String message, Object... args) {
        super(String.format(message, args));
    }

    public static Supplier<ApiException> formatMessage(String message, Object... args) {
        return () -> new ApiException(message, args);
    }
}
