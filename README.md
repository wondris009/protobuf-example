# Protobuf demo in kotlin (based on [Baeldung's example in java](https://www.baeldung.com/spring-rest-api-with-protocol-buffers))

Creates own dummy repository as bean where we hard coded several students and their phone numbers. Hitting GET endpoint serialize there data using protobuf.

Gradle on its own uses protobuf plugin. During "build" phase it builds java representation of protobuf definition, which can be then used to generate data.

