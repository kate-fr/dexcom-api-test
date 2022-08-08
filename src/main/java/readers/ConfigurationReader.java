package readers;


import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class ConfigurationReader {

    private static final Logger log = LogManager.getLogger(ConfigurationReader.class);


    public static Config read() {
        String env = System.getProperty("env") == null ? "default" : System.getProperty("env");
        Config defaultConfig = ConfigFactory.parseResources("defaults.conf");

        log.info(env + " configuration is going to be used");

        return env.equals("default") ? defaultConfig
                : ConfigFactory.parseResources(env + ".conf")
                .withFallback(defaultConfig).resolve();
    }


}
