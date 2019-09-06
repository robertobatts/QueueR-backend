package main

import (
	"log"
	"github.com/aws/aws-sdk-go/aws"
	"github.com/aws/aws-sdk-go/aws/session"
	"github.com/aws/aws-sdk-go/service/sns"
	"github.com/aws/aws-lambda-go/lambda"
)

func Handle() {
  //Create a session object to talk to SNS (also make sure you have your key and secret setup in your .aws/credentials file)
	svc := sns.New(session.New())
  // params will be sent to the publish call included here is the bare minimum params to send a message.
	params := &sns.PublishInput{
		Message: aws.String("message"), // This is the message itself (can be XML / JSON / Text - anything you want)
		PhoneNumber: aws.String("+393381188800"),  //Get this from the Topic in the AWS console.
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