# IMDB GraphQL CRUD (Java + Spring Boot + MongoDB Atlas)

This is a minimal, ready-to-run Spring Boot GraphQL backend for the assignment:
- Database: `imdb`
- Collections: `movies`, `actors`
- GraphQL CRUD for both Movies and Actors
- Movie documents embed `actors` as array of objects (id, name)

## Prereqs
- Java 17+
- Maven 3.9+
- MongoDB Atlas connection string (or local MongoDB)

## Configure MongoDB
Edit: `src/main/resources/application.properties`

Set:
- `spring.data.mongodb.uri=...`
- optional `spring.data.mongodb.database=imdb`

## Run
```bash
mvn spring-boot:run
```

Then open GraphiQL:
- http://localhost:8080/graphiql

GraphQL endpoint:
- http://localhost:8080/graphql

## Example Queries / Mutations

### Get all movies
```graphql
query {
  getAllMovies {
    id title year rating
    actors { id name }
  }
}
```

### Create actor
```graphql
mutation {
  createActor(actor: {id: 5001, name: "Test Actor"}) {
    id name
  }
}
```

### Create movie
```graphql
mutation {
  createMovie(movie: {
    id: 9001,
    title: "Test Movie",
    genres: ["Action","Drama"],
    description: "Demo",
    director: "Someone",
    actors: [{id: 5001, name: "Test Actor"}],
    year: 2024,
    runtime: 120,
    rating: 7.5,
    votes: 12000,
    revenue: 55.2
  }) {
    id title year
    actors { id name }
  }
}
```

### Update movie rating
```graphql
mutation {
  updateMovie(id: 9001, update: {rating: 8.1}) {
    id title rating
  }
}
```

### Delete movie
```graphql
mutation {
  deleteMovie(id: 9001)
}
```

## Notes
- We store an application-level integer `id` field for movies/actors (matches CSV).
- MongoDB `_id` is still present but hidden from GraphQL.
- When creating/updating a movie, the service will upsert actors into the `actors` collection as well.
