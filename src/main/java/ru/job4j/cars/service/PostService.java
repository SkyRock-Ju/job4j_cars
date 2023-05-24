package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.repository.PostRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PostService {

    private PostRepository postRepository;

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Optional<Post> findById(int id) {
        return postRepository.findById(id);
    }

    public List<Post> findNewPosts() {
        return postRepository.findNewPosts();
    }

    public List<Post> findPostsWithPhoto() {
        return postRepository.findPostsWithPhoto();
    }

    public List<Post> findPostsByName(String name) {
        return postRepository.findPostsByName(name);
    }

    public void save(Post post) {
        postRepository.save(post);
    }

    public void update(Post post) {
        postRepository.update(post);
    }

    public void delete(int id) {
        postRepository.delete(id);
    }
}
