package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.History;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HistoryRepository {

    private CrudRepository crudRepository;

    public List<History> findAll() {
        return crudRepository.query("FROM Owner", History.class);
    }

    public Optional<History> findById(int id) {
        return crudRepository.optional(
                "FROM History WHERE id = :id",
                History.class,
                Map.of("id", id)
        );
    }

    public void save(History history) {
        crudRepository.run(session -> session.persist(history));
    }

    public void update(History history) {
        crudRepository.run(session -> session.merge(history));
    }

    public void delete(int id) {
        crudRepository.run(
                "DELETE FROM History WHERE id = :id",
                Map.of("id", id)
        );
    }
}
