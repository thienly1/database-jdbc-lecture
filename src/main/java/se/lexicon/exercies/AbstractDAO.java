package se.lexicon.exercies;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AbstractDAO {

    public void closeAll(AutoCloseable...closeables){
        if(closeables!= null){
            for(AutoCloseable closeable: closeables){
                try {
                    closeable.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    protected static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DatabaseCredentials.getINSTANCE().getURL(),
                DatabaseCredentials.getINSTANCE().getUSER(),
                DatabaseCredentials.getINSTANCE().getPASSWORD()
        );

    }
}
