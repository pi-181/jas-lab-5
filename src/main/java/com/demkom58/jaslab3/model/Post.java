package com.demkom58.jaslab3.model;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

@Entity(name = "posts")
public class Post implements ObservableEntity, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", nullable = false)
    private Integer postId;

    @Column(name = "post_content", length = 5000, nullable = false)
    private String postContent;

    @Column(nullable = false)
    private Integer views = 0;

    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "group_id")
    private Group group;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "user_id")
    private User author;

    @Column(name = "postDate", nullable = false)
    private Long postDate;

    public Post(Integer postId, String postContent, Integer views,
                Group group, User author, Long postDate) {
        this.postId = postId;
        this.postContent = postContent;
        this.views = views;
        this.group = group;
        this.author = author;
        this.postDate = postDate;
    }

    public Post() {
    }

    @Override
    public Integer getId() {
        return postId;
    }

    @Override
    public String getDisplayName() {
        return postId + " (" + postContent + ")";
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroupId(Group group) {
        this.group = group;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Long getPostDate() {
        return postDate;
    }

    public void setPostDate(Long postDate) {
        this.postDate = postDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(postId, post.postId) &&
                Objects.equals(postContent, post.postContent) &&
                Objects.equals(views, post.views) &&
                Objects.equals(group.getGroupId(), post.group.getGroupId()) &&
                Objects.equals(author, post.author) &&
                Objects.equals(postDate, post.postDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId);
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", postContent='" + postContent + '\'' +
                ", views=" + views +
                ", group=" + group.getGroupId() +
                ", author=" + author.getUserId() +
                ", postDate=" + postDate +
                '}';
    }

    public static Post createEmpty() {
        final Group group = new Group();
        group.setGroupId(0);

        final User user = new User();
        user.setUserId(0);

        return new Post(-1, "", 0, group, user, System.currentTimeMillis());
    }

    public static Post create(HttpServletRequest request) {
        System.out.println(request.getParameterMap());
        final int id = Integer.parseInt(request.getParameter("id"));
        final String content = request.getParameter("content");
        final Integer views = Integer.parseInt(request.getParameter("views"));
        final Integer groupId = Integer.parseInt(request.getParameter("group"));
        final Integer authorId = Integer.parseInt(request.getParameter("author"));
        final Long postdate = Long.parseLong(request.getParameter("postdate"));

        final Group group = new Group();
        group.setGroupId(groupId);

        final User user = new User();
        user.setUserId(authorId);

        return new Post(id == -1 ? null : id, content, views, group, user, postdate);
    }
}
