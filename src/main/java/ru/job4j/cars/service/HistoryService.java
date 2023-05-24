package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.History;
import ru.job4j.cars.repository.HistoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class HistoryService {

    private HistoryRepository historyRepository;

    public List<History> findAll() {
        return historyRepository.findAll();
    }

    public Optional<History> findById(int id) {
        return historyRepository.findById(id);
    }

    public void save(History history) {
        historyRepository.save(history);
    }

    public void update(History history) {
        historyRepository.update(history);
    }

    public void delete(int id) {
        historyRepository.delete(id);
    }
}
