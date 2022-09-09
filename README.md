# Daily Jokes 
The project tries to provide jokes on a daily basis.

## Requirement
* User can get a daily Joke
* User can get a jokeResponse even if user goes offline
* User can submit a jokeResponse

## External API
This project chose https://sv443.net/jokeapi/v2/ to get jokes after comparing https://jokes.one/api/jokeResponse/#java
and https://icanhazdadjoke.com/api#fetch-a-random-dad-jokeResponse.
### Why it is chosen?
- Has more common sense jokes
- Can generate more than one jokeResponse in a day
- Can send 120 requests per minute
- Total 6 jokeResponse languages: cs, de, en, es, fr, pt

### Technical Stack
- Java 18
- Springboot MVC 
- Relation DB: Mysql8

### Assumption
- JokeAPI.Joke response `id` is the identifier for a joke between local db and JokeAPI. 

## Future Improvement
- User can filter by joke Category
- User can rate a joke
- Authentication