package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.hibernate.SessionFactory;
import ru.job4j.cars.model.User;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class UserRepository {
    private final SessionFactory sf;

    /**
     * Сохранить в базе.
     * @param user пользователь.
     * @return пользователь с id.
     */
    public User create(User user) {
        var session = sf.openSession();
        try {
            session.beginTransaction();
            session.persist(user);
            session.getTransaction().commit();
            return user;
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            sf.close();
        }
        return user;
    }

    /**
     * Обновить в базе пользователя.
     * @param user пользователь.
     */
    public void update(User user) {
        var session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery(
                            "UPDATE User SET login = :userLogin, password = :userPassword WHERE id = :userId",
                            User.class)
                    .setParameter("userLogin", user.getLogin())
                    .setParameter("userPassword", user.getPassword())
                    .setParameter("userId", user.getId())
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            sf.close();
        }
    }

    /**
     * Удалить пользователя по id.
     * @param userId ID
     */
    public void delete(int userId) {
        var session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery(
                            "DELETE FROM User WHERE id = :userId", User.class)
                    .setParameter("userId", userId)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            sf.close();
        }
    }

    /**
     * Список пользователь отсортированных по id.
     * @return список пользователей.
     */
    public List<User> findAllOrderById() {
        var session = sf.openSession();
        try {
            session.beginTransaction();
            var users = session.createQuery("FROM User user ORDER BY user.id ASC", User.class).list();
            session.getTransaction().commit();
            return users;
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            sf.close();
        }
        return Collections.emptyList();
    }

    /**
     * Найти пользователя по ID
     * @return пользователь.
     */
    public Optional<User> findById(int userId) {
        var session = sf.openSession();
        try {
            var user = session.createQuery("from User where id = :userId", User.class)
                    .setParameter("userId", userId).uniqueResultOptional();
            session.getTransaction().commit();
            return user;
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            sf.close();
        }
        return Optional.empty();
    }

    /**
     * Список пользователей по login LIKE %key%
     * @param key key
     * @return список пользователей.
     */
    public List<User> findByLikeLogin(String key) {
        var session = sf.openSession();
        try {
            var users = session.createQuery("FROM User user WHERE user.login LIKE :key ", User.class)
                    .setParameter("key", key)
                    .list();
            session.getTransaction().commit();
            return users;
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            sf.close();
        }
        return Collections.emptyList();
    }

    /**
     * Найти пользователя по login.
     * @param login login.
     * @return Optional or user.
     */
    public Optional<User> findByLogin(String login) {
        var session = sf.openSession();
        try {
            var user = session.createQuery("FROM User user WHERE user.login = :login ", User.class)
                    .setParameter("login", login).uniqueResultOptional();
            session.getTransaction().commit();
            return user;
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            sf.close();
        }
        return Optional.empty();
    }
}