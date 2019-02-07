# Genetic Algorithm example using JAVA
This repository is implementation of Genetic Algorithm to solve the "Evolution of a salesman" problem.
Lets say there is a travelling salesman who has to visit different cities daily. He starts from one of the cities and has to travel to each city once and return to the origin city. We have to calculate the shortest route using Genetic algorithm.

# Introduction:
Genetic Algorithm is an algorithm that is inspired from the process of "Natural Selection". The theory of Natural Selection or Darwin's theory of evolution refers to evolution of species and survival of the fittest.

The approach
Let’s start with a few definitions, rephrased in the context of the problem:

1. Gene: a city (represented as (x, y) coordinates)
2. Individual (aka “chromosome”): a single route satisfying the conditions above
3. Population: a collection of possible routes (i.e., collection of individuals)
4. Parents: two routes that are combined to create a new route
5. Mating pool: a collection of parents that are used to create our next population (thus creating the next generation of routes)
6. Fitness: a function that tells us how good each route is (in our case, how short the distance is)
7. Mutation: a way to introduce variation in our population by randomly swapping two cities in a route
8. Elitism: a way to carry the best individuals into the next generation


Our Algorithm will proceed in the following steps:

1. Create the population
2. Determine fitness
3. Select the mating pool
4. Breed
5. Mutate
6. Repeat

Wiki Link: https://en.wikipedia.org/wiki/Genetic_algorithm
