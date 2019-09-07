package com.techfest.queuer.resource;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.techfest.queuer.domain.Appointment;

public class AppointmentResource {

	@JsonUnwrapped
	private Appointment appointment;
	
	public AppointmentResource(Appointment appointment) {
		this.appointment = appointment;
	}

}
