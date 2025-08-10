package com.hudl.builders;

import com.google.gson.Gson;
import com.hudl.wtos.RegionManagerWto;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

import static com.hudl.tests.BaseTest.grid;


public class RegionManagerBuilder {

    private static final Logger logger = Logger.getLogger(RegionManagerBuilder.class.getName());

    public static RegionManagerWto buildRegionConfig() throws IOException {
        Path path = Paths.get(System.getProperty("user.dir", "../")).getParent();
        String rootRecourcePath = Paths.get(path.toString(), "hudl-ui-automation", "src/test/resources/cfg", grid + ".json").toString();
        String json = FileUtils.readFileToString(new File(rootRecourcePath), StandardCharsets.UTF_8);
        logger.info("Fetching environment variables from path: {}" + json);
        return new Gson().fromJson(json, RegionManagerWto.class);

    }

}
