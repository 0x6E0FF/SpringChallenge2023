# SpringChallenge2023 Benchmark

Fork of CodinGame's Spring Challenge 2023 event. https://github.com/CodinGame/SpringChallenge2023

Allow to benchmark a bot against a set of reference bots, with fixed seeds, and for each seed both start positions.

requires :
 - current bot to be compiled as `../bots/current`
 - reference bots compiled in `../bechmark/`

Install and run :
```bash
mvn package && java -jar target/spring-2023-ants-1.0-SNAPSHOT.jar
```
