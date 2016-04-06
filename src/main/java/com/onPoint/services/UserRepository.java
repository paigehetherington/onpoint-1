package com.onPoint.services;

import com.onPoint.entities.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by vajrayogini on 4/5/16.
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername (String username);
}
