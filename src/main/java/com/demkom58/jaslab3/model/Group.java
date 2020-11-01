package com.demkom58.jaslab3.model;

import org.jetbrains.annotations.Nullable;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Objects;

@Entity(name = "groups")
public class Group implements ObservableEntity, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id", nullable = false)
    private Integer groupId;

    @ManyToOne
    @JoinColumn(name = "owner_id",
            referencedColumnName = "user_id", nullable = false)
    private User owner;

    @Column(name = "group_name", length = 40, nullable = false)
    private String groupName;

    @Column(name = "description", length = 255)
    private String description;

    @Column(nullable = false)
    private Boolean closed = false;

    @Column(nullable = false)
    private Boolean removed = false;

    public Group(Integer groupId, User owner, String groupName, String description, Boolean closed, Boolean removed) {
        this.groupId = groupId;
        this.owner = owner;
        this.groupName = groupName;
        this.description = description;
        this.closed = closed;
        this.removed = removed;
    }

    public Group() {
    }

    @Override
    public Integer getId() {
        return groupId;
    }

    @Override
    public String getDisplayName() {
        return groupId + " (" + groupName + ")";
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    public void setDescription(@Nullable String description) {
        this.description = description;
    }

    public Boolean isClosed() {
        return closed;
    }

    public void setClosed(Boolean closed) {
        this.closed = closed;
    }

    public Boolean isRemoved() {
        return removed;
    }

    public void setRemoved(Boolean removed) {
        this.removed = removed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return groupId.equals(group.groupId) &&
                Objects.equals(owner, group.owner) &&
                Objects.equals(groupName, group.groupName) &&
                Objects.equals(description, group.description) &&
                Objects.equals(closed, group.closed) &&
                Objects.equals(removed, group.removed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId);
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupId=" + groupId +
                ", owner=" + owner.getUserId() +
                ", groupName='" + groupName + '\'' +
                ", description='" + description + '\'' +
                ", closed='" + closed + '\'' +
                ", removed='" + removed + '\'' +
                '}';
    }

    public static Group createEmpty() {
        final User user = new User();
        user.setUserId(0);
        return new Group(-1, user, "", "", false, false);
    }

    public static Group create(HttpServletRequest request) {
        System.out.println(request.getParameterMap());
        final int id = Integer.parseInt(request.getParameter("id"));
        final Integer ownerId = Integer.parseInt(request.getParameter("owner"));
        final String groupName = request.getParameter("group_name");
        final String description = request.getParameter("description");
        final Boolean closed = Boolean.parseBoolean(request.getParameter("closed"));
        final Boolean removed = Boolean.parseBoolean(request.getParameter("removed"));

        final User user = new User();
        user.setUserId(ownerId);

        return new Group(id == -1 ? null : id, user, groupName, description, closed, removed);
    }

}
