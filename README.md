#Paint Squares

[![Build Status](https://travis-ci.org/zawer-xyz/paint-squares.svg?branch=master)](https://travis-ci.org/zawer-xyz/paint-squares)
[![codecov](https://codecov.io/gh/zawer-xyz/paint-squares/branch/master/graph/badge.svg)](https://codecov.io/gh/zawer-xyz/paint-squares)

The proposed solution uses some key concept like DDD, Event Sourcing, CQRS.

There are three parts on this project

- Command: paint-squares-command-application
  - EventSourcing: EventStore
  - Spring Reactive Web
- Query: paint-squares-query-application
  - Spring Reactive Web
  - Spring MongoDB Reactive
- UI: paint-squares-ui-application
  - Node.js
  - React
  - Feign

Using EventStore and eventSourcing on Command Responsability. Spring Reactive with Mongo DB on Query. And have UI
apart using Feign to access the Query. So you can scale write and read independently

There is a docker-compose with all the images, but run one at a time in the following order, and before run a 
docker-compose build to build the proxy
- mongodb
- event-store
- setup
- paint-squares-command-application-1
- paint-squares-command-application-2
- paint-squares-query-application-1
- paint-squares-query-application-2
- paint-squares-ui-application-1
- paint-squares-ui-application-2
- proxy

There's a Postman to be imported on /docs/api

The dashboard by proxy should be open on localhost:8890

If you want to run a mvn clean install comment the <goal>push</goal> on docker-maven-plugin

Tests limited to an plane from 0..300 and 0..300

