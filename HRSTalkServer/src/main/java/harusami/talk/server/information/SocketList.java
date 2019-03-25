package harusami.talk.server.information;
/*
 * @package: harusami.talk.server.information
 * @program: HRSTalk
 * @description  ${description}
 *
 * @author:  rinne
 * @e-mail:  minami.rinne.me@gmail.com
 * @date: 2019/03/25 下午 07:55
 */

import java.net.Socket;
import java.util.HashMap;

/**
 * @classname: SocketList
 * @description: %{description}
 * @author: rinne
 * @date: 2019/03/25 下午 07:55
 * @Version 1.0
 */
public class SocketList {
    private static HashMap<String, Socket> map = new HashMap<String, Socket>();
    public static void addSocket(SocketEntity socketEntity) {
        map.put(socketEntity.getName(), socketEntity.getSocket());
    }

    //通过昵称返回socket 类比socklist在客户端创建 ChatUIList
    public static Socket getSocket(String name) {
        return map.get(name);
    }

    public static HashMap<String, Socket> getMap(){
        return map;
    }
    public static void deleteSocket(String name) {
        if(map.get(name) != null) {
            map.remove(name);
        }
        return;
    }
}
