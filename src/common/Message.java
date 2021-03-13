package common;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
    private String mesType;
    private String sender;
    private String receiver;
    private String content;
    private Date sentTime;

    public void setContent(String content) {
        this.content = content;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setSentTime(Date sentTime) {
        this.sentTime = sentTime;
    }

    public Date getSentTime() {
        return sentTime;
    }

    public String getContent() {
        return content;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getSender() {
        return sender;
    }

    public String getMesType() {
        return mesType;
    }

    public void setMesType(String mesType) {
        this.mesType = mesType;
    }
}