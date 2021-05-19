package com.selenium.selenium_testing.operation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadObject {
    Properties properties = new Properties();

    public Properties getObjectRepository() throws IOException {
        InputStream stream = new FileInputStream(
                new File(System.getProperty("user.dir") + "/src/main/resources/object.properties"));
        properties.load(stream);
        return properties;
    }

}
