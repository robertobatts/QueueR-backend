module newslotsnotifier

go 1.12

require (
	dbutil v0.0.0-00010101000000-000000000000
	github.com/aws/aws-lambda-go v1.13.1
	github.com/aws/aws-sdk-go v1.33.0
)

replace dbutil => ./dbutil
