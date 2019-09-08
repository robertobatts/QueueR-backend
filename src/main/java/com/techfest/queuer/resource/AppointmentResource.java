package com.techfest.queuer.resource;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.techfest.queuer.domain.Appointment;

@JsonInclude(Include.NON_NULL)
public class AppointmentResource {
	
	private Long appointmentId;
	private Long startDate;
	private Integer duration;
	private String visitType;
	private Long cancelDate;
	private AppointmentResource switchableAppointment;
	
	public AppointmentResource(Appointment appointment) {
		if (appointment != null) {
			this.appointmentId = appointment.getAppointmentId();
			if (appointment.getStartDate() != null) 
				this.startDate = appointment.getStartDate().getTime();
			this.duration = appointment.getDuration();
			this.visitType = appointment.getVisitType();
			if (appointment.getCancelDate() != null)
				this.cancelDate = appointment.getCancelDate().getTime();
			this.switchableAppointment = new AppointmentResource(appointment.getSwitchableAppointment());
		}
	}


    @JsonProperty("id")
	public Long getAppointmentId() {
		return appointmentId;
	}

	public Long getStartDate() {
		return startDate;
	}

	public Integer getDuration() {
		return duration;
	}

	public String getVisitType() {
		return visitType;
	}

	public Long getCancelDate() {
		return cancelDate;
	}

	public AppointmentResource getSwitchableAppointment() {
		return switchableAppointment;
	}

}
