# Genetic Algorithm (Travelling Salesman)

A Spring Boot REST service that solves the classic Travelling Salesman Problem with a Genetic Algorithm. Given a set of cities with `(x, y)` coordinates, it evolves a population of candidate routes to find the shortest tour that visits every city once and returns to the origin.

## Introduction

A Genetic Algorithm is a search heuristic inspired by Charles Darwin's theory of natural selection — the "survival of the fittest". The algorithm starts from a random population of routes and iteratively improves them by combining and mutating the best candidates over successive generations.

In the context of this problem:

- **Gene** — a city, represented as `(x, y)` coordinates
- **Individual (chromosome)** — a single route through all cities
- **Population** — a collection of candidate routes
- **Parents** — two routes combined to produce a new route
- **Fitness** — how short a route is (shorter distance = fitter)
- **Mutation** — randomly swapping two cities in a route to introduce variation
- **Elitism** — carrying the best individuals into the next generation

The engine proceeds by: create the population, evaluate fitness, select a mating pool, breed, mutate, and repeat for the configured number of generations.

## Features

- REST endpoint to compute the shortest route for a list of cities
- Configurable population size, generation count, and mutation rate per request
- Returns the best route found along with its fitness/distance

## Tech Stack

| Component | Technology |
|-----------|-----------|
| Language | Java |
| Framework | Spring Boot (spring-boot-starter-web) |
| Packaging | WAR (embedded / external Tomcat) |
| Utilities | Lombok |
| Build | Maven |

## Getting Started

### Prerequisites

- Java (JDK)
- Maven 3.8+

### Build

```bash
mvn clean install
```

### Run

```bash
mvn spring-boot:run
```

The application starts on `http://127.0.0.1:8080` with a context path of `/GA`.

## Configuration

Configuration lives in `src/main/resources/application.properties`:

```properties
server.address=<HOST>
server.port=<PORT>
server.servlet.context-path=/GA
```

## Usage

Send a POST request describing the cities and algorithm parameters.

- **Method:** `POST`
- **URL:** `http://localhost:8080/GA/salesManRoute`

Sample request (8 cities on a symmetric cartesian layout):

```json
{
  "data": {
    "popSize": 100,
    "genSize": 100,
    "mutationRate": 0.05,
    "city": [
      { "name": "A", "x": 10,  "y": 5  },
      { "name": "B", "x": 10,  "y": -5 },
      { "name": "C", "x": 5,   "y": -10 },
      { "name": "D", "x": -5,  "y": -10 },
      { "name": "E", "x": -10, "y": -5 },
      { "name": "F", "x": -10, "y": 5  },
      { "name": "G", "x": -5,  "y": 10 },
      { "name": "H", "x": 5,   "y": 10 }
    ]
  }
}
```

The response contains the best route discovered and its fitness.

## Project Structure

```
src/main/java/com/navneet/
├── GeneticAlgoApplication.java   # Spring Boot entry point
├── ServletInitializer.java       # WAR deployment support
├── controller/GAController.java  # POST /salesManRoute endpoint
├── service/                      # GA orchestration (interface + impl)
├── models/                       # City, Fitness, Request/Response, SalesManRequest
└── utils/CommonUtils.java        # Shared helpers
```

## Reference

- [Genetic algorithm (Wikipedia)](https://en.wikipedia.org/wiki/Genetic_algorithm)
