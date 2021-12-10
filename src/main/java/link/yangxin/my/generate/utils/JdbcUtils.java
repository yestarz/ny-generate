package link.yangxin.my.generate.utils;

import link.yangxin.my.generate.po.Column;
import link.yangxin.my.generate.po.DatabaseProperties;
import link.yangxin.my.generate.po.TableInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yangxin
 * @date 2021/12/9
 */
public class JdbcUtils {

    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    private static Connection getConnection(DatabaseProperties databaseProperties) throws ClassNotFoundException, SQLException {
        if (threadLocal.get() != null) {
            return threadLocal.get();
        }
        Class.forName(databaseProperties.getDriverClassName());
        Connection connection = DriverManager.getConnection(databaseProperties.getJdbcUrl(), databaseProperties.getUsername(), databaseProperties.getPassword());
        threadLocal.set(connection);
        return connection;
    }

    private static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                threadLocal.remove();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    private static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    private static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static TableInfo getTableInfo(String tableName, DatabaseProperties databaseProperties) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection(databaseProperties);
            List<Column> columnList = getColumnList(connection, tableName);

            preparedStatement = connection.prepareStatement(String.format("show table status WHERE Name = '%s'", tableName));
            resultSet = preparedStatement.executeQuery();
            TableInfo tableInfo = new TableInfo();
            while (resultSet.next()) {
                tableInfo.setTableName(tableName);
                tableInfo.setTableComment(resultSet.getString("Comment"));
            }
            tableInfo.setColumnList(columnList);
            return tableInfo;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            close(connection);
            close(resultSet);
            close(preparedStatement);
        }
    }

    /**
     * 获取一张表的字段信息
     *
     * @param connection
     * @param tableName
     * @return
     */
    private static List<Column> getColumnList(Connection connection, String tableName) {
        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;
        try {
            prepareStatement = connection.prepareStatement(String.format("show full fields from `%s`", tableName));
            resultSet = prepareStatement.executeQuery();

            List<Column> columnList = new ArrayList<>();
            while (resultSet.next()) {
                String field = resultSet.getString("Field");
                String type = resultSet.getString("Type");
                String key = resultSet.getString("Key");
                String comment = resultSet.getString("Comment");

                Column column = new Column();
                column.setType(type);
                column.setName(field);
                column.setComment(comment);
                column.setPk("Pri".equals(key));
                columnList.add(column);
            }
            return columnList;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            close(resultSet);
            close(prepareStatement);
        }
    }


}
