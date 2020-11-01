package com.demkom58.jaslab4.config;

import com.demkom58.jaslab3.model.*;
import com.demkom58.jaslab3.repo.CommonRepository;
import com.demkom58.jaslab3.repo.CrudRepository;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import java.util.Properties;

public final class Database {
    private static Database database;

    private final SessionFactory sessionFactory;
    private final EntityManager entityManager;

    private final CrudRepository<Integer, Conversation> conversationRepository;
    private final CrudRepository<Integer, ConversationSubscription> conversationSubscriptionRepository;
    private final CrudRepository<Integer, Group> groupRepository;
    private final CrudRepository<Integer, GroupSubscription> groupSubscriptionRepository;
    private final CrudRepository<Integer, Message> messageRepository;
    private final CrudRepository<Integer, Post> postRepository;
    private final CrudRepository<Integer, User> userRepository;

    private Database(Configuration configuration) {
        this.sessionFactory = configuration.buildSessionFactory();
        this.entityManager = sessionFactory.createEntityManager();

        this.conversationRepository = new CommonRepository<>(entityManager, "conversations", "conversation_id");
        this.conversationSubscriptionRepository = new CommonRepository<>(entityManager, "conversation_subscriptions", "subscription_id");
        this.groupRepository = new CommonRepository<>(entityManager, "groups", "group_id");
        this.groupSubscriptionRepository = new CommonRepository<>(entityManager, "group_subscriptions", "subscription_id");
        this.messageRepository = new CommonRepository<>(entityManager, "messages", "message_id");
        this.postRepository = new CommonRepository<>(entityManager, "posts", "post_id");
        this.userRepository = new CommonRepository<>(entityManager, "users", "user_id");
    }

    private static Configuration createConfiguration() {
        final String host = "127.0.0.1";
        final String database = "university";
        final String port = "5432";
        final String user = "testuser";
        final String password = "testuser";
        final String schema = "jpatest";

        final Properties properties = new Properties();
        properties.put("hibernate.dialect",
                "org.hibernate.dialect.PostgreSQL10Dialect");
        properties.put("hibernate.connection.driver_class", "org.postgresql.Driver");
        properties.put("hibernate.connection.url",
                "jdbc:postgresql://" + host + ":" + port + "/" + database);
        properties.put("hibernate.default_schema", schema);
        properties.put("hibernate.connection.username", user);
        properties.put("hibernate.connection.password", password);
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.hbm2ddl.auto", "update");

        final Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Conversation.class);
        configuration.addAnnotatedClass(ConversationSubscription.class);
        configuration.addAnnotatedClass(Group.class);
        configuration.addAnnotatedClass(GroupSubscription.class);
        configuration.addAnnotatedClass(Message.class);
        configuration.addAnnotatedClass(Post.class);
        configuration.addAnnotatedClass(User.class);
        configuration.setProperties(properties);

        return configuration;
    }

    public CrudRepository<Integer, Conversation> getConversationRepository() {
        return conversationRepository;
    }

    public CrudRepository<Integer, ConversationSubscription> getConversationSubscriptionRepository() {
        return conversationSubscriptionRepository;
    }

    public CrudRepository<Integer, Group> getGroupRepository() {
        return groupRepository;
    }

    public CrudRepository<Integer, GroupSubscription> getGroupSubscriptionRepository() {
        return groupSubscriptionRepository;
    }

    public CrudRepository<Integer, Message> getMessageRepository() {
        return messageRepository;
    }

    public CrudRepository<Integer, Post> getPostRepository() {
        return postRepository;
    }

    public CrudRepository<Integer, User> getUserRepository() {
        return userRepository;
    }

    public static synchronized Database getDatabase() {
        if (database == null)
            database = new Database(createConfiguration());

        return database;
    }

}
