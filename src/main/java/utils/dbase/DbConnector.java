package utils.dbase;

import utils.ConfProperties;

import java.sql.*;

public class DbConnector {

    /**
     * Подсоединяемся к нужной базе.
     *
     * @return - готовое соединение
     */
    private Statement getConnectToDataBase() throws Exception {
        {
            try {
                Connection connection = DriverManager.getConnection(
                        ConfProperties.getProperty("db_url"),
                        ConfProperties.getProperty("db_username"),
                        ConfProperties.getProperty("db_password"));
                return connection.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new Exception("Error connection to Data Base");
            }
        }
    }

    /**
     * Выполняем запрос в БД
     *
     * @param sqlRequest - SQL запрос, который нужно выполнить в базе
     * @return - ответ на запрос
     */
    public ResultSet requestToDataBase(String sqlRequest) throws Exception {
        {
            try {
                return getConnectToDataBase().executeQuery(sqlRequest);
            } catch (SQLException s) {
                s.printStackTrace();
                throw new Exception("Error in request in Data Base");
            }
        }
    }
}
