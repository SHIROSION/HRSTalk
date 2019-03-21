package harusami.talk.cilent.socket;
/*
 * @package: harusami.talk.cilent.socket
 * @program: HRSTalk
 * @description  ${description}
 *
 * @author:  rinne
 * @e-mail:  minami.rinne.me@gmail.com
 * @date: 2019/03/21 下午 10:58
 */

import harusami.talk.cilent.control.email.TalkControl;
import harusami.talk.cilent.gui.TalkWindows;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.Socket;
import java.net.SocketException;

/**
 * @classname: ClientTalkSocketThread
 * @description: %{description}
 * @author: rinne
 * @date: 2019/03/21 下午 10:58
 * @Version 1.0
 */
public class ClientTalkSocketThread extends Thread {

    private Socket socket = null;
    private Reader reader = null;
    private int len = 0;
    private char[] chars = new char[1024];
    private TalkControl talkControl = null;
    private String temp = "";

    public ClientTalkSocketThread(Socket socket, TalkControl talkControl) {
        this.socket = socket;
        this.talkControl = talkControl;

        try {
            reader = new InputStreamReader(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        System.out.println("客户端 子线程" + this.getId() + "开始工作");
        while (true) {
            if (!socket.isClosed()) {
                if (!socket.isInputShutdown()) {
                    try {
                        while (reader.read(chars) != -1) {
                            temp = new String(chars, 0, len);

                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        if (!socket.getKeepAlive()) {
                            reader.close();
                            socket.close();
                            sleep(2000);
                            this.interrupt();
                        }
                    } catch (SocketException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
