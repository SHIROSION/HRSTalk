package harusami.talk.client.socket;
/*
 * @package: harusami.talk.cilent.socket
 * @program: HRSTalk
 * @description  ${description}
 *
 * @author:  rinne
 * @e-mail:  minami.rinne.me@gmail.com
 * @date: 2019/03/24 下午 10:31
 */

import harusami.serialize.CommandTranser;
import harusami.talk.client.information.UserInformation;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @classname: ClientTread
 * @description: %{description}
 * @author: rinne
 * @date: 2019/03/24 下午 10:31
 * @Version 1.0
 */
public class ClientTread extends Thread {
    private Socket socket;
    private boolean isOnline = true;
    private ClientSocket clientSocket;

    public ClientTread(ClientSocket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        if (!isOnline) {
            return;
        }

        while (isOnline) {
            CommandTranser commandTranser = clientSocket.getData();
            if (commandTranser != null) {
                execute(commandTranser);
            }
        }
    }

    private void execute(CommandTranser commandTranser) {
        if ("Talk".equals(commandTranser.getCmd())) {
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd a hh:mm:ss:▶");
            
        }
    }
}
