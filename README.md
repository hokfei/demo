# demo
## Lombok:
This project uses lombok, you will want to have lombok plugin installed in your IDE in order for the errors with @Getter, @Setter, @AllArgsConstructor, and other lombok annotations to stop showing.

## Entity Diagram: 
I went with a many to one relationship between Account and Customer.
![Alt text](src/main/resource/entitydiagram.png "Entity Diagram")

## Security:
As security precaution another microservice would be needed. Let's call it TokenMicroservice. This microservice would validate the credentials of a user and generate a jwt. The token would be signed with my own certificate (created with openssl) and this microservice would use the same certificate to validate the token when the user submits a request.

## Error Handling:
For Error Handling, DemoExceptionHandler will handle all of them. Based on the exception type or message thrown, DemoExceptionHandler will create the appropriate response.
A generic Exception will return a response with HttpStatus 500 with message "error.500".
An EntityNotFoundException will return a response with HttpStatus 404 with message "error.404".
An IllegalArgumentException will return a response with HttpStatus 500. The message will be the exceptions existing message else it will also be "error.500".

## Logging Strategy:
Logs are written when entering each method and when exiting them. This is done in the DemoAspect.java using @Aspect, @PointCut, @Before and @After. 


## API Documentation:
Below is a sample collection in postman of how to call the different endpoints of this demo app.

```json

{
	"info": {
		"_postman_id": "5062a9fb-38ab-4660-b0de-9a8152c9a303",
		"name": "demo",
		"schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json",
		"_exporter_id": "14105766"
	},
	"item": [
		{
			"name": "create customer",
			"request": {
				"method": "POST",
				"header": [],
				"body": {
					"mode": "raw",
					"raw": "{\r\n    \"name\": \"aoeu\"\r\n}",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "http://localhost:8300/haha/customer",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8300",
					"path": [
						"haha",
						"customer"
					]
				}
			},
			"response": []
		},
		{
			"name": "get customer",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "http://localhost:8300/haha/customer?id=6",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8300",
					"path": [
						"haha",
						"customer"
					],
					"query": [
						{
							"key": "id",
							"value": "6"
						}
					]
				}
			},
			"response": []
		},
		{
			"name": "create account",
			"request": {
				"method": "POST",
				"header": [],
				"body": {
					"mode": "raw",
					"raw": "{\r\n    \"customerId\": 1,\r\n    \"type\": \"savings\"\r\n}",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "http://localhost:8300/haha/account",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8300",
					"path": [
						"haha",
						"account"
					]
				}
			},
			"response": []
		},
		{
			"name": "get account",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "http://localhost:8300/haha/account?accountNumber=1",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8300",
					"path": [
						"haha",
						"account"
					],
					"query": [
						{
							"key": "accountNumber",
							"value": "1"
						}
					]
				}
			},
			"response": []
		},
		{
			"name": "deposit",
			"request": {
				"method": "PUT",
				"header": [],
				"url": {
					"raw": "http://localhost:8300/haha/account/1/deposit?amount=123",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8300",
					"path": [
						"haha",
						"account",
						"1",
						"deposit"
					],
					"query": [
						{
							"key": "amount",
							"value": "123"
						}
					]
				}
			},
			"response": []
		},
		{
			"name": "withdraw",
			"request": {
				"method": "PUT",
				"header": [],
				"url": {
					"raw": "http://localhost:8300/haha/account/1/withdraw?amount=123",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8300",
					"path": [
						"haha",
						"account",
						"1",
						"withdraw"
					],
					"query": [
						{
							"key": "amount",
							"value": "123"
						}
					]
				}
			},
			"response": []
		},
		{
			"name": "close account",
			"request": {
				"method": "POST",
				"header": [],
				"url": {
					"raw": "http://localhost:8300/haha/account/1/close",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8300",
					"path": [
						"haha",
						"account",
						"1",
						"close"
					]
				}
			},
			"response": []
		}
	]
}

```

## Large number of transactions:
A load balancer would be needed in this case. If there is a load balancer, multiple instances of this microservice can be running and the load balancer would distribute the transactions coming in accordingly.

## CI/CD:
Whenever a change is made to the source code of this Microservice, Jenkins' pipeline will start by maven build the project. If the build is successful, the job for automated tests will be started. The automated tests could be in Cucumber framework and Gherkin syntax. If the automated tests passed, auto deploy the jar to lower environments. Let QAs test the application then push to UAT. If everything is fine, push to production.



