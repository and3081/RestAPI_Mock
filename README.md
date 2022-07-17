## Тестирование REST API с MockServer http://127.0.0.1:1080
* RestAssured / MockServer
* Java 17 / Maven
* Junit5
* Allure

## запуск всех тестов
mvn clean test

## запуск тестов выборочно по названиям тегов
mvn clean test -Dgroups="перечень через запятую"

## построение отчета Allure
mvn allure:serve

## настройки в классе DataProvider
параметризация тестов

## настройки в проперти:
* mock.properties - настройки url, ip, port, logging

## тест-кейсы:
### ТК1
* Тестирование GET Photo123:
  * GET на endpoint "/api/photos/cats/123/photos"
  * ОР: status 200, body { "images": ["test.jpg"] }
### ТК2
* Тестирование GET Photo456:
  * GET на endpoint "/api/photos/cats/456/photos"
  * ОР: status 400 "Wrong request"
### ТК3
* Тестирование GET по id 123, 456, 9999:
    * GET на endpoint "/api/core/cats/get-by-id" с параметром id
    * для id="123" ОР: status 200
    * для id="456" ОР: status 201
    * для id="9999" ОР: status 400