import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Application.class);

        LogLevel level = LogLevel.ERROR;
        level.prepare(logger).log("It works!"); // just message, without parameter
        level.prepare(logger).log("Hello {}!", "world"); // with slf4j's parameter replacing

        try {
            throw new RuntimeException("Oops");
        } catch (Throwable t) {
            level.prepare(logger).log("Exception", t);
        }

        if (level.isEnabled(logger)) {
            level.prepare(logger).log("logging is enabled");
        }
    }
}
