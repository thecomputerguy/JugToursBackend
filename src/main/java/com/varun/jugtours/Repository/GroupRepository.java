package com.varun.jugtours.Repository;

import com.varun.jugtours.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {

    Group findByName(String name);
}
