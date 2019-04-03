package harusami.talk.server.socket;
/*
 * @package: harusami.talk.server.socket
 * @program: HRSTalk
 * @description  ${description}
 *
 * @author:  rinne
 * @e-mail:  minami.rinne.me@gmail.com
 * @date: 2019/03/25 上午 10:51
 */

import harusami.serialize.CommandTranser;
import harusami.serialize.SendInformation;
import harusami.talk.server.control.PasswordEncryption;
import harusami.talk.server.control.SignUpControl;
import harusami.serialize.SignUpInformation;
import harusami.talk.server.database.UserModel;
import harusami.serialize.LoginInformation;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * @classname: ServerThread
 * @description: %{description}
 * @author: rinne
 * @date: 2019/03/25 上午 10:51
 * @Version 1.0
 */
public class ServerThread extends Thread {

    private Socket socket;

    /**
     * session存储连接对象，并且设置为静态方法
     */
    private static Map<String, Socket> session = new HashMap<>();

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutputStream = null;

        while(socket != null) {
            try {
                objectInputStream = new ObjectInputStream(socket.getInputStream());
                CommandTranser commandTranser = (CommandTranser) objectInputStream.readObject();

                // 当报文是注册请求的时候
                // 如果提交数据库成功，则发送注册成功信息，否则报错
                if (commandTranser.getCmd().equals("SignUp")) {
                    SignUpControl signUpControl = new SignUpControl((SignUpInformation) commandTranser.getData());
                    if (signUpControl.executeSignUp()) {
                        CommandTranser information = new CommandTranser();
                        information.setCmd("SignInSuccessful");
                        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                        objectOutputStream.writeObject(information);
                    } else {
                        CommandTranser information = new CommandTranser();
                        information.setCmd("SignInError");
                        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                        objectOutputStream.writeObject(information);
                    }
                }

                // 当报文是登陆请求的时候
                // 从Model得到该用户的密码查询结果，然后通过PasswordEncryption的validPassword得到密码判断结果
                // 若密码正确则给客户端返回正确的讯息并且返回该用户的个人信息，否则返回错误的讯息
                if (commandTranser.getCmd().equals("LogIn")) {
                    CommandTranser information = new CommandTranser();
                    if (PasswordEncryption.validPassword(((LoginInformation) commandTranser.getData()).getPassword(),
                            new UserModel().loginData((LoginInformation) commandTranser.getData()))) {

                        new UserModel().updateLoginTime((LoginInformation) commandTranser.getData());
                        session.put(commandTranser.getSender(), socket);
                        information.setCmd("LogInSuccessful");
                        information.setData(new UserModel().getOneData(((LoginInformation) commandTranser.getData()).getUserEmail()));

                        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                        objectOutputStream.writeObject(information);
                    } else {
                        information.setCmd("LogInError");
                        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                        objectOutputStream.writeObject(information);
                    }
                }

                // 当报文是登陆成功的时候
                // 在数据库上打上时间戳
//                if (commandTranser.getCmd().equals("LogInSuccessful")) {
//                    CommandTranser information = new CommandTranser();
//                    UserModel userModel = new UserModel();
//
//                    userModel.updateLoginTime((LoginInformation) commandTranser.getData());
//                    session.put(commandTranser.getSender(), socket);
//
//                    information.setCmd("UserInformation");
//                    information.setData(new UserModel().getOneData(((LoginInformation) commandTranser.getData()).getUserEmail()));
//
//                    objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
//                    objectOutputStream.writeObject(information);
//                }

                // 当报文是聊天请求的时候
                // 现在session中查找聊天对象的连接信息，然后把报文再次封装发送给聊天对象
                if (commandTranser.getCmd().equals("Talk")) {
                    SendInformation sendInformation = (SendInformation) commandTranser.getData();
                    CommandTranser information = new CommandTranser();

                    if (session.containsKey(sendInformation.getTo())) {
                        information.setCmd("Talk");
                        information.setData(sendInformation);

                        objectOutputStream = new ObjectOutputStream(session.get(sendInformation.getTo()).getOutputStream());
                        objectOutputStream.writeObject(information);
                    }
                }
            } catch (Exception e) {
                try {
                    if (objectInputStream != null) { objectInputStream.close(); }
                    if (socket != null) { socket.close(); break;}
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                e.printStackTrace();
            }
        }
    }
}
