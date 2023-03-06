package ru.ivanov.librarymvc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.ivanov.librarymvc.models.Person;

import java.util.List;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;
    private final PersonMapper personMapper;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate, PersonMapper personMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.personMapper = personMapper;
    }

    public void delete(int id) {
        jdbcTemplate.update("delete from person where id=?", id);
    }

    public void update(int id, Person person) {
        jdbcTemplate.update("update person set full_name=?, date_of_birth=?, email=?, phone_number=? where id=?",
                person.getName(),
                person.getBirthDate(),
                person.getEmail(),
                person.getPhoneNumber(),
                id
        );
    }

    public Person show(int id) {
        return jdbcTemplate.query("select * from person where id=?", personMapper, id)
                .stream()
                .findAny()
                .orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("insert into person(full_name, date_of_birth, phone_number, email) values(?, ?, ?, ?)",
                person.getName(),
                person.getBirthDate(),
                person.getPhoneNumber(),
                person.getEmail()
        );
    }

    public List<Person> index() {
        return jdbcTemplate.query("select * from person", personMapper);
    }
}
