package com.hudl.builders;

import com.google.gson.Gson;
import com.hudl.wtos.RegionManagerWto;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

import static com.hudl.tests.BaseTest.grid;


public class RegionManagerBuilder {

    private static final Logger logger = Logger.getLogger(RegionManagerBuilder.class.getName());

    /**
     * Loads a JSON configuration file from the classpath based on the 'grid' system property
     * and deserializes it into a RegionManagerWto object.
     *
     * @return -> RegionManagerWto configuration object.
     * @throws IOException
     */
    public static RegionManagerWto buildRegionConfig() throws IOException {

        String resourcePath = "cfg/" + grid + ".json";
        logger.info("Loading region config from classpath resource: " + resourcePath);

        try (InputStream inputStream = RegionManagerBuilder.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                logger.info("Resource not found on classpath: " + resourcePath);
                throw new IOException("Resource not found on classpath: " + resourcePath + ". Please ensure the file exists in src/test/resources/cfg/");
            }
            String json = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            return new Gson().fromJson(json, RegionManagerWto.class);
        }
    }

}
