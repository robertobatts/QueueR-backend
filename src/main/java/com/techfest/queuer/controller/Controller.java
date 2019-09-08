package com.techfest.queuer.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techfest.queuer.domain.Appointment;
import com.techfest.queuer.persistence.AppointmentMapper;
import com.techfest.queuer.resource.AppointmentResource;
import com.techfest.queuer.resource.assembler.AppointmentResourceAssembler;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/queuer", produces = "application/json")
public class Controller {
	 	
	@Autowired
	AppointmentResourceAssembler assembler;
	
	@Resource(name = "appointmentMapper")
	private AppointmentMapper appointmentMapper;
	
    @RequestMapping(value = "/mybookings", method = RequestMethod.GET)
    public ResponseEntity<Collection<AppointmentResource>> getBookings(@RequestParam(name = "id") String patientId) {
        List<Appointment> apps = appointmentMapper.getPatientBookings(patientId);
        
        for (Appointment app : apps) {
        	if (app.getCanSwitchTo() != null) {
        		app.setSwitchableAppointment(appointmentMapper.getAppointmentById(app.getCanSwitchTo()));
        	}
         }
        
        return new ResponseEntity<>(assembler.toResourceCollection(apps), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/cancel", method = RequestMethod.GET)
    public ResponseEntity<AppointmentResource> cancelBooking(@RequestParam(name = "id") long appointmentId) {
        appointmentMapper.cancelAppointment(appointmentId);
        return new ResponseEntity<>(assembler.toResource(null), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/switch", method = RequestMethod.GET)
    public ResponseEntity<AppointmentResource> cancelBooking(@RequestParam(name = "id") long appointmentId, @RequestParam(name = "switchId") long switchId) {
        Date newDate = appointmentMapper.getDate(switchId);
    	appointmentMapper.changeDate(appointmentId, newDate);
        return new ResponseEntity<>(assembler.toResource(null), HttpStatus.OK);
    }

}
