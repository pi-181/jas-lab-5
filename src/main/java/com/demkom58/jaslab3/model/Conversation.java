package com.demkom58.jaslab3.model;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Objects;

@Entity(name = "conversations")
public class Conversation implements ObservableEntity, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "conversation_id", nullable = false)
    private Integer conversationId;

    @Column(name = "conversation_name", length = 50, nullable = false)
    private String conversationName;

    @Column(nullable = false)
    private Boolean removed = false;

    @Column(nullable = false)
    private Boolean personal = true;

    public Conversation(Integer conversationId, String conversationName, Boolean removed, Boolean personal) {
        this.conversationId = conversationId;
        this.conversationName = conversationName;
        this.removed = removed;
        this.personal = personal;
    }

    public Conversation() {
    }

    @Override
    public Integer getId() {
        return conversationId;
    }

    @Override
    public String getDisplayName() {
        return conversationId + " (" + getConversationName() + ")";
    }

    public Integer getConversationId() {
        return conversationId;
    }

    public void setConversationId(Integer conversationId) {
        this.conversationId = conversationId;
    }

    public String getConversationName() {
        return conversationName;
    }

    public void setConversationName(String conversationName) {
        this.conversationName = conversationName;
    }

    public Boolean isRemoved() {
        return removed;
    }

    public void setRemoved(Boolean removed) {
        this.removed = removed;
    }

    public Boolean isPersonal() {
        return personal;
    }

    public void setPersonal(Boolean personal) {
        this.personal = personal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conversation that = (Conversation) o;
        return Objects.equals(conversationId, that.conversationId) &&
                Objects.equals(conversationName, that.conversationName) &&
                Objects.equals(removed, that.removed) &&
                Objects.equals(personal, that.personal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(conversationId);
    }

    @Override
    public String toString() {
        return "Conversation{" +
                "conversationId=" + conversationId +
                ", conversationName='" + conversationName + '\'' +
                ", removed=" + removed +
                ", personal=" + personal +
                '}';
    }

    public static Conversation createEmpty() {
        return new Conversation(-1, "", false, false);
    }

    public static Conversation create(HttpServletRequest request) {
        final int id = Integer.parseInt(request.getParameter("id"));
        final String groupName = request.getParameter("conversation_name");
        final Boolean removed = Boolean.parseBoolean(request.getParameter("removed"));
        final Boolean personal = Boolean.parseBoolean(request.getParameter("personal"));

        return new Conversation(id == -1 ? null : id, groupName, removed, personal);
    }
}
