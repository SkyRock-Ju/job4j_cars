package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class PostRepository {

    private CrudRepository crudRepository;

    public List<Post> findAll() {
        return crudRepository.query("FROM Post", Post.class);
    }

    public Optional<Post> findById(int id) {
        return crudRepository.optional(
                "FROM Post WHERE id = :id",
                Post.class,
                Map.of("id", id)
        );
    }

    public List<Post> findNewPosts() {
        return crudRepository.query(
                "FROM Post WHERE created > :date",
                Post.class,
                Map.of("date", LocalDateTime.now().minusDays(1))
        );
    }

    public List<Post> findPostsWithPhoto() {
        return crudRepository.query(
                "FROM Post WHERE file_id NOT NULL",
                Post.class
        );
    }

    public List<Post> findPostsByName(String name) {
        return crudRepository.query(
                "FROM Post WHERE name LIKE :name",
                Post.class,
                Map.of("name", "%" + name + "%")
        );
    }

    public void save(Post post) {
        crudRepository.run(session -> session.persist(post));
    }

    public void update(Post post) {
        crudRepository.run(session -> session.merge(post));
    }

    public void delete(int id) {
        crudRepository.run(
                "DELETE FROM Post WHERE id = :id",
                Map.of("id", id)
        );
    }
}
