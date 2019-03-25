package harusami.talk.server.information;
/*
 * @package: harusami.talk.server.information
 * @program: HRSTalk
 * @description  ${description}
 *
 * @author:  rinne
 * @e-mail:  minami.rinne.me@gmail.com
 * @date: 2019/03/25 下午 08:00
 */

import java.net.Socket;

/**
 * @classname: SocketEntity
 * @description: %{description}
 * @author: rinne
 * @date: 2019/03/25 下午 08:00
 * @Version 1.0
 */
public class SocketEntity {
    private Socket socket;
    private String name;

    public SocketEntity() {
        super();
    }

    public SocketEntity(Socket socket, String name) {
        super();
        this.socket = socket;
        this.name = name;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
