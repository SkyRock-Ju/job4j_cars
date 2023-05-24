package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.File;
import ru.job4j.cars.repository.FileRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class FileService {

    private FileRepository fileRepository;

    public void save(File file) {
        fileRepository.save(file);
    }

    public Optional<File> findById(int id) {
        return fileRepository.findById(id);
    }

    public void deleteById(int id) {
        fileRepository.deleteById(id);
    }
}
