import org.slf4j.Logger;

import java.util.function.Function;

public enum LogLevel {

    TRACE(l -> l::trace, l -> l::trace, l -> l::isTraceEnabled),
    DEBUG(l -> l::debug, l -> l::debug, l -> l::isDebugEnabled),
    INFO(l -> l::info, l -> l::info, l -> l::isInfoEnabled),
    WARN(l -> l::warn, l -> l::warn, l -> l::isWarnEnabled),
    ERROR(l -> l::error, l -> l::error, l -> l::isErrorEnabled);

    interface LogMethod {
        void log(String format, Object... arguments);
    }

    interface LogThrowableMethod {
        void log(String msg, Throwable t);
    }

    interface IsEnabledMethod {
        boolean isEnabled();
    }

    private final Function<Logger, LogMethod> logMethod;
    private final Function<Logger, LogThrowableMethod> logThrowableMethod;
    private final Function<Logger, IsEnabledMethod> isEnabledMethod;

    LogLevel(Function<Logger, LogMethod> logMethod, Function<Logger, LogThrowableMethod> logThrowableMethod, Function<Logger, IsEnabledMethod> isEnabledMethod) {
        this.logMethod = logMethod;
        this.logThrowableMethod = logThrowableMethod;
        this.isEnabledMethod = isEnabledMethod;
    }

    public LogMethod message(Logger logger) {
        return logMethod.apply(logger);
    }

    public LogThrowableMethod throwable(Logger logger) {
        return logThrowableMethod.apply(logger);
    }

    public boolean isEnabled(Logger logger) {
        return isEnabledMethod.apply(logger).isEnabled();
    }
}
