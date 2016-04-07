package com.onPoint.services;

import com.onPoint.entities.Comment;
import org.springframework.data.repository.CrudRepository;


/**
 * Created by vajrayogini on 4/6/16.
 */
public interface CommentRepository extends CrudRepository<Comment, Integer> {
}
