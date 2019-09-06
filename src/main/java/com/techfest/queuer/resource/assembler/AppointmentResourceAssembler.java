package com.techfest.queuer.resource.assembler;

import org.springframework.stereotype.Component;

import com.techfest.queuer.domain.Appointment;
import com.techfest.queuer.resource.AppointmentResource;

@Component
public class AppointmentResourceAssembler extends ResourceAssembler<Appointment, AppointmentResource> {

	@Override
	public AppointmentResource toResource(Appointment appointment) {
		AppointmentResource resource = new AppointmentResource(appointment);
		
		return resource;
	}
	


}
