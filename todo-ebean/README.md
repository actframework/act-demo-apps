TODO App Demo
==============

A simple TODO Application built on top of ActFramework and Ebean

To run this application 

1. inside the project folder (`~somewhere~/act-demo-apps/fullstack-app/todo-ebean/`) type `mvn clean compile exec:exec`
2. go to your browser and navigate to `http://localhost:5460`.

Note, if you have not specified db configuration the app will launch with a in memory h2 database for testing. And each time the application start the database will be reset.
