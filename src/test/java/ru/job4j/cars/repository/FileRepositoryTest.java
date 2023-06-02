package ru.job4j.cars.repository;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.configuration.HibernateConfiguration;
import ru.job4j.cars.model.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class FileRepositoryTest {

    private static SessionFactory sf;
    private final CrudRepository crudRepository = new CrudRepository(sf);
    private final FileRepository fileRepository = new FileRepository(crudRepository);

    @BeforeAll
    public static void init() {
        sf = new HibernateConfiguration().sf();

    }

    @AfterEach
    public void delete() {
        for (File file : fileRepository.findAll()) {
            fileRepository.deleteById(file.getId());
        }
    }

    @Test
    public void saveEngineThenGetSame() {
        File file = new File();
        file.setName("test name");
        file.setPath("test path");
        fileRepository.save(file);
        assertThat(fileRepository.findById(file.getId()).orElseThrow(), is(file));
    }

    @Test
    public void saveEngineThenUpdateAndGetUpdatedEngine() {
        File file = new File();
        file.setName("test name");
        file.setPath("test path");
        fileRepository.save(file);
        file.setName("updated name");
        file.setPath("updated path");
        fileRepository.update(file);
        assertThat(fileRepository.findById(file.getId()).orElseThrow().getName(), is("updated name"));
    }

    @Test
    public void saveEnginesThenGetListOfThem() {
        File file1 = new File();
        file1.setName("test name1");
        file1.setPath("test path1");
        fileRepository.save(file1);

        File file2 = new File();
        file2.setName("test name2");
        file2.setPath("test path2");
        fileRepository.save(file2);
        assertThat(fileRepository.findAll().size(), is(2));
    }
}