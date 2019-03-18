package harusami.talk.database;
/*
 * @package: com.harusami.talk.database
 * @program: HRSTalk
 * @description  ${description}
 *
 * @author:  rinne
 * @e-mail:  minami.rinne.me@gmail.com
 * @date: 2019/03/18 上午 11:55
 */

import java.sql.*;

/**
 * @classname: Model
 * @description: 数据连接的基础类
 * @author: rinne
 * @date: 2019/03/18 上午 11:55
 * @Version 1.0
 */
public class Model {

    private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost:3306/hrstalk?useSSL=false&serverTimezone=Asia/Shanghai";
    private final String user = "root";
    private final String pwd = "123456";
    private Connection connection = null;

    /**
     * 与数据库进行连接
     * @return
     */
    protected Connection getConnection() {
        connection = null;

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, pwd);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    /**
     * 对数据库进行查询
     * @param connection 获取数据库连接
     * @param sql 获取执行SQL查询语句
     * @param objects 获取SQL查询参数
     * @return
     */
    protected PreparedStatement preparedStatement(Connection connection, String sql, Object[] objects) {
        PreparedStatement ps = null;
        try {
            int index = 1;
            ps = connection.prepareStatement(sql);
            if (ps != null && objects != null) {
                for (int i = 0; i < objects.length; i++, index++) {
                    ps.setObject(index, objects[i]);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;
    }

    /**
     * 对数据库进行增、删、改动作
     * @param sql
     * @param objects
     * @return
     */
    protected int executeUpdate(String sql, Object[] objects) {
        connection = getConnection();
        PreparedStatement ps= null;
        try {
            ps = preparedStatement(connection, sql, objects);;
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            closeConnect(connection, ps, null);
        }
    }

    /**
     * 关闭数据库的连接
     * @param connection
     * @param ps
     * @param resultSet
     */
    protected void closeConnect(Connection connection, PreparedStatement ps, ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();

                if (ps != null) {
                    ps.close();
                }

                if (connection != null) {
                    connection.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
