package com.onPoint.services;

import com.onPoint.entities.Volunteer;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by vajrayogini on 4/5/16.
 */
public interface VolunteerRepository extends CrudRepository<Volunteer, Integer> {
}
