package com.company.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbUtil {
    private static Connection dbConnection = null;
    private static final String DB_PROPERTIES_FILE = "resources/db.properties";
    private static final String DB_CONNECTION_URL_PROPERTY_NAME = "connectionUrl";
    private static final String DB_USER_NAME_PROPERTY_NAME = "userName";
    private static final String DB_PASSWORD_PROPERTY_NAME = "password";

    public static Connection getConnection () {
        if (dbConnection == null) {
            try (InputStream is = DbUtil.class.getClassLoader().getResourceAsStream(DB_PROPERTIES_FILE);) {
                Properties properties = new Properties();
                properties.load(is);
                String connectionUrl = properties.getProperty(DB_CONNECTION_URL_PROPERTY_NAME);
                String userName = properties.getProperty(DB_USER_NAME_PROPERTY_NAME);
                String password = properties.getProperty(DB_PASSWORD_PROPERTY_NAME);
                dbConnection = DriverManager.getConnection(connectionUrl, userName, password);
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        }
        return dbConnection;
    }
}
