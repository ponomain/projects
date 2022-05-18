# Client - server application with microservice architecture. 
## Featured Services:
- card issuer: bank data management service (cards, accounts and related)
- client service: customer data management service (name, surname, email, documents, etc.)
- cloud server: service launching a server on Eureka, our services are registered in it
- notification: service accepts kafka messages

### Services are launched in docker and communicate with each other via kafka and there is an open feing client in the card issuer
