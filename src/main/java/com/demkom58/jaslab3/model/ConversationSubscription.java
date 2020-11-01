package com.demkom58.jaslab3.model;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Objects;

@Entity(name = "conversation_subscriptions")
public class ConversationSubscription implements ObservableEntity, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subscription_id", nullable = false)
    private Integer subscriptionId;

    @ManyToOne
    @JoinColumn(name = "subscriber_id",
            referencedColumnName = "user_id", nullable = false)
    private User subscriber;

    @ManyToOne
    @JoinColumn(name = "conversation_id",
            referencedColumnName = "conversation_id", nullable = false)
    private Conversation conversation;

    @Column(name = "creation_time", nullable = false)
    private Long creationTime = System.currentTimeMillis();

    public ConversationSubscription(Integer subscriptionId, User subscriber,
                                    Conversation conversation, Long creationTime) {
        this.subscriptionId = subscriptionId;
        this.subscriber = subscriber;
        this.conversation = conversation;
        this.creationTime = creationTime;
    }

    public ConversationSubscription(Integer subscriptionId, Integer subscriber,
                                    Integer conversation, Long creationTime) {
        this.subscriptionId = subscriptionId;

        User u = new User();
        u.setUserId(subscriber);
        this.subscriber = u;

        Conversation c = new Conversation();
        c.setConversationId(conversation);
        this.conversation = c;

        this.creationTime = creationTime;
    }

    public ConversationSubscription() {
    }

    @Override
    public Integer getId() {
        return subscriptionId;
    }

    @Override
    public String getDisplayName() {
        return subscriptionId + " (" + conversation.getConversationName() + ")";
    }

    public Integer getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(Integer subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public User getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(User subscriber) {
        this.subscriber = subscriber;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

    public Long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Long creationTime) {
        this.creationTime = creationTime;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConversationSubscription that = (ConversationSubscription) o;
        return Objects.equals(subscriptionId, that.subscriptionId) &&
                Objects.equals(subscriber.getUserId(), that.subscriber.getUserId()) &&
                Objects.equals(conversation.getConversationId(), that.conversation.getConversationId()) &&
                Objects.equals(creationTime, that.creationTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subscriptionId);
    }

    @Override
    public String toString() {
        return "ConversationSubscription{" +
                "subscriptionId=" + subscriptionId +
                ", subscriber=" + subscriber.getUserId() +
                ", conversation=" + conversation.getConversationId() +
                ", creationTime=" + creationTime +
                '}';
    }

    public static ConversationSubscription createEmpty() {
        final User user = new User();
        user.setUserId(0);

        final Conversation conversation = new Conversation();
        conversation.setConversationId(0);

        return new ConversationSubscription(-1, user, conversation, System.currentTimeMillis());
    }

    public static ConversationSubscription create(HttpServletRequest request) {
        final int id = Integer.parseInt(request.getParameter("id"));
        final Integer subscriberId = Integer.parseInt(request.getParameter("subscriber"));
        final Integer conversationId = Integer.parseInt(request.getParameter("conversation"));
        final Long creationTime = Long.parseLong(request.getParameter("creation_time"));
        return new ConversationSubscription(id == -1 ? null : id, subscriberId, conversationId, creationTime);
    }

}
