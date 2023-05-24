package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.File;

import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class FileRepository {

    private CrudRepository crudRepository;

    public void save(File file) {
        crudRepository.run(session -> session.persist(file));
    }

    public Optional<File> findById(int id) {
        return crudRepository.optional(
                "FROM File where id = :id",
                File.class,
                Map.of("id", id)
        );
    }

    public void deleteById(int id) {
        crudRepository.run(
                "DELETE FROM File where id = :id",
                Map.of("id", id)
        );
    }
}
