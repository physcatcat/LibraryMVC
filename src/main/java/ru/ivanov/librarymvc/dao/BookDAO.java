package ru.ivanov.librarymvc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.ivanov.librarymvc.models.Book;

import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("select * from book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id) {
        return jdbcTemplate.query("select * from book where id=?", new BeanPropertyRowMapper<>(Book.class), id)
                .stream()
                .findAny()
                .orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update("insert into book(title, author, year) values(?, ?, ?)",
                book.getTitle(),
                book.getAuthor(), //TODO:названия в классе и в таблице Book не сходятся (проблема в BeanPropertyRowMapper)
                book.getYear()
        );
    }

    public void update(int id, Book book) {
        jdbcTemplate.update("update book set title=?, author=?, year=? where id=?",
                book.getTitle(),
                book.getAuthor(),
                book.getYear(),
                id
        );
    }

    public void delete(int id) {
        jdbcTemplate.update("delete from book where id=?", id);
    }
}
