# TaskService

This project is meant to create a small monolith with as much clean code and best practices.
At the same time a playing ground for learning new technologies.

### Architecture

The architecture uses a standard 3 layerd architecture with a shared common module.

###### Presentation layer
```
A place for where logic is defined for the outside to interact with the application.
```
###### Application layer
```
A place for cross domain business rules and mappers for converting the domain to dto's and reverse
```
###### Domain layer
```
A place where the domain logic lives
```

###### Common Module
```
This module will includes utils, Pojo and other things which could used in all layers.
purpose is to not redefine common classes in each module and have a place where they can be shared
```


### Testing

###### Architectural Testing
- [ ] Create a test that check that only the DateTimeWrapper is been used

###### Acceptance Testing
- [x] Create an acceptance test with testcontainers
- [ ] Create an acceptance test which test the NotFoundException
###### Integration Testing
- [ ] Create an integration test for testing the pagination querry
###### Unit Testing
- Implemented


### Security
- [ ] all Task endpoint should be secured by a jwt token

### Devops
- [x] Create a pipeline which runs all tests
- [x] which should build a docker image
- [x] publish it to a repository

### Logging
- [ ] introduce ELK stack
- [ ] add Application performance monitoring

### Infrastructure
- [ ] Create a Terraform config for setting up a kubernetes cluster

### Documentation
- [ ] should create openApi yaml file witch will generate controller 
- [ ] should create an endpoint to visualize the documentation