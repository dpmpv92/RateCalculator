# RateCalculator 
This is a simple application that given rate windows can find a rate for specfic timeframe

## Starting application
After you clone down this application you can start it very simply by using the gradle wrapper

For windows 
```
gradlew.bat tomcatRun
```
For linux/mac
```
./gradlew tomcatRun
```
## API

This Application supports both XML and JSON 

To send a JSON request you must set the Content-Type header to application/json

To receive a JOSN response you should set the Accept header to application/json

To send a XML request you must set the Content-Type header to application/xml

To receive a XML response must should set the Accept header to application/xml

### Creating rate ranges
First step is to create a rate range inorder to compare against

This can be done by sending a PUT to http://localhost:8080/RateCalculator/api/v1/rates
JSON example:
```json
{
  "rates": [
    {
      "days": "mon,tues,wed,thurs,fri",
      "times": "0600-1800",
      "price": 1500
    },
    {
      "days": "sat,sun",
      "times": "0600-2000",
      "price": 2000
    }
  ]
}
```
XML example:
```xml
<rates>
	<rate>
    	<days>mon,tues,wed,thurs,fri</days>
    	<times>0600-1800</times>
    	<price>1500</price>
    </rate>
    <rate>
    	<days>sat,sun</days>
    	<times>0600-2000</times>
    	<price>2000</price>
    </rate>
</rates>
```
days: is a comma(,) separated day of week aberration: mon,tues,wed,thurs,fri,sat,sun
times: is a hyphen(-) separated 24hour time format start end range
price: is a integer value

### find rate for time range
After you created your rate ranges you can find out what the rate is for that time window

this can be done by sending a GET to http://localhost:8080/RateCalculator/api/v1/rates with a query paramater of a from and to date 

example: http://localhost:8080/RateCalculator/api/v1/rates?from=2015-07-01T12:00:00.000Z&to=2015-07-01T18:00:00.000Z

Note: the dates must be in the full ISO format of yyyy-MM-dd'T'HH:mm:ss.SSSZ

sample JSON response:
```json
{
    "rate": 1500,
    "rateFound": true
}
```
sample xml response:
```xml
<rate>
    <rate>1500</rate>
    <rateFound>true</rateFound>
</rate>
```

rate: is the intger rate value

rateFound: this is a flag that tells if a rate was found or not
