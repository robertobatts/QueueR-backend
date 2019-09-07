package main

import (
	"log"
	"github.com/aws/aws-sdk-go/aws"
	"github.com/aws/aws-sdk-go/aws/session"
	"github.com/aws/aws-sdk-go/service/sns"
	"github.com/aws/aws-lambda-go/lambda"
	"dbutil"
)


func Handle() {
	notifications := dbutil.GetPatientNotification()

	if notifications != nil {
		for _, notification := range notifications {
			SendMessage(BuildMessage(notification.Appointment), notification.PhoneNumber)
		}
	}

}

func BuildMessage(appointment dbutil.Appointment) string {
	fmtTime := appointment.StartDate.Format("2006-01-02 15:04:05")
	return "A previously occupied slot is now available at " + fmtTime
}

func SendMessage(message string, phoneNumber string) {
	  //Create a session object to talk to SNS (also make sure you have your key and secret setup in your .aws/credentials file)
		svc := sns.New(session.New())
		// params will be sent to the publish call included here is the bare minimum params to send a message.
		params := &sns.PublishInput{
			Message: aws.String(message), // This is the message itself (can be XML / JSON / Text - anything you want)
			PhoneNumber: aws.String(phoneNumber),  //Get this from the Topic in the AWS console.
		}
	
		resp, err := svc.Publish(params)   //Call to publish the message
	
		if err != nil {                    //Check for errors
			// Print the error, cast err to awserr.Error to get the Code and
			// Message from an error.
			log.Println(err.Error())
			return
		}
		
		// Pretty-print the response data.
		log.Println(resp)
}


func main() {
	lambda.Start(Handle)
}