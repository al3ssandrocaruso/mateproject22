package kapta.utils.utils;

import kapta.utils.exception.myexception.MysqlConnectionFailed;
import kapta.utils.exception.Trigger;

import java.io.*;
import java.sql.*;
import java.util.Properties;

public class MysqlConnection {

    private MysqlConnection(){
        //ignore
    }

    private static Connection connection;
    public static Statement mysqlConnection() throws MysqlConnectionFailed {
        Statement stm = null;
        try {
            getConnection();
            stm = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {
            Trigger.mySqlConnectionException(e);
        }
        return stm;
    }

    public static  PreparedStatement upProfilePhotoPS() throws SQLException {
        return connection.prepareStatement( "INSERT INTO Utente VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" );
    }
    public  static PreparedStatement upProfileClubPhotoPS() throws SQLException {
        return connection.prepareStatement( "INSERT INTO Manager VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)" );
    }
    public  static PreparedStatement upProfileEventPhotoPS() throws SQLException {
        return connection.prepareStatement( "UPDATE Evento SET img = ? WHERE idEvento=?" );
    }
    public  static PreparedStatement upProfilePartyPhotoPS() throws SQLException {
        return connection.prepareStatement( "UPDATE Party SET img = ? WHERE idParty=?" );
    }

    private static Connection getConnection() throws SQLException {
        String user;
        String pass;
        String dbUrl;
        String driverClassName;

        if(connection==null){
            try {String resourceName = "config.properties";
                    InputStream inputStream = MysqlConnection.class.getClassLoader().getResourceAsStream(resourceName);
                    Properties props = new Properties();
                    props.load(inputStream);
                    pass = props.getProperty("PASS");
                    user = props.getProperty("USER");
                    dbUrl = props.getProperty("DB_URL");
                    driverClassName = props.getProperty("DRIVER_CLASS_NAME");
                    Class.forName(driverClassName);
                    DriverManager.setLoginTimeout(5);
                    connection = DriverManager.getConnection(dbUrl, user, pass);
                } catch (ClassNotFoundException | IOException e) {
                    e.printStackTrace();
                }

        }
        return connection ;
    }
}


