# ActFramework HelloWorld JAX-RS Demo

This application demonstrates how to use write a HelloWorld application using actframework with JAX-RS plugin

## Start the application

Start the application in dev mode

```
mvn clean compile exec:exec
```

Start the application in prod mode

```
mvn clean package
cd target/dist
unzip *
./start
```

Once application has been started, you can open browser and locate to `http://localhost:5460` get the home page.


