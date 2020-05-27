package conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseProvider {
    private final static String server = "javaweb.huhaorui.com:3306";
    private final static String database = "zjut_health_code";
    private final static String uri = "jdbc:mysql://" + server + "/" + database + "?useUnicode=true&characterEncoding=utf8";
    private final static String user = "zjut_health_code";
    private final static String password = "zjut123456";

    public static Connection getConn() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(uri, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
