package com.techfest.queuer.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techfest.queuer.domain.Appointment;
import com.techfest.queuer.resource.AppointmentResource;
import com.techfest.queuer.resource.assembler.AppointmentResourceAssembler;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/queue", produces = "application/json")
public class QueueController {
	
	@Autowired
	AppointmentResourceAssembler assembler;
	
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<AppointmentResource>> findAllOrders() {
        List<Appointment> apps = new ArrayList<Appointment>();
        Appointment app = new Appointment();
        app.setTimestamp(new Date());
        apps.add(app);
        return new ResponseEntity<>(assembler.toResourceCollection(apps), HttpStatus.OK);
    }

}
