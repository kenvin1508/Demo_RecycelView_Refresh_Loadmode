package vn.edu.vtn.model;

public class Message {
    private int id;
    private String senderName;
    private String contentMessage;

    public Message(int id, String senderName, String contentMessage) {
        this.id = id;
        this.senderName = senderName;
        this.contentMessage = contentMessage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getContentMessage() {
        return contentMessage;
    }

    public void setContentMessage(String contentMessage) {
        this.contentMessage = contentMessage;
    }
}
