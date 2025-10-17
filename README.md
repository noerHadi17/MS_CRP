# CRP Service

Java 17 Spring Boot service for questionnaires and risk profiles.

- Port: 8082
- Package: `com.wms.crp`
- DB: `jdbc:postgresql://localhost:5432/wms` (postgres/postgress)
- Kafka: `localhost:29092` (produces `audit.events` and `crp.riskprofile.updated`)

## Run

`mvn -DskipTests spring-boot:run`

## cURL

- Get questions

```
curl -s http://localhost:8082/v1/crp/questions
```

- Validate answers

```
curl -s http://localhost:8082/v1/crp/answers/validate -H 'Content-Type: application/json' -H 'X-User-Id: <UUID>' -d '{"answers":[{"questionId":"<QID>","answerId":"<AID>"}]}'
```

- Save answers

```
curl -s http://localhost:8082/v1/crp/answers/save -H 'Content-Type: application/json' -H 'X-User-Id: <UUID>' -d '{"answers":[{"questionId":"<QID>","answerId":"<AID>"}]}'
```

