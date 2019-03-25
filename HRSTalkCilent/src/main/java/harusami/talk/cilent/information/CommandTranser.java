package harusami.talk.cilent.information;
/*
 * @package: harusami.talk.cilent.control
 * @program: HRSTalk
 * @description  ${description}
 *
 * @author:  rinne
 * @e-mail:  minami.rinne.me@gmail.com
 * @date: 2019/03/24 下午 09:47
 */

import java.io.Serializable;

/**
 * @classname: CommandTranser
 * @description: %{description}
 * @author: rinne
 * @date: 2019/03/24 下午 09:47
 * @Version 1.0
 */
public class CommandTranser implements Serializable {

    /**
     * UUID
     */
    private static final long serialVersionUID = 1L;
    /**
     * 发送者
     */
    private String sender = null;
    /**
     * 接受者
     */
    private String receiver = null;
    /**
     * 传递的数据
     */
    private Object data = null;
    /**
     * 指令的处理结果
     */
    private boolean flag = false;
    /**
     * 服务端要做的指令
     */
    private String cmd = null;
    /**
     * 处理结果
     */
    private String result = null;

    public CommandTranser() {
        super();
    }

    public String getSender() {
        return sender;
    }

    public String setSender(String sender) {
        return this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String setReceiver(String receiver) {
        return this.receiver = receiver;
    }

    public Object getData() {
        return data;
    }

    public Object setData(Object data) {
        return this.data = data;
    }

    public boolean isFlag() {
        return flag;
    }

    public boolean setFlag(boolean flag) {
        return this.flag = flag;
    }

    public String getResult() {
        return result;
    }

    public String setResult(String result) {
        return this.result = result;
    }

    public String getCmd() {
        return cmd;
    }

    public String setCmd(String cmd) {
        return this.cmd = cmd;
    }

}
