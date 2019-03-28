package harusami.talk.client.control;
/*
 * @package: harusami.talk.Control
 * @program: HRSTalk
 * @description  ${description}
 *
 * @author:  rinne
 * @e-mail:  minami.rinne.me@gmail.com
 * @date: 2019/03/20 上午 12:41
 */


import harusami.serialize.CommandTranser;
import harusami.talk.client.socket.ClientSocket;
import harusami.talk.client.socket.ClientTread;

/**
 * @classname: SignUpControl
 * @description: %{description}
 * @author: rinne
 * @date: 2019/03/20 上午 12:41
 * @Version 1.0
 */
public class SignUpControl extends Thread {

    private ClientSocket clientSocket;
    private ClientTread clientTread;
    private CommandTranser commandTranser;

    public SignUpControl() {
        this.commandTranser = null;
        this.clientSocket = null;
        this.clientTread = null;
    }

    public SignUpControl(CommandTranser commandTranser) {
        this.commandTranser = commandTranser;
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
