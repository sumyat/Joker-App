# Daily Jokes 
The project tries to provide jokes on a daily basis.

## Requirement
* User can get a daily Joke
* User can get a jokeResponse even if user goes offline
* User can submit a jokeResponse

### Diagram
![](../../../../var/folders/w8/w7h08w_j72qd8x456k34l7b80000gn/T/TemporaryItems/NSIRD_screencaptureui_llz4bc/Screen Shot 2022-09-09 at 12.37.08.png)

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