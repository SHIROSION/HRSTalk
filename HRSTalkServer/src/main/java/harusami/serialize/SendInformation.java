package harusami.serialize;
/*
 * @package: harusami.serialize
 * @program: HRSTalk
 * @description
 *
 * @author:  rinne
 * @e-mail:  minami.rinne.me@gmail.com
 * @date: 2019/03/30 下午 07:42
 */

import java.io.Serializable;

/**
 * @classname: SendInformation
 * @description: %{description}
 * @author: rinne
 * @date: 2019/03/30 下午 07:42
 * @Version 1.0
 */
public class SendInformation implements Serializable {
    private String from, to;
    private String information;

    public SendInformation() {
        this.from = null;
        this.to = null;
        this.information = null;
    }

    public SendInformation(String from, String to, String information) {
        this.information = information;
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getInformation() {
        return information;
    }

    public String getTo() {
        return to;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
