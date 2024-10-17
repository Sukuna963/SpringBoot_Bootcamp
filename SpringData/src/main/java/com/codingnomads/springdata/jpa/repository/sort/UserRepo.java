package com.codingnomads.springdata.jpa.repository.sort;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

public interface UserRepo extends JpaRepository<User, Long> {
}
