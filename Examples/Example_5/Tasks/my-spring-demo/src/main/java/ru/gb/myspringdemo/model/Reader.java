package ru.gb.myspringdemo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "readers")
@Data
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class Reader {

    public static long sequence = 1L;

    @Id
    private final long id;

    @Column(name = "name")
    private final String name;

    public Reader(String name) {
        this(sequence++, name);
    }
}
