package harusami.talk.server.database;
/*
 * @package: org.harusami.talk.database
 * @program: HRSTalk
 * @description  ${description}
 *
 * @author:  rinne
 * @e-mail:  minami.rinne.me@gmail.com
 * @date: 2019/03/18 下午 04:46
 */


import harusami.talk.server.information.LoginInformation;
import harusami.serialize.SignUpInformation;
import harusami.talk.server.information.UserInformation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @classname: UserModel
 * @description: %{description}
 * @author: rinne
 * @date: 2019/03/18 下午 04:46
 * @Version 1.0
 */
public class UserModel extends Model{

    /**
     *
     * @param sql
     * @param params
     * @return
     */
    private List<UserInformation> search(String sql, Object...params) {
        List<UserInformation> list = new ArrayList<UserInformation>();
        Connection connection = this.getConnection();
        PreparedStatement  preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = this.preparedStatement(connection, sql, params);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                UserInformation information = new UserInformation();
                information.setUid(resultSet.getInt(1));
                information.setUserName(resultSet.getString(2));
                information.setPwd(resultSet.getString(3));
                information.setGender(resultSet.getString(4));
                information.setEmail(resultSet.getString(5));
                information.setLoginIp(resultSet.getString(6));
                information.setLoginTime(resultSet.getString(7));
                information.setSignUpTime(resultSet.getString(8));
                list.add(information);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnect(connection, preparedStatement, resultSet);
        }

        return list;
    }

    /**
     * @return
     */
    public List<UserInformation> getTableAllData() {
        String sql = "select * from user_information";
        return search(sql);
    }

    /**
     *
     * @param userInformation
     * @return
     */
    public List<UserInformation> getOneData(UserInformation userInformation) {
        String sql = "select * from user_information where uid = ?";
        return search(sql, userInformation.getUid());
    }

    /**
     *
     * @param loginInformation
     * @return
     */
    public String loginData(LoginInformation loginInformation) {
        String sql = "select * from user_information where email = ?";
        return search(sql, loginInformation.getUserEmail()).get(0).getPwd();
    }

    public String getSignUpData(String getEmail) {
        String sql = "select * from user_information where email = ?";
        return search(sql, getEmail).get(0).getEmail();
    }

    /**
     * @param signUpInformation
     * @return
     */
    public int insert(SignUpInformation signUpInformation) {
        String sql = "insert into user_information (email, pwd, sign_up_time)" +
                " value (?, ?, ?)";
        return executeUpdate(sql, new Object[]{
                signUpInformation.getUserEmail(), signUpInformation.getUserPassword(), signUpInformation.getSignUpTime()
        });
    }

    /**
     * @param userInformation
     * @return
     */
    public int updateBaseInformation(UserInformation userInformation) {
        String sql = "update user_information set 'user_name' = ?, 'gender' = ?, 'email' = ? where uid = ?";
        return executeUpdate(sql, new Object[] {
                userInformation.getUserName(), userInformation.getGender(), userInformation.getEmail()
        });
    }

    /**
     *
     * @param userInformation
     * @return
     */
    public int updatePassword(UserInformation userInformation) {
        String sql = "update user_information set 'pwd' = ? where uid = ?";
        return executeUpdate(sql, new Object[] {userInformation.getPwd()});
    }

    /**
     *
     * @param userInformation
     * @return
     */
    public int updateLoginIp(UserInformation userInformation) {
        String sql = "update user_information set 'login_ip' = ? where uid = ?";
        return executeUpdate(sql, new Object[] {userInformation.getLoginIp()});
    }

    /**
     *
     * @param userInformation
     * @return
     */
    private int updateLoginTime(UserInformation userInformation) {
        String sql = "update user_information set 'login_time' = ? where uid = ?";
        return executeUpdate(sql, new Object[] {userInformation.getLoginTime()});
    }

    /**
     *
     * @param userInformation
     * @return
     */
    public int delete(UserInformation userInformation) {
        String sql = "DELETE FROM user_information WHERE uid = ?";
        return executeUpdate(sql, new Object[] {userInformation.getUid()});
    }
}
