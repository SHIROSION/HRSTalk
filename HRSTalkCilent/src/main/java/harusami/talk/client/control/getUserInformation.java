package harusami.talk.client.control;
/*
 * @package: harusami.talk.client.control
 * @program: HRSTalk
 * @description  ${description}
 *
 * @author:  rinne
 * @e-mail:  minami.rinne.me@gmail.com
 * @date: 2019/03/29 上午 08:40
 */

import harusami.serialize.CommandTranser;
import harusami.serialize.UserInformation;
import harusami.talk.client.socket.ClientSocket;
import harusami.talk.client.socket.ClientTread;

/**
 * @classname: getUserInformation
 * @description: %{description}
 * @author: rinne
 * @date: 2019/03/29 上午 08:40
 * @Version 1.0
 */
public class getUserInformation extends Thread {
    private ClientSocket clientSocket;
    private ClientTread clientTread;
    private CommandTranser commandTranser;
    private UserInformation userInformation;

    public getUserInformation() {
        this.clientSocket = null;
        this.clientTread = null;
        this.commandTranser = null;
        this.userInformation = null;
    }

    public getUserInformation(CommandTranser commandTranser) {
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
