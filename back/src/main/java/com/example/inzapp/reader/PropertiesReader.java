package com.example.inzapp.reader;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Logger;


public final class PropertiesReader {


    private static final Properties PROPERTIES;

    private static final String PROP_FILE = "apikey.properties";

    private PropertiesReader() {
    }

    static {
        PROPERTIES = new Properties();
        final URL props = ClassLoader.getSystemResource(PROP_FILE);
        try {
            PROPERTIES.load(props.openStream());
        } catch (IOException ex) {

        }
    }


    public static String getProperty(final String name) {

        return PROPERTIES.getProperty(name);
    }
}