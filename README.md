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
###### Acceptance Testing
- Implemented without testcontainer
###### Integration Testing
###### Unit Testing
- Implemented


### Devops


- [x] Create a CRUD task service with postgresQL
- [x] Devide this into a 3 layerd architecture with multiple modules
- [ ] Create unit, Integration and Acceptance test (with testcontainer)
- [x] Seperate Test dependencies and prod dependencies
- [ ] General error catcher @ExceptionHandler

- [ ] Introduce security (work with an idp / oauth)
- [ ] Create a basic github pipeline which create a container and upload it to docker
