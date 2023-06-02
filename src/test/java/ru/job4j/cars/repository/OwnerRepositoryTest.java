package ru.job4j.cars.repository;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.configuration.HibernateConfiguration;
import ru.job4j.cars.model.Owner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class OwnerRepositoryTest {

    private static SessionFactory sf;
    private final CrudRepository crudRepository = new CrudRepository(sf);
    private final OwnerRepository ownerRepository = new OwnerRepository(crudRepository);

    @BeforeAll
    public static void init() {
        sf = new HibernateConfiguration().sf();

    }

    @AfterEach
    public void delete() {
        for (Owner owner : ownerRepository.findAll()) {
            ownerRepository.delete(owner.getId());
        }
    }

    @Test
    public void saveEngineThenGetSame() {
        Owner owner = new Owner();
        owner.setName("test owner");
        ownerRepository.save(owner);
        assertThat(ownerRepository.findById(owner.getId()).orElseThrow().getName(), is(owner.getName()));
    }

    @Test
    public void saveEngineThenUpdateAndGetUpdatedEngine() {
        Owner owner = new Owner();
        owner.setName("test owner");
        ownerRepository.save(owner);
        owner.setName("updated owner");
        ownerRepository.update(owner);
        assertThat(ownerRepository.findById(owner.getId()).orElseThrow().getName(), is("updated owner"));
    }

    @Test
    public void saveEnginesThenGetListOfThem() {
        Owner owner1 = new Owner();
        owner1.setName("test owner1");
        ownerRepository.save(owner1);

        Owner owner2 = new Owner();
        owner2.setName("test owner2");
        ownerRepository.save(owner2);
        assertThat(ownerRepository.findAll().size(), is(2));
    }
}
