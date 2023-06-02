package ru.job4j.cars.repository;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.configuration.HibernateConfiguration;
import ru.job4j.cars.model.History;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class HistoryRepositoryTest {

    private static SessionFactory sf;
    private final CrudRepository crudRepository = new CrudRepository(sf);
    private final HistoryRepository historyRepository = new HistoryRepository(crudRepository);

    @BeforeAll
    public static void init() {
        sf = new HibernateConfiguration().sf();

    }

    @AfterEach
    public void delete() {
        for (History history : historyRepository.findAll()) {
            historyRepository.delete(history.getId());
        }
    }

    @Test
    public void saveEngineThenGetSame() {
        History history = new History();
        history.setStartAt(LocalDateTime.now());
        history.setEndAt(LocalDateTime.now());
        historyRepository.save(history);
        assertThat(historyRepository.findById(history.getId()).orElseThrow(), is(history));
    }

    @Test
    public void saveEngineThenUpdateAndGetUpdatedEngine() {
        History history = new History();
        history.setStartAt(LocalDateTime.now());
        history.setEndAt(LocalDateTime.now());
        historyRepository.save(history);
        var updatedDate = LocalDateTime.now();
        history.setStartAt(updatedDate);
        history.setEndAt(updatedDate);
        historyRepository.update(history);
        assertThat(
                historyRepository.findById(
                        history.getId()).orElseThrow().getStartAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS")),
                is(updatedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS")))
        );
    }

    @Test
    public void saveEnginesThenGetListOfThem() {
        History history1 = new History();
        history1.setStartAt(LocalDateTime.now());
        history1.setEndAt(LocalDateTime.now());
        historyRepository.save(history1);

        History history2 = new History();
        history2.setStartAt(LocalDateTime.now());
        history2.setEndAt(LocalDateTime.now());
        historyRepository.save(history2);
        assertThat(historyRepository.findAll().size(), is(2));
    }
}

