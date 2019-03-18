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
 * @description: %{description}
 * @author: rinne
 * @date: 2019/03/18 上午 11:55
 * @Version 1.0
 */
public class Model {

    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/hrstalk?useSSL=false&serverTimezone=Asia/Shanghai";
    private String user = "root";
    private String pwd = "123456";
    private Connection connection = null;

    /**
     *
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
     *
     * @param connection
     * @param sql
     * @param objects
     * @return
     */
    public PreparedStatement preparedStatement(Connection connection, String sql, Object[] objects) {
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
     *
     * @param sql
     * @param objects
     * @return
     */
    public int executeUpdate(String sql, Object[] objects) {
        connection = getConnection();
        PreparedStatement ps= null;
        try {
            ps = preparedStatement(connection, sql, objects);
            int effectRow = ps.executeUpdate();
            return effectRow;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            closeConnect(connection, ps, null);
        }
    }

    /**
     *
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
