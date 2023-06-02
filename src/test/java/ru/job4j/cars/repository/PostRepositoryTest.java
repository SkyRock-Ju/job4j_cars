package ru.job4j.cars.repository;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.configuration.HibernateConfiguration;
import ru.job4j.cars.model.Post;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PostRepositoryTest {

    private static SessionFactory sf;
    private final CrudRepository crudRepository = new CrudRepository(sf);
    private final PostRepository postRepository = new PostRepository(crudRepository);

    @BeforeAll
    public static void init() {
        sf = new HibernateConfiguration().sf();
    }

    @AfterEach
    public void delete() {
        for (Post post : postRepository.findAll()) {
            postRepository.delete(post.getId());
        }
    }

    @Test
    public void saveCarThenGetSame() {
        Post post = new Post();
        post.setDescription("test description");
        post.setCreated(LocalDateTime.now());
        postRepository.save(post);
        assertThat(postRepository.findById(post.getId()).orElseThrow().getDescription(), is(post.getDescription()));
    }

    @Test
    public void saveCarThenUpdateAndGetUpdatedCar() {
        Post post = new Post();
        post.setDescription("test description");
        post.setCreated(LocalDateTime.now());
        postRepository.save(post);
        post.setDescription("updated description");
        postRepository.update(post);
        assertThat(
                postRepository.findById(post.getId()).orElseThrow().getDescription(),
                is("updated description")
        );
    }

    @Test
    public void saveCarsThenGetListOfThem() {
        Post post1 = new Post();
        post1.setDescription("test description1");
        post1.setCreated(LocalDateTime.now());
        postRepository.save(post1);

        Post post2 = new Post();
        post2.setDescription("test description");
        post2.setCreated(LocalDateTime.now());
        postRepository.save(post2);
        assertThat(postRepository.findAll().size(), is(2));
    }
}