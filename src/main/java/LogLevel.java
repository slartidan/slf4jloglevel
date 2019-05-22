import org.slf4j.Logger;

import java.util.function.Function;

public enum LogLevel {

    TRACE(l -> l::trace),
    DEBUG(l -> l::debug),
    INFO(l -> l::info),
    WARN(l -> l::warn),
    ERROR(l -> l::error);

    interface LogMethod {
        void log(String format, Object... arguments);
    }

    private final Function<Logger, LogMethod> function;

    LogLevel(Function<Logger, LogMethod> function) {
        this.function = function;
    }

    public void log(Logger logger, String format, Object... arguments) {
        function.apply(logger).log(format, arguments);
    }
}
