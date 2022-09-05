package se.lexicon.exercies;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class DatabaseCredentials {

    private String URL;
    private String USER;
    private String PASSWORD;

    private static DatabaseCredentials INSTANCE;

    public static DatabaseCredentials getINSTANCE() {
        if(INSTANCE == null){
            throw new IllegalArgumentException(" There is no instance created, call initialize method first");
        }
        return INSTANCE;
    }
    public static void initialize(String url){
        INSTANCE = new DatabaseCredentials();
        INSTANCE.loadProperties(Paths.get(url));
    }
    public void loadProperties(Path path){
        Properties properties = new Properties();
        try {
            properties.load(Files.newBufferedReader(path));
            URL = properties.getProperty("url");
            USER = properties.getProperty("username");
            PASSWORD = properties.getProperty("password");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public DatabaseCredentials() {
    }

    public String getURL() {
        return URL;
    }

    public String getUSER() {
        return USER;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }


}
