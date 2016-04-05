package com.onPoint.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by vajrayogini on 4/5/16.
 */
@RestController
public class OnPointController {

    @Autowired
    VolunteerRepository volunteers;


}
