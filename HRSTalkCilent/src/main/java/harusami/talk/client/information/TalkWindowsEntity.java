package harusami.talk.client.information;
/*
 * @package: harusami.talk.client.information
 * @program: HRSTalk
 * @description  ${description}
 *
 * @author:  rinne
 * @e-mail:  minami.rinne.me@gmail.com
 * @date: 2019/03/28 上午 01:56
 */

import harusami.talk.client.gui.TalkWindows;

/**
 * @classname: TalkWindowsEntity
 * @description: %{description}
 * @author: rinne
 * @date: 2019/03/28 上午 01:56
 * @Version 1.0
 */
public class TalkWindowsEntity {
    private TalkWindows talkWindows;
    private String name;

    public TalkWindowsEntity() {

    }

    public TalkWindowsEntity(TalkWindows talkWindows, String name) {
        this.talkWindows = talkWindows;
        this.name = name;
    }

    public TalkWindows getTalkWindows() {
        return talkWindows;
    }

    public void setTalkWindows(TalkWindows talkWindows) {
        this.talkWindows = talkWindows;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
