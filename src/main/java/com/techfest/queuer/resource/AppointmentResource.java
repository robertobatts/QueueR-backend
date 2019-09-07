package com.techfest.queuer.resource;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.techfest.queuer.domain.Appointment;

@JsonInclude(Include.NON_NULL)
public class AppointmentResource {
	
	private Long appointmentId;
	private Date startDate;
	private Integer duration;
	private String visitType;
	private Date cancelDate;
	private AppointmentResource switchableAppointment;
	
	public AppointmentResource(Appointment appointment) {
		if (appointment != null) {
			this.appointmentId = appointment.getAppointmentId();
			this.startDate = appointment.getStartDate();
			this.duration = appointment.getDuration();
			this.visitType = appointment.getVisitType();
			this.cancelDate = appointment.getCancelDate();
			this.switchableAppointment = new AppointmentResource(appointment.getSwitchableAppointment());
		}
	}


    @JsonProperty("id")
	public long getAppointmentId() {
		return appointmentId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public int getDuration() {
		return duration;
	}

	public String getVisitType() {
		return visitType;
	}

	public Date getCancelDate() {
		return cancelDate;
	}

	public AppointmentResource getSwitchableAppointment() {
		return switchableAppointment;
	}

}
