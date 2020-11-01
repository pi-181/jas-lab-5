package com.demkom58.jaslab3.model;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Objects;

@Entity(name = "messages")
public class Message implements ObservableEntity, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id", nullable = false)
    private Integer messageId;

    @ManyToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "user_id", nullable = false)
    private User sender;

    @Column(name = "text_message", nullable = false, length = 5000)
    private String textMessage;

    @Column(nullable = false)
    private Boolean removed;

    @ManyToOne
    @JoinColumn(name = "conversation_id",
            referencedColumnName = "conversation_id", nullable = false)
    private Conversation conversation;

    public Message(Integer messageId, User sender, String textMessage,
                   Boolean removed, Conversation conversation) {
        this.messageId = messageId;
        this.sender = sender;
        this.textMessage = textMessage;
        this.removed = removed;
        this.conversation = conversation;
    }

    public Message() {
    }

    @Override
    public Integer getId() {
        return messageId;
    }

    @Override
    public String getDisplayName() {
        return messageId + " (" + textMessage + ")";
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    public Boolean isRemoved() {
        return removed;
    }

    public void setRemoved(Boolean removed) {
        this.removed = removed;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(messageId, message.messageId) &&
                Objects.equals(sender.getUserId(), message.sender.getUserId()) &&
                Objects.equals(textMessage, message.textMessage) &&
                Objects.equals(removed, message.removed) &&
                Objects.equals(conversation, message.conversation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageId);
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", sender=" + sender.getUserId() +
                ", textMessage='" + textMessage + '\'' +
                ", removed=" + removed +
                ", conversationId=" + conversation +
                '}';
    }

    public static Message createEmpty() {
        final User user = new User();
        user.setUserId(0);

        final Conversation conversation = new Conversation();
        conversation.setConversationId(0);

        return new Message(-1, user, "", false, conversation);
    }

    public static Message create(HttpServletRequest request) {
        System.out.println(request.getParameterMap());
        final int id = Integer.parseInt(request.getParameter("id"));
        final Integer senderId = Integer.parseInt(request.getParameter("sender"));
        final String text = request.getParameter("text");
        final Boolean removed = Boolean.parseBoolean(request.getParameter("removed"));
        final Integer conversationId = Integer.parseInt(request.getParameter("conversation"));

        final User user = new User();
        user.setUserId(senderId);

        final Conversation conversation = new Conversation();
        conversation.setConversationId(conversationId);

        return new Message(id == -1 ? null : id, user, text, removed, conversation);
    }
}
