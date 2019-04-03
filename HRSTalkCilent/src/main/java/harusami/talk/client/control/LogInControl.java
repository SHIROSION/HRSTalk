package harusami.talk.client.control;
/*
 * @package: harusami.talk.Control
 * @program: HRSTalk
 * @description  ${description}
 *
 * @author:  rinne
 * @e-mail:  minami.rinne.me@gmail.com
 * @date: 2019/03/19 下午 08:20
 */

import harusami.serialize.CommandTranser;
import harusami.talk.client.socket.ClientSocket;
import harusami.talk.client.socket.ClientTread;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;


/**
 * @classname: LogInControl
 * @description: %{description}
 * @author: rinne
 * @date: 2019/03/19 下午 08:20
 * @Version 1.0
 */
public class LogInControl extends Thread {

    private ClientSocket clientSocket;
    private ClientTread clientTread;
    private CommandTranser commandTranser;
    private static boolean logInstatus;

    public LogInControl() {
        this.commandTranser = null;
        this.clientSocket = null;
        this.clientTread = null;
        logInstatus = false;
    }

    public LogInControl(CommandTranser commandTranser) {
        this.commandTranser = commandTranser;
    }

    public static void setLogInstatus(boolean status) {
        logInstatus = status;
    }

    public static boolean getLogInstatus() {
        return logInstatus;
    }

    @Override
    public void run() {
        clientSocket = new ClientSocket();
        clientTread = new ClientTread(clientSocket);
        clientSocket.sendData(this.commandTranser);
        clientTread.start();
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
