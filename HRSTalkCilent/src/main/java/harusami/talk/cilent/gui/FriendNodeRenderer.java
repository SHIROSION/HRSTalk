package harusami.talk.cilent.gui;
/*
 * @package: harusami.talk.cilent.gui
 * @program: HRSTalk
 * @description  ${description}
 *
 * @author:  rinne
 * @e-mail:  minami.rinne.me@gmail.com
 * @date: 2019/03/20 下午 09:24
 */

import harusami.talk.cilent.information.FriendNode;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

/**
 * @classname: FriendNodeRenderer
 * @description: %{description}
 * @author: rinne
 * @date: 2019/03/20 下午 09:24
 * @Version 1.0
 */
public class FriendNodeRenderer extends DefaultTreeCellRenderer {

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel,
                                                  boolean expanded, boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
        //将value转化为节点对象
        FriendNode friendNode = (FriendNode) value;
        //从节点中读取图片并且将图片自适应大小、居中
        ImageIcon icon = new ImageIcon(friendNode.getImageIcon() + "");
        icon.setImage(icon.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        //从节点中读取昵称和是否在线
        String isOnline = "离线";
        String text = "";

        //因为在Label中文本文字不能够通过调用相应的方法进行换行，
        //所以通过使用html的语法对文字进行换行
        if (friendNode.getOnlie() == 0) {
            isOnline = "[离线]";
            text = "<html>" + friendNode.getName() + "<br/>" + isOnline + " <html/>";
        } else if (friendNode.getOnlie() == 1) {
            isOnline = "[在线]";
            text = "<html>" + friendNode.getName()  + "<br/>" + isOnline + " <html/>";
        } else if (friendNode.getOnlie() == 3) {
            text = friendNode.getName() ;
        }

        //设置图片
        setIcon(icon);
        //设置文本
        setText(text);
        //设置图片和文本之间的距离
        setIconTextGap(20);
        return this;
    }
}
