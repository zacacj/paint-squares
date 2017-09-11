#Vitta Challenge

[![Build Status](https://travis-ci.org/zacacj/vitta-challenge.svg?branch=master)](https://travis-ci.org/zacacj/vitta-challenge)
[![codecov](https://codecov.io/gh/zacacj/vitta-challenge/branch/master/graph/badge.svg)](https://codecov.io/gh/zacacj/vitta-challenge)

The proposed solution uses some key concept like DDD, Event Sourcing, CQRS.

There are three parts on this project

- Command: vitta-challenge-command-application
  - EventSourcing: EventStore
  - Spring Reactive Web
- Query: vitta-challenge-query-application
  - Spring Reactive Web
  - Spring MongoDB Reactive
- UI: vitta-challenge-ui-application
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
- vitta-challenge-command-application-1
- vitta-challenge-command-application-2
- vitta-challenge-query-application-1
- vitta-challenge-query-application-2
- vitta-challenge-ui-application-1
- vitta-challenge-ui-application-2
- proxy

There's a Postman to be imported on /docs/api

The dashboard by proxy should be open on localhost:8890

If you want to run a mvn clean install comment the <goal>push</goal> on docker-maven-plugin

Tests limited to an plane from 0..300 and 0..300
