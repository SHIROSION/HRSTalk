package harusami.talk.cilent.information;
/*
 * @package: harusami.talk.cilent.information
 * @program: HRSTalk
 * @description  ${description}
 *
 * @author:  rinne
 * @e-mail:  minami.rinne.me@gmail.com
 * @date: 2019/03/20 下午 08:29
 */

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * @classname: FriendNode
 * @description: %{description}
 * @author: rinne
 * @date: 2019/03/20 下午 08:29
 * @Version 1.0
 */
public class FriendNode extends DefaultMutableTreeNode {
    private ImageIcon imageIcon;
    private long userAcoount;
    private String name;
    private int onlie;

    public FriendNode() {

    }

    public FriendNode (String name, int onlie) {
        this.name = name;
        this.onlie = onlie;
    }

    public FriendNode (ImageIcon imageIcon,String name, int onlie, long userAccount) {
        this.imageIcon = imageIcon;
        this.name = name;
        this.onlie = onlie;
        this.userAcoount = userAccount;
    }

    public String getName() {
        return name;
    }

    public long getUserAcoount() {
        return userAcoount;
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    public int getOnlie() {
        return onlie;
    }

    public void setImageIcon(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOnlie(int onlie) {
        this.onlie = onlie;
    }

    public void setUserAcoount(long userAcoount) {
        this.userAcoount = userAcoount;
    }
}
