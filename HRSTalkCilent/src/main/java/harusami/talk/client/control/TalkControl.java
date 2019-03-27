package harusami.talk.client.control;
/*
 * @package: harusami.talk.client.control
 * @program: HRSTalk
 * @description  ${description}
 *
 * @author:  rinne
 * @e-mail:  minami.rinne.me@gmail.com
 * @date: 2019/03/27 上午 11:47
 */

import harusami.serialize.CommandTranser;
import harusami.talk.client.socket.ClientSocket;

/**
 * @classname: TalkControl
 * @description: %{description}
 * @author: rinne
 * @date: 2019/03/27 上午 11:47
 * @Version 1.0
 */
public class TalkControl extends Thread {

    private CommandTranser commandTranser;
    private ClientSocket clientSocket;

    public TalkControl() {
        this.commandTranser = null;
    }

    public TalkControl(CommandTranser commandTranser) {
        this.commandTranser = commandTranser;
    }

    @Override
    public void run() {
        clientSocket = new ClientSocket();
        clientSocket.sendData(commandTranser);
        try {
            sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
