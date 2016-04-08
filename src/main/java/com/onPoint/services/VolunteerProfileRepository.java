package com.onPoint.services;

import com.onPoint.entities.User;
import com.onPoint.entities.VolunteerProfile;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by vajrayogini on 4/5/16.
 */
public interface VolunteerProfileRepository extends CrudRepository<VolunteerProfile, Integer> {

}
