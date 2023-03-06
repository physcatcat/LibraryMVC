package ru.ivanov.librarymvc.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.ivanov.librarymvc.models.Person;

@Component
public class PersonValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        //TODO: валидация на уникальный номер телефона и почту
    }
}
