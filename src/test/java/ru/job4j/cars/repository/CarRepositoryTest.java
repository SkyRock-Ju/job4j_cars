package ru.job4j.cars.repository;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.configuration.HibernateConfiguration;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.Engine;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CarRepositoryTest {

    private static SessionFactory sf;
    private final CrudRepository crudRepository = new CrudRepository(sf);
    private final EngineRepository engineRepository = new EngineRepository(crudRepository);
    private final CarRepository carRepository = new CarRepository(crudRepository);

    @BeforeAll
    public static void init() {
        sf = new HibernateConfiguration().sf();
    }

    @AfterEach
    public void delete() {
        for (Car car : carRepository.findAll()) {
            carRepository.delete(car.getId());
        }
    }

    @Test
    public void saveCarThenGetSame() {
        Engine engine = new Engine();
        engine.setName("test engine");
        engineRepository.save(engine);
        Car car = new Car();
        car.setName("test car");
        car.setEngine(engine);
        carRepository.save(car);
        assertThat(carRepository.findById(car.getId()).orElseThrow(), is(car));
    }

    @Test
    public void saveCarThenUpdateAndGetUpdatedCar() {
        Engine engine = new Engine();
        engine.setName("test engine");
        engineRepository.save(engine);
        Car car = new Car();
        car.setName("test car");
        car.setEngine(engine);
        carRepository.save(car);
        car.setName("updated name");
        carRepository.update(car);
        assertThat(carRepository.findById(car.getId()).orElseThrow().getName(), is("updated name"));
    }

    @Test
    public void saveCarsThenGetListOfThem() {
        Engine engine1 = new Engine();
        engine1.setName("test engine 2");
        engineRepository.save(engine1);
        Car car1 = new Car();
        car1.setName("test car 2");
        car1.setEngine(engine1);
        carRepository.save(car1);

        Engine engine2 = new Engine();
        engine2.setName("test engine 2");
        engineRepository.save(engine2);
        Car car2 = new Car();
        car2.setName("test car 2");
        car2.setEngine(engine2);
        carRepository.save(car2);
        assertThat(carRepository.findAll().size(), is(2));
    }
}
