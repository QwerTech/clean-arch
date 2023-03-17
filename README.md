### This repository is an example of Clean Architecture based on Java using Spring Boot
Clean Architecture is organized into layers that are defined by their responsibility and level of abstraction. The most inner layer, the domain layer, contains the business logic and rules of the system. The outer layers, such as the UI layer and infrastructure layer, deal with input/output and implementation details. These layers are designed to be independent of each other, allowing for changes to one layer without affecting the others.​


To add order via kafka you need to push message in topic ```orders-creation``` with the following header:

```__TypeId__:    org.example.usecases.dto.OrderCreateDto```
