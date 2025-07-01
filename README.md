# bncn technical challenge

## USE CASE
Servicio de prioridad de seleccion de precios

## Install java 21
Set java version
```
/usr/libexec/java_home -v 21 --exec javac -version
echo export "JAVA_HOME=\$(/usr/libexec/java_home -v21)" >> ~/.zshrc
echo $JAVA_HOME
```

## Test
```
mvn test -X
```

## Api endpoint
localhost:8080/api/v1/prices

## Consume Rest api
```
curl --location 'localhost:8080/api/v1/prices?applicationDate=2020-06-15T00%3A00%3A00&productId=35455&brandId=1'
```