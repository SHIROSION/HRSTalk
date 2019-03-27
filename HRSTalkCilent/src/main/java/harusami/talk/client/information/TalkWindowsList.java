package harusami.talk.client.information;
/*
 * @package: harusami.talk.client.gui
 * @program: HRSTalk
 * @description  ${description}
 *
 * @author:  rinne
 * @e-mail:  minami.rinne.me@gmail.com
 * @date: 2019/03/28 上午 01:55
 */

import harusami.talk.client.gui.TalkWindows;

import java.util.HashMap;

/**
 * @classname: TalkWindowsList
 * @description: %{description}
 * @author: rinne
 * @date: 2019/03/28 上午 01:55
 * @Version 1.0
 */
public class TalkWindowsList {
    private static HashMap<String, TalkWindows> map = new HashMap<String, TalkWindows>();

    public static void addChatUI(TalkWindowsEntity talkWindowsEntity) {
        map.put(talkWindowsEntity.getName(), talkWindowsEntity.getTalkWindows());
    }

    public static void deleteTalkWindows(String talkWindows) {
        if (map.get(talkWindows) != null) {
            map.remove(talkWindows);
        }
    }

    public static TalkWindows getTalkWindows(String name) {
        return map.get(name);
    }
}
