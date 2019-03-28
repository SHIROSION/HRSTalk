package harusami.talk.server.socket;
/*
 * @package: harusami.talk.server.socket
 * @program: HRSTalk
 * @description  ${description}
 *
 * @author:  rinne
 * @e-mail:  minami.rinne.me@gmail.com
 * @date: 2019/03/25 下午 10:10
 */

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @classname: Server
 * @description: %{description}
 * @author: rinne
 * @date: 2019/03/25 下午 10:10
 * @Version 1.0
 */
public class Server {
    public void startService() {
        try {
            ServerSocket ss = new ServerSocket(8124);
            Socket socket = null;

            while(true) {
                socket = ss.accept();
                ServerThread thread = new ServerThread(socket);
                thread.start();
                Date date = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd a hh:mm:ss:");
                System.out.println(simpleDateFormat.format(date) + "  线程" + thread.getId() + " 已启动");
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
