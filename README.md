# How to connect to the Docker host machine from within a Docker container?

## Prerequisites

Make sure to have:

* Java 11 [installed](https://sdkman.io/usage)
* Spring CLI [installed](https://docs.spring.io/spring-boot/docs/current/reference/html/getting-started.html#getting-started.installing.cli)
* Docker [installed](https://docs.docker.com/get-docker/)

## Blogpost

Small codebase with blogpost:

## Run the most simple Spring Boot web application

```
spring run helloWorld.groovy
```

A Spring Boot application will start at port 8080 on your host: [http://localhost:8080](http://localhost:8080)

## Start and attach to the Ubuntu container

```
docker run -it --add-host=host.docker.internal:host-gateway ubuntu bash
```

You have to specify the run flag `--add-host=host.docker.internal:host-gateway` (so just executing `docker run -it ubuntu bash`) otherwise your Ubuntu container can't resolve
`host.docker.internal` and you will get an error like:

```
curl: (6) Could not resolve host: host.docker.internal
```

### Install curl (inside the Ubuntu container):

```
apt update && apt install curl -y
```

## Reach to Spring Boot application that is running on the host

```
curl http://host.docker.internal:8080
```

Response:

```
Hello world container. You are able to reach the Docker host!
```

## Docker compose

Example config using Docker compose

```yml
version: "3.8"
services:

  ubuntu:
    image: ubuntu
    container_name: ubuntu
    extra_hosts:
      - "host.docker.internal:host-gateway"
    command: sleep infinity
```

Start the Ubuntu container using Docker compose:

```
docker-compose up -d
```

Attach to the Ubuntu container started using Docker compose:

```
docker exec -it ubuntu bash
```

Shut down the Ubuntu container:

```
docker-compose down -v
```