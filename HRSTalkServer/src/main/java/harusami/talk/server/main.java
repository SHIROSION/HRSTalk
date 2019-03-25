package harusami.talk.server;
/*
 * @package: harusami.talk.server
 * @program: HRSTalk
 * @description  ${description}
 *
 * @author:  rinne
 * @e-mail:  minami.rinne.me@gmail.com
 * @date: 2019/03/25 下午 10:20
 */

import harusami.talk.server.socket.Server;

/**
 * @classname: main
 * @description: %{description}
 * @author: rinne
 * @date: 2019/03/25 下午 10:20
 * @Version 1.0
 */
public class main {
    public static void main(String[] args) {
        Server server = new Server();
        server.startService();
    }
}
