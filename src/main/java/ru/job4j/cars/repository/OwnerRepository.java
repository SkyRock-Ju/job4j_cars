package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Owner;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class OwnerRepository {

    private CrudRepository crudRepository;

    public List<Owner> findAll() {
        return crudRepository.query("FROM Owner", Owner.class);
    }

    public Optional<Owner> findById(int id) {
        return crudRepository.optional(
                "FROM Owner WHERE id = :id",
                Owner.class,
                Map.of("id", id)
        );
    }

    public void save(Owner owner) {
        crudRepository.run(session -> session.persist(owner));
    }

    public void update(Owner owner) {
        crudRepository.run(session -> session.merge(owner));
    }

    public void delete(int id) {
        crudRepository.run(
                "DELETE FROM Owner WHERE id = :id",
                Map.of("id", id)
        );
    }
}
