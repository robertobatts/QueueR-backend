package com.techfest.queuer.persistence;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techfest.queuer.domain.Appointment;

@Mapper
@Service("appointmentMapper")
@Transactional
public interface AppointmentMapper {

	  @Select("SELECT APPOINTMENT_ID FROM APPOINTMENTS WHERE PATIENT_ID = #{patientId}")
	  public long findByPatientId(@Param("patientId") String patientId);
}
