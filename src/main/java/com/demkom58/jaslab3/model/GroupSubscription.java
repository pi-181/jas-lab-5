package com.demkom58.jaslab3.model;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Objects;

@Entity(name = "group_subscriptions")
public class GroupSubscription implements ObservableEntity, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subscription_id", nullable = false)
    private Integer subscriptionId;

    @ManyToOne
    @JoinColumn(name = "subscriber_id", referencedColumnName = "user_id", nullable = false)
    private User subscriber;

    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "group_id", nullable = false)
    private Group group;

    @Column(name = "creation_time", nullable = false)
    private Long creationTime;

    @Column(nullable = false)
    private Boolean accepted = false;

    public GroupSubscription(Integer subscriptionId, User subscriber, Group group,
                             Long creationTime, Boolean accepted) {
        this.subscriptionId = subscriptionId;
        this.subscriber = subscriber;
        this.group = group;
        this.creationTime = creationTime;
        this.accepted = accepted;
    }

    public GroupSubscription() {
    }

    @Override
    public Integer getId() {
        return subscriptionId;
    }

    @Override
    public String getDisplayName() {
        return subscriptionId + " (" + group.getGroupName() + ")";
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Long creationTime) {
        this.creationTime = creationTime;
    }

    public Boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupSubscription that = (GroupSubscription) o;
        return Objects.equals(subscriptionId, that.subscriptionId) &&
                Objects.equals(subscriber.getUserId(), that.subscriber.getUserId()) &&
                Objects.equals(group.getGroupId(), that.group.getGroupId()) &&
                Objects.equals(creationTime, that.creationTime) &&
                Objects.equals(accepted, that.accepted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subscriptionId);
    }

    @Override
    public String toString() {
        return "GroupSubscription{" +
                "subscriptionId=" + subscriptionId +
                ", subscriber=" + subscriber.getUserId() +
                ", group=" + group.getGroupId() +
                ", creationTime=" + creationTime +
                ", accepted=" + accepted +
                '}';
    }

    public static GroupSubscription createEmpty() {
        final User user = new User();
        user.setUserId(0);

        final Group group = new Group();
        group.setGroupId(0);

        return new GroupSubscription(-1, user, group, System.currentTimeMillis(), false);
    }

    public static GroupSubscription create(HttpServletRequest request) {
        System.out.println(request.getParameterMap());
        final int id = Integer.parseInt(request.getParameter("id"));
        final Integer subscriberId = Integer.parseInt(request.getParameter("subscriber"));
        final Integer groupId = Integer.parseInt(request.getParameter("group"));
        final Long creationTime = Long.parseLong(request.getParameter("creation_time"));
        final Boolean accepted = Boolean.parseBoolean(request.getParameter("accepted"));

        final User user = new User();
        user.setUserId(subscriberId);

        final Group group = new Group();
        group.setGroupId(groupId);

        return new GroupSubscription(id == -1 ? null : id, user, group, creationTime, accepted);
    }

}
