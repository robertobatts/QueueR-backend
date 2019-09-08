package com.techfest.queuer.domain;

import java.util.Date;

public class Appointment {
	
	private Long appointmentId;
	private Date startDate;
	private Integer duration;
	private Long patientId;
	private String visitType;
	private Date cancelDate;
	private Long canSwitchTo;
	private Appointment switchableAppointment;
	
	public long getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(long appointmentId) {
		this.appointmentId = appointmentId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	public Long getPatientId() {
		return patientId;
	}
	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}
	public String getVisitType() {
		return visitType;
	}
	public void setVisitType(String visitType) {
		this.visitType = visitType;
	}
	public Date getCancelDate() {
		return cancelDate;
	}
	public void setCancelDate(Date cancelDate) {
		this.cancelDate = cancelDate;
	}
	public Long getCanSwitchTo() {
		return canSwitchTo;
	}
	public void setCanSwitchTo(Long canSwitchTo) {
		this.canSwitchTo = canSwitchTo;
	}
	public Appointment getSwitchableAppointment() {
		return switchableAppointment;
	}
	public void setSwitchableAppointment(Appointment switchableAppointment) {
		this.switchableAppointment = switchableAppointment;
	}
}
