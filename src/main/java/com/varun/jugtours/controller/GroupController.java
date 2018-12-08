package com.varun.jugtours.controller;

import com.varun.jugtours.Repository.GroupRepository;
import com.varun.jugtours.model.Group;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class GroupController {

    private final Logger log = LoggerFactory.getLogger(GroupController.class);

    private GroupRepository repository;

    public GroupController(GroupRepository groupRepository) {
        this.repository = groupRepository;
    }

    @GetMapping("/groups")
    public Collection<Group> groups() {
        return repository.findAll();
    }

    @GetMapping("/group/{id}")
    public ResponseEntity<?> getGroup(@PathVariable Long id) {

        log.info(" Request to fetch a group with id {} ", id);
        Optional<Group> group = repository.findById(id);
        return group.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/group")
    public ResponseEntity<Group> createGroup(@Valid @RequestBody Group group) throws URISyntaxException {

        log.info("Request to create a group {} ", group);
        Group result = repository.save(group);
        return ResponseEntity.created(new URI("/api/group/" + result.getId())).body(result);
    }

    @PutMapping("/group")
    public ResponseEntity<Group> updateGroup(@Valid @RequestBody Group group) {

        log.info(" Request to update group {} ", group);
        Group result = repository.save(group);
        return ResponseEntity.ok().body(result);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteGroup(@PathVariable Long id) {

        log.info("Request to delete group with id {} ", id);
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
