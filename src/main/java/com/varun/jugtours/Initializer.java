package com.varun.jugtours;

import com.varun.jugtours.Repository.GroupRepository;
import com.varun.jugtours.model.Event;
import com.varun.jugtours.model.Group;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Collections;
import java.util.stream.Stream;

@Component
public class Initializer implements CommandLineRunner {

    private GroupRepository groupRepository;

    public  Initializer(GroupRepository groupRepository){
        this.groupRepository = groupRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Stream.of("Denver JUG", "Utah JUG", "Seattle JUG",
                "Richmond JUG").forEach(name -> {
                    groupRepository.save(new Group(name));
        });

        Group denverJug = groupRepository.findByName("Denver JUG");
        Event event = Event.builder()
                .title("Full Stack Reactive")
                .description("Reactive with Spring Boot + React")
                .date(Instant.parse("2018-12-12T18:00:00.000Z"))
                .build();
        denverJug.setEvents(Collections.singleton(event));
        groupRepository.save(denverJug);
        groupRepository.findAll().forEach(System.out::println);
    }
}
