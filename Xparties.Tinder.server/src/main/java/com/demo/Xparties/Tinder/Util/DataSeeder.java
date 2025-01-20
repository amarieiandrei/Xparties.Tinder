package com.demo.Xparties.Tinder.Util;

import com.demo.Xparties.Tinder.Model.Entity.Event;
import com.demo.Xparties.Tinder.Model.Entity.Person;
import com.demo.Xparties.Tinder.Model.Entity.Photo;
import com.demo.Xparties.Tinder.Model.Enums.EventCategory;
import com.demo.Xparties.Tinder.Model.Enums.PersonGender;
import com.demo.Xparties.Tinder.Repository.EventRepository;
import com.demo.Xparties.Tinder.Repository.PersonRepository;
import com.demo.Xparties.Tinder.Repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DataSeeder {

    private final PersonRepository personRepository;
    private final EventRepository eventRepository;
    private final PhotoRepository photoRepository;

    @Bean
    public CommandLineRunner seedDatabase() {
        return args -> {
            createPersons();
//
//            createPhotos();
//
            createEvents();
//
//            assignPhotosToPerson();
//
//            assignPhotoToEvent();
//
//            assignPersonsToEvents();
        };
    }

    private void assignPersonsToEvents() {
        List<Event> events = eventRepository.findAll();
        List<Person> persons = personRepository.findAll();

        // Randomly assign persons to events
        Random random = new Random();

        for (Event event : events) {
            // Randomly decide how many persons will be assigned to this event
            int numberOfPeople = random.nextInt(1, 3);

            for (int i = 0; i < numberOfPeople; i++) {
                Person person = persons.get(random.nextInt(persons.size()));

                // Avoid assigning the same person to the same event more than once
                if (!event.getPersons().contains(person)) {
                    event.getPersons().add(person);
                    person.getEvents().add(event);
                }
            }

            // Save only once after all persons are assigned to the event
            eventRepository.save(event); // Save the event after all the persons have been assigned
        }

        // After updating all events, save all persons at once
        personRepository.saveAll(persons); // Save all persons after being updated
    }

//    private void assignPersonsToEvents() {
//        // I will use the already created relationships between Events table and Persons table to have data inside tables connected
//        // I am focusing to connect data between Events table and Persons table
//        // One event can have multiple persons and one person can participate to multiple events, but not in the same time, so exists a many to many relationships between tables
//
//        List<Event> events = eventRepository.findAll();
//        List<Person> persons = personRepository.findAll();
//
//        Random random = new Random();
//        for (int i = 0; i < 25; i += 1) {
//            Event event = events.get(random.nextInt(events.size()));
//            Person person = persons.get(random.nextInt(persons.size()));
//            event.getPersons().add(person);
//            eventRepository.save(event);
//        }
//    }

    private void assignPhotoToEvent() {
        // I will use the already created relationships between Events table and Photos table to have data inside tables connected
        // I am focusing to connect data between Events table and Photos table
        // One event can have only one photo, so exists a one to one relationship between Events table and Photos table

        List<Event> events = eventRepository.findAll();
        List<Photo> photos = photoRepository.findAll();

        int i = 0;
        for (int j = events.size() - 1; j >= 0; j -= 1) {
            events.get(i).setPhoto(photos.get(j));
            i += 1;
        }
        eventRepository.saveAll(events);
    }

    private void assignPhotosToPerson() {
        // I will use the already created relationships between Persons table and Photos table to have data inside tables connected
        // I am focusing to connect data between Persons table and Photos table
        // One person can have multiple photos, so exists one to many relationship between Persons table and Photos table

        List<Person> persons = personRepository.findAll();
        List<Photo> photos = photoRepository.findAll();

        // Assign photos to person, but not everyone because we need to have photos for events
        Random random = new Random();
        for (int i = photos.size() / 2; i < photos.size(); i += 1) {
                Photo photo = photos.get(i);
                Person person = persons.get(random.nextInt(persons.size()));
                photo.setPerson(person);
                photoRepository.save(photo);
        }
    }


    private void createPersons() {
        createPerson("John", "Doe", "john.doe@example.com", 30, PersonGender.M);
        createPerson("Jane", "Smith", "jane.smith@example.com", 25, PersonGender.F);
        createPerson("Andrei", "Amariei", "andrei.amariei.1116@hotmail.com", 24, PersonGender.M);
        createPerson("Alex-Justin", "Amariei", "amarieialexjustin@gmail.com", 19, PersonGender.M);
        createPerson("Steve", "Dorian", "steve-dorian@gmail.com", 45, PersonGender.M);
        createPerson("Mikey", "Dorian", "mikey.hash@yahoo.com", 60, PersonGender.M);
        createPerson("Stones", "Johny", "stonesjohny@gmail.com", 27, PersonGender.M);
        createPerson("Messi", "Lionel", "messi25@gmail.com", 33, PersonGender.M);
        createPerson("Ronaldo", "Cristiano", "cristiano16@hotmail.com", 37, PersonGender.M);
        createPerson("Maria", "Dan", "dandan25@yahoo.com", 18, PersonGender.F);
    }

    private void createPerson(String firstName, String lastName, String email, int age, PersonGender gender) {
        Person person = new Person();
        person.setExternalId(UUID.randomUUID().toString());
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setEmail(email);
        person.setAge(age);
        person.setGender(gender);
        personRepository.save(person);
    }

    private void createPhotos() {
        createPhoto("PrinceOfPersia.jpg", "image/jpg", new byte[]{});
        createPhoto("DanaStudio.jpg", "image/jpg", new byte[]{});
        createPhoto("Diwali.jpeg", "image/jpeg", new byte[]{});
        createPhoto("Holi_Festival.jpg", "image/jpg", new byte[]{});
        createPhoto("Rio_Carnival.jpeg", "image/jpeg", new byte[]{});
        createPhoto("Oktoberfest.jpg", "image/jpg", new byte[]{});
        createPhoto("Beach_Please.jpeg", "image/jpeg", new byte[]{});
        createPhoto("LittleFestivalTonight.jpeg", "image/jpeg", new byte[]{});
        createPhoto("Sunwaves.jpeg", "image/jpeg", new byte[]{});
        createPhoto("IMG_0157.jpeg", "image/jpeg", new byte[]{});
        createPhoto("photo_xyz_20250115101530.jpeg", "image/jpeg", new byte[]{});
        createPhoto("img_user_upload_14567.jpeg", "image/jpeg", new byte[]{});
        createPhoto("random_pic_76890.jpeg", "image/jpeg", new byte[]{});
        createPhoto("photo_mountain_sunrise.jpg", "image/jpg", new byte[]{});
        createPhoto("capture_2025_01_15.jpeg", "image/jpeg", new byte[]{});
        createPhoto("photo_12345.jpg", "image/jpg", new byte[]{});
        createPhoto("image_20250115_091234.jpg", "image/jpg", new byte[]{});
        createPhoto("snapshot_abcdef.jpeg", "image/jpeg", new byte[]{});
        createPhoto("pic_random456.jpg", "image/jpg", new byte[]{});
        createPhoto("random2221S.jpg", "image/jpg", new byte[]{});
    }

    private void createPhoto(String filename, String contentType, byte[] data) {
        Photo photo = new Photo();
        photo.setExternalId(UUID.randomUUID().toString());
        photo.setFileName(filename);
        photo.setContentType(contentType);
        photo.setData(data);
        photoRepository.save(photo);
    }

    private void createEvents() {
        createEvent("Spring Boot Workshop", randomDate(), EventCategory.TECH);
        createEvent("Angular Camp", randomDate(), EventCategory.TECH);
        createEvent("React Festival", randomDate(), EventCategory.TECH);
        createEvent("Untold", randomDate(), EventCategory.MUSIC);
        createEvent("Beach Please", randomDate(), EventCategory.MUSIC);
        createEvent("Neversea", randomDate(), EventCategory.MUSIC);
        createEvent("Festivalul Muzicii Clasice", randomDate(), EventCategory.MUSIC);
        createEvent("Festivalul Tineretului", randomDate(), EventCategory.MAINSTREAM);
        createEvent("Cupa Romaniei", randomDate(), EventCategory.SPORTS);
        createEvent("Super Liga Programatorilor", randomDate(), EventCategory.SPORTS);
    }

    private void createEvent(String name, Date date, EventCategory category) {
        Event event = new Event();
        event.setExternalId(UUID.randomUUID().toString());
        event.setName(name);
        event.setDate(date);
        event.setCategory(category);
        eventRepository.save(event);
    }

    private Date randomDate() {
        try {

            // Define the range for the random date
            long startEpoch = new Date(0).getTime(); // January 1, 1970
            long endEpoch = new Date().getTime(); // Current time

            // Generate a random timestamp within the range
            long randomTimestamp = ThreadLocalRandom.current().nextLong(startEpoch, endEpoch);

            // Create a Date object from the random timestamp
            Date randomDate = new Date(randomTimestamp);
            return randomDate;

        } catch (Exception e) {
            throw new RuntimeException("Could not generate a random date");
        }
    }
}


