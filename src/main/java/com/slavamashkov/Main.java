package com.slavamashkov;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>(Arrays.asList(
                new Person("John", 35, Person.Position.MANAGER),
                new Person("Bob", 44, Person.Position.DIRECTOR),
                new Person("Kate", 25, Person.Position.ENGINEER),
                new Person("Mary", 42, Person.Position.ENGINEER),
                new Person("Mike", 55, Person.Position.MANAGER),
                new Person("Jack", 19, Person.Position.MANAGER),
                new Person("Mark", 33, Person.Position.ENGINEER),
                new Person("Sam", 37, Person.Position.MANAGER)
        ));

        List<String> engineerNames = persons.stream()
                .filter(person -> person.getPosition() == Person.Position.ENGINEER)
                .sorted(Comparator.comparingInt(Person::getAge))
                .map(Person::getName)
                .collect(Collectors.toList());

        System.out.println(engineerNames);
    }
}
