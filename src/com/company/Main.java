package com.company;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Collection<Person> persons = Arrays.asList(
                new Person("Jack", "Evans", 16, Sex.MAN, Education.SECONDARY),
                new Person("Connor", "Young", 23, Sex.MAN, Education.FURTHER),
                new Person("Emily", "Harris", 42, Sex.WOMEN, Education.HIGHER),
                new Person("Harry", "Wilson", 69, Sex.MAN, Education.HIGHER),
                new Person("George", "Davies", 35, Sex.MAN, Education.FURTHER),
                new Person("Samuel", "Adamson", 40, Sex.MAN, Education.HIGHER),
                new Person("John", "Brown", 44, Sex.MAN, Education.HIGHER)
        );
        
        //количество несовершеннолетних
        long count = persons.stream()
                .filter(value -> value.getAge() < 18)
                .count();
        System.out.println("Количество несовершеннолетних в списке: " + count);

        //Список фамилий призывников
        List<String> family = persons.stream()
                .filter(value -> value.getAge() >= 18)
                .filter(value -> value.getSex() == Sex.MAN)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println("Призывники: " + family);

        //Список потенциально работоспособных людей с высшим образованием
        List<Person> list = persons.stream()
                .filter(value -> value.getAge() >= 18)
                .filter(value -> value.getEducation() == Education.HIGHER)
                .filter(value -> (value.getAge() < 65 && value.getSex() == Sex.MAN) || (value.getAge() < 60 && value.getSex() == Sex.WOMEN))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        System.out.println("Потенциально работоспособные люди с высшим образованием:");
        for (Person person : list) System.out.println(person);
    }
}
