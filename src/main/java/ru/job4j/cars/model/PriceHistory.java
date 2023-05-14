package ru.job4j.cars.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "price_history")
public class PriceHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    private BigInteger before;

    private BigInteger after;

    private LocalDateTime created;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "price_history_id")
    private List<Post> posts;
}
