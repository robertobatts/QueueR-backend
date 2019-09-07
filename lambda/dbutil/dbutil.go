package dbutil

import (
	"fmt"
	"time"
	"database/sql"
	 _ "github.com/go-sql-driver/mysql"
)


type Appointment struct {
	AppointmentID 	int64 			`json:APPOINTMENT_ID`
	StartDate 			time.Time 	`json:START_DATE,omitempty`
	Duration				int					`json:DURATION`
	CancelDate 			time.Time		`json:CANCEL_DATE,omitempty`
	PatientID				int64				`json:PATIENT_ID`
	VisitType				string			`json:VISIT_TYPE,omitempty`
	CanSwitchTo			int64				`json:CAN_SWITCH_TO,omitempty`
}

type Notification struct {
	PhoneNumber string 				`json:PHONE_NUMBER`
	Appointment Appointment		`json:APPOINTMENT`

}

var patientsNotificationQuery = 
	`SELECT CO.PHONE_NUMBER, CANC.START_DATE, CANC.DURATION, APP.APPOINTMENT_ID, CANC.APPOINTMENT_ID FROM APPOINTMENT APP
	JOIN CONTACT CO ON APP.PATIENT_ID = CO.PATIENT_ID
	JOIN (
		SELECT * FROM APPOINTMENT
			WHERE
			CANCEL_DATE IS NOT NULL
		AND START_DATE > CURRENT_TIMESTAMP() + INTERVAL 1 HOUR
	) CANC ON ( 
			DAY(APP.START_DATE) = DAY(CANC.START_DATE) 
			AND APP.PATIENT_ID != CANC.PATIENT_ID
			)
	WHERE 
		APP.CANCEL_DATE IS NULL
	AND APP.START_DATE > CURRENT_TIMESTAMP() + INTERVAL 1 HOUR;`

func GetPatientNotification() []Notification {

	db, err := sql.Open("mysql", "doadmin:buyhpg4cdhnsd3zj@tcp(db-mysql-fra1-72985-do-user-4087706-0.db.ondigitalocean.com:25060)/defaultdb?parseTime=true")

	if err != nil {
		fmt.Println(err.Error())
		panic(err.Error())
	}

	results, err := db.Query(patientsNotificationQuery)

	if err != nil {
		panic(err.Error())
	}

	notifs := make([]Notification, 0)

	if results != nil {
		for results.Next() {
			notif := Notification{}
			app := Appointment{}
			// for each row, scan the result into our tag composite object
			err = results.Scan(&notif.PhoneNumber, &app.StartDate, &app.Duration, &app.AppointmentID, &app.CanSwitchTo)
			if err != nil {
					panic(err.Error()) 
			}
			notif.Appointment = app
			// and then print out the tag's Name attribute
			notifs = append(notifs, notif)
			fmt.Println(notif)
		}
	}
	defer db.Close()

	return notifs
}

var updateSwitchAppointment = `
	UPDATE APPOINTMENT
	SET CAN_SWITCH_TO = ?
	WHERE APPOINTMENT_ID = ?
`

func UpdateAppointment(appointment Appointment) {
	db, err := sql.Open("mysql", "doadmin:buyhpg4cdhnsd3zj@tcp(db-mysql-fra1-72985-do-user-4087706-0.db.ondigitalocean.com:25060)/defaultdb?parseTime=true")

	// if there is an error opening the connection, handle it
	if err != nil {
		fmt.Println(err.Error())
		panic(err.Error())
	}

	_, err = db.Exec(updateSwitchAppointment, appointment.CanSwitchTo, appointment.AppointmentID)

	if err != nil {
		panic(err.Error())
	}
}