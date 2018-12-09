package com.varun.jugtours.Repository;

import com.varun.jugtours.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

}
