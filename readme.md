ABC Banking Tokening System guidelines:

Do checkout the project from github using link https://github.com/jana335637/tokeningsystem.git

It's a spring boot,maven project.

Add the project as Maven Project and do Maven clean,install and Start the project in Run/Debug mode.

Now, API is available for testing.

API exposed:

GET: http://localhost:8080/abc/tokens - gives all tokens counters wise.

GET: http://localhost:8080/abc/queue/tokens - gives unassigned tokens.

GET: http://localhost:8080/abc/tokens/{tokenId} - gives specific token Details, usefull to give deatils to make token to be served.

PUT: http://localhost:8080/abc/tokens/{tokenId} - serves/update the token as per the given body request. It checks 

Remaining below API is for CRUD operations of token and customer.

POST: http://localhost:8080/abc/token - creates token with given details.

DELETE: http://localhost:8080/abc/tokens/{tokenId} - Deletes given token.

GET: http://localhost:8080/abc/customers - gives all customers list

GET: http://localhost:8080/abc/customers/{custId} - gives specific customer details.

GET: http://localhost:8080/abc/customers/{custId}/Type - gives specific customer service Type.

PUT: http://localhost:8080/abc/customers/{custId} - updates specific customer details.

DELETE: http://localhost:8080/abc/customers/{custId} - Deletes given customer.


Implementation details:

For this tokening system, two Singleton Queues(Premium Queue, Regular Queue) are maintained.

When a customer arrives, a token will be generated and if serving counters are availble counter will be assigned otherwise tokens will be enqueued to Premium/Regular queue depending upon the customer service type. When a token is served, then counter will be assigned to token based on the priority.

For testing purpose, 3 counters are taken and initialized with tokens and customers.

Enums are used to accept specific values for Priority Type(PREMIUM/REGULAR) and Status(OPEN/COMPLETED/CANCELLED/FORWARDED), Exceptions are handled with proper messages and HTTP status codes.

Provision is given to add comments, action items or to server the token with PUT API /abc/tokens/{tokenId}.

Multi counter services are provided with Status "FORWARDED": Operator has to provide forwarded counter number as action item in the request body, In this case token will be dequeued and enqueued to forwarded token.

While serving the token few scenarios handled, It checks whether it is a valid token or not that means Is that the first one in the counter queues, If not Error is thrown with status code 400 as Token with id:4 is not valid one to serve.

While updating token or customer, it is checked whether a token or customer exists with the given ID or not, If it doesn't exist it throws Customer/Token not found with id:{Id}.

Junit test cases using Mockito library are added and Integration test is added to check whether a token assigned to counter after serving a token at counter is checked.

