package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Owner;
import ru.job4j.cars.repository.OwnerRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OwnerService {
    private OwnerRepository ownerRepository;

    public List<Owner> findAll() {
        return ownerRepository.findAll();
    }

    public Optional<Owner> findById(int id) {
        return ownerRepository.findById(id);
    }

    public void save(Owner owner) {
        ownerRepository.save(owner);
    }

    public void update(Owner owner) {
        ownerRepository.update(owner);
    }

    public void delete(int id) {
        ownerRepository.delete(id);
    }
}
