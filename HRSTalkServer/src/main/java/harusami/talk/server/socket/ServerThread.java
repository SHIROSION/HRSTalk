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

                if (commandTranser.getCmd().equals("LogIn")) {
                    CommandTranser information = new CommandTranser();
                    information.setCmd("LogInInformation");
                    information.setData(new UserModel().loginData((LoginInformation) commandTranser.getData()));
                    objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                    objectOutputStream.writeObject(information);
                }

                if (commandTranser.getCmd().equals("LogInSuccessful")) {
                    CommandTranser information = new CommandTranser();
                    UserModel userModel = new UserModel();

                    userModel.updateLoginTime((LoginInformation) commandTranser.getData());
                    session.put(commandTranser.getSender(), socket);

                    information.setCmd("UserInformation");
                    information.setData(new UserModel().getOneData(((LoginInformation) commandTranser.getData()).getUserEmail()));

                    objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                    objectOutputStream.writeObject(information);
                }

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
