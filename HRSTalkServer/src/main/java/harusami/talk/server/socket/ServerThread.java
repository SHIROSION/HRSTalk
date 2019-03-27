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
import harusami.talk.server.control.SignUpControl;
import harusami.serialize.SignUpInformation;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @classname: ServerThread
 * @description: %{description}
 * @author: rinne
 * @date: 2019/03/25 上午 10:51
 * @Version 1.0
 */
public class ServerThread extends Thread {

    private Socket socket;

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
                        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                        objectOutputStream.writeObject(information.setCmd("SignInSuccessful"));
                    } else {
                        CommandTranser information = new CommandTranser();
                        information.setCmd("SignInError");
                        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
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
