package harusami.talk.GUI;
/*
 * @package: harusami.talk.GUI
 * @program: HRSTalk
 * @description  ${description}
 *
 * @author:  rinne
 * @e-mail:  minami.rinne.me@gmail.com
 * @date: 2019/03/19 上午 09:23
 */

import javax.swing.*;
import java.awt.*;

/**
 * @classname: ImageScale
 * @description: %{description}
 * @author: rinne
 * @date: 2019/03/19 上午 09:23
 * @Version 1.0
 */
public class ImageScale {
    public static ImageIcon getImage(ImageIcon icon, int width, int height){
        Image image = icon.getImage().getScaledInstance(width, height,Image.SCALE_REPLICATE);
        icon.setImage(image);
        return icon;
    }
}
