package ru.gb.myspringdemo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * Запись о факте выдачи книги (в БД)
 */
@Entity
@Table(name = "issues")
@Data
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class Issue {

    public static long sequence = 1L;

    @Id
    private final long id;

    @Column(name = "book_id")
    private final long bookId;

    @Column(name = "reader_id")
    private final long readerId;

    @Column(name = "issued_at")
    private final LocalDateTime issued_at;  //  Дата выдачи

    @Column(name = "returned_at")
    private LocalDateTime returned_at;      //  Дата возврата

    public Issue(long bookId, long readerId) {
        this.id = sequence++;
        this.bookId = bookId;
        this.readerId = readerId;
        this.issued_at = LocalDateTime.now();
    }
}
