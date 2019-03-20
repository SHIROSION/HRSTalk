package harusami.talk.cilent.gui;
/*
 * @package: harusami.talk.GUI
 * @program: HRSTalk
 * @description  ${description}
 *
 * @author:  rinne
 * @e-mail:  minami.rinne.me@gmail.com
 * @date: 2019/03/19 上午 10:35
 */

import java.awt.*;

/**
 * @classname: WindowsCentered
 * @description: %{description}
 * @author: rinne
 * @date: 2019/03/19 上午 10:35
 * @Version 1.0
 */
public class WindowsCentered {

    public static Point getXY(int w, int h) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int width = toolkit.getScreenSize().width;
        int height = toolkit.getScreenSize().height;
        return new Point((width - w) / 2, (height - h) / 2);
    }

    public static Point getXY(Dimension dimension) {
        return getXY(dimension.width, dimension.height);
    }

}
