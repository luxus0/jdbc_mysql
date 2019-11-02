package websocket.websocket.Database.Mysql;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CrudOperation {

    public static String insert()
    {
        String sql = "CREATE TABLE client(id int NOT NULL, name varchar(100)NOT NULL,surname varchar(100)NOT NULL,age int NOT NULL,pesel int NOT NULL,email varchar(100) NOT NULL,numberAccount long NOT NULL)";
        return sql;
    }

    public static String select()
    {
        String sql = "";
        return sql;
    }

    public static String update()
    {
        String sql = "";
        return sql;
    }

    public void delete()
    {

    }

    public static void main(String args[]) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql//127.0.0.1/database","lukasz","lukasz");
        Statement statement = connection.createStatement();
        statement.execute(insert());
        statement.execute(select());
    }


}
