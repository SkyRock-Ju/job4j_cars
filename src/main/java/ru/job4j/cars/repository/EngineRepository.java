package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Engine;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class EngineRepository {

    private CrudRepository crudRepository;

    public List<Engine> findAll() {
        return crudRepository.query("FROM Engine", Engine.class);
    }

    public Optional<Engine> findById(int id) {
        return crudRepository.optional(
                "FROM Engine WHERE id = :id",
                Engine.class,
                Map.of("id", id)
        );
    }

    public void save(Engine engine) {
        crudRepository.run(session -> session.persist(engine));
    }

    public void update(Engine engine) {
        crudRepository.run(session -> session.merge(engine));
    }

    public void delete(int id) {
        crudRepository.run(
                "DELETE FROM Engine WHERE id = :id",
                Map.of("id", id)
        );
    }
}
