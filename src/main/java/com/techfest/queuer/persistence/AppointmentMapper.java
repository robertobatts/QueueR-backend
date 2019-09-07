package com.techfest.queuer.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techfest.queuer.domain.Appointment;

@Mapper
@Service("appointmentMapper")
@Transactional
public interface AppointmentMapper {

	@Results(value = {
		      @Result(property = "appointmentId", column = "APPOINTMENT_ID"),
		      @Result(property = "startDate", column = "START_DATE"),
		      @Result(property = "patientId", column="PATIENT_ID"),
		      @Result(property = "visitType", column="VISIT_TYPE"),
		      @Result(property = "cancelDate", column="CANCEL_DATE")
		    })
	@Select("SELECT * FROM APPOINTMENT WHERE PATIENT_ID = #{patientId} ORDER BY START_DATE DESC")
	public List<Appointment> getPatientBookings(@Param("patientId") String patientId);
	
	@Results(value = {
		      @Result(property = "appointmentId", column = "APPOINTMENT_ID"),
		      @Result(property = "startDate", column = "START_DATE"),
		      @Result(property = "patientId", column="PATIENT_ID"),
		      @Result(property = "visitType", column="VISIT_TYPE"),
		      @Result(property = "cancelDate", column="CANCEL_DATE")
		    })
	@Select("SELECT * FROM APPOINTMENT WHERE APPOINTMENT_ID = #{appointmentId}")
	public Appointment getAppointmentById(@Param("appointmentId") String appointmentId);
}
