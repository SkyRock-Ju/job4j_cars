package ru.job4j.cars.repository;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.configuration.HibernateConfiguration;
import ru.job4j.cars.model.Engine;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class EngineRepositoryTest {

    private static SessionFactory sf;
    private final CrudRepository crudRepository = new CrudRepository(sf);
    private final EngineRepository engineRepository = new EngineRepository(crudRepository);

    @BeforeAll
    public static void init() {
        sf = new HibernateConfiguration().sf();

    }

    @AfterEach
    public void delete() {
        for (Engine engine : engineRepository.findAll()) {
            engineRepository.delete(engine.getId());
        }
    }

    @Test
    public void saveEngineThenGetSame() {
        Engine engine = new Engine();
        engine.setName("test engine");
        engineRepository.save(engine);
        assertThat(engineRepository.findById(engine.getId()).orElseThrow(), is(engine));
    }

    @Test
    public void saveEngineThenUpdateAndGetUpdatedEngine() {
        Engine engine = new Engine();
        engine.setName("test engine");
        engineRepository.save(engine);
        engine.setName("updated name");
        engineRepository.update(engine);
        assertThat(engineRepository.findById(engine.getId()).orElseThrow().getName(), is("updated name"));
    }

    @Test
    public void saveEnginesThenGetListOfThem() {
        Engine engine1 = new Engine();
        engine1.setName("test engine 2");
        engineRepository.save(engine1);

        Engine engine2 = new Engine();
        engine2.setName("test engine 2");
        engineRepository.save(engine2);
        assertThat(engineRepository.findAll().size(), is(2));
    }
}
