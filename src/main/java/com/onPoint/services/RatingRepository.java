package com.onPoint.services;

import com.onPoint.entities.Rating;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by vajrayogini on 4/12/16.
 */
public interface RatingRepository extends CrudRepository<Rating, Integer> {
}
