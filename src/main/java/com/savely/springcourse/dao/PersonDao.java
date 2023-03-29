package com.savely.springcourse.dao;

import com.savely.springcourse.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDao {
    private static int PEOPLE_COUNT;
    private final List<Person> people;

    {
        people = new ArrayList<>();
        people.add(new Person(++PEOPLE_COUNT, "Jhon", 20, "jhon@gmail.com"));
        people.add(new Person(++PEOPLE_COUNT, "Marty", 21, "marty@yandex.ru"));
    }

    public List<Person> getPeople() {
        return people;
    }

    public Person getPerson(int id) {
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void savePerson(Person person) {
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

    public void updatePerson(int id, Person person) {
        people.stream().filter(p -> p.getId() == id).findAny().ifPresent(p -> {
            p.setName(person.getName());
            p.setAge(person.getAge());
            p.setEmail(person.getEmail());
        });
    }

    public void deletePerson(int id) {
        people.removeIf(person -> person.getId() == id);
    }
}
