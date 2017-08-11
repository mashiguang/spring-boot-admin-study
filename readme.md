trace相关数据保存到数据库，并通过spring-boot-admin展示出来。

保存的数据有：
- timestamp
- time_taken
- status (http-code)
- content (request-header, response-header, parameters, ...)

```
mvn clean package && docker-compose up --build
```