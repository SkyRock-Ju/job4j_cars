package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Car;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class CarRepository {

    private CrudRepository crudRepository;

    public List<Car> findAll() {
        return crudRepository.query("FROM Car car JOIN FETCH car.owner", Car.class);
    }

    public Optional<Car> findById(int id) {
        return crudRepository.optional(
                "FROM Car WHERE id = :id",
                Car.class,
                Map.of("id", id)
        );
    }

    public void save(Car car) {
        crudRepository.run(session -> session.persist(car));
    }

    public void update(Car car) {
        crudRepository.run(session -> session.merge(car));
    }

    public void delete(int id) {
        crudRepository.run(
                "DELETE FROM Car WHERE id = :id",
                Map.of("id", id)
        );
    }
}
