import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Application.class);

        LogLevel level = LogLevel.ERROR;
        level.log(logger, "It works!"); // just message, without parameter
        level.log(logger, "Hello {}!", "world"); // with slf4j's parameter replacing

        if (level.isEnabled(logger)) {
            level.log(logger, "logging is enabled");
        }
    }
}
