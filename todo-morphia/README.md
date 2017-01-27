TODO App Demo
==============

A simple TODO Application built on top of ActFramework and MongoDB

**To run this application in dev mode:**

`mvn clean compile exec:exec`

**To run this application in prod mode:**

```bash
mvn clean package
cd target/dist
unzip *.zip
./start
``` 

Go to your browser and navigate to `http://localhost:5460`.

Note, you need [MongoDB service](http://mongodb.org) running up before starting this application
