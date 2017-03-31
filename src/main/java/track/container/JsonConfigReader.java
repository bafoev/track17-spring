package track.container;

import java.io.File;
import java.util.List;
import java.io.IOException;

import track.container.config.Bean;
import track.container.config.ConfigReader;
import track.container.config.InvalidConfigurationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import track.container.config.Root;

/**
 * TODO: Реализовать
 */
public class JsonConfigReader implements ConfigReader {

    @Override
    public List<Bean> parseBeans(File configFile) throws InvalidConfigurationException {
        Root root;
        ObjectMapper maper = new ObjectMapper();

        try {
            root = maper.readValue(configFile, Root.class);
        } catch (IOException e) {
            throw new InvalidConfigurationException(e.getMessage());
        }
        return root.getBeans();
    }
}
