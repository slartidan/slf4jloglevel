import org.slf4j.Logger;

import java.util.function.Function;

public enum LogLevel {

    TRACE(l -> l::trace, l -> l::isTraceEnabled),
    DEBUG(l -> l::debug, l -> l::isDebugEnabled),
    INFO(l -> l::info, l -> l::isInfoEnabled),
    WARN(l -> l::warn, l -> l::isWarnEnabled),
    ERROR(l -> l::error, l -> l::isErrorEnabled);

    interface LogMethod {
        void log(String format, Object... arguments);
    }

    interface IsEnabledMethod {
        boolean isEnabled();
    }

    private final Function<Logger, LogMethod> logMethod;
    private final Function<Logger, IsEnabledMethod> isEnabledMethod;

    LogLevel(Function<Logger, LogMethod> logMethod, Function<Logger, IsEnabledMethod> isEnabledMethod) {
        this.logMethod = logMethod;
        this.isEnabledMethod = isEnabledMethod;
    }

    public LogMethod prepare(Logger logger) {
        return logMethod.apply(logger);
    }

    public boolean isEnabled(Logger logger) {
        return isEnabledMethod.apply(logger).isEnabled();
    }
}
