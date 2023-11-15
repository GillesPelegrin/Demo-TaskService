# TaskService

This project is meant to create a small monolith with as much clean code and best practices.
At the same time a playing ground for learning new technologies. Each decision should be documented in a README.md 


### Architecture

The architecture uses a standard 3 layerd model with a shared common module.

* [Presentation layer](presentation/README.md)  (dependency on Application layer, Common Module)
* [Application layer](application/README.md)  (dependency on Domain layer, Common Module)
* [Domain layer](domain/README.md) (dependency on Common Module)
* [Common Module](common/README.md)

The dependencies are chosen so that the domain and the presentation layer is seperated by an application layer.
This make it's so that anything in the presentation layer can not automatically share a domain entity.


### Testing

Each functionality should be tested at least by one type of test.
Multiple type of test are been written so that most of all flows are tested

* Unit test
* Integration test
* [Acceptance test](presentation/src/test/java/com/example/demo/acceptancetest/README.md)
* Architectural Test

### Devops

* [Pipeline documentation](.github/pipeline.md)
* [K8s deployment documentation](k8s/manifest.md)


---

`Clean up documentation beneath when task are create in github or have been solved`

###### Architectural Testing
- [ ] Create a test that check that only the DateTimeWrapper is been used

###### Acceptance Testing
- [x] Create an acceptance test with testcontainers
- [ ] Create an acceptance test which test the NotFoundException
###### Integration Testing
- [ ] Create an integration test for testing the pagination querry


### Security
- [ ] all Task endpoint should be secured by a jwt token

### Devops
- [x] Create a pipeline which runs all tests
- [x] which should build a docker image
- [x] publish it to a repository
- [ ] add mvn release plugin

### Logging
- [ ] introduce ELK stack
- [ ] add Application performance monitoring

### Infrastructure
- [ ] Create a Terraform config for setting up a kubernetes cluster

### Documentation
- [x] should create openApi yaml file witch will generate controller 
- [ ] should create an endpoint to visualize the documentation


### Errors
- [ ] Make use of OffsetDateTime instead of LocalDateTime + create test to see how we handle dayTimeSaving