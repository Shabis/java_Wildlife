# Wildlife Tracker
------

In this application we are creating a system for tracking animals. A ranger has the ability to go in an mark an animal as being spotted and to report where it was seen and its guesstimated age and health.

<br/>

### Setup/Installation Instructions
------
![Databases] (database_setup.png)

* In PSQL:
  * CREATE DATABASE wildlife_tracker;
  * CREATE TABLE animals (id serial PRIMARY KEY, name varchar, endangered boolean, health varchar, int age);
  * CREATE TABLE sightings (id serial PRIMARY KEY, animalid int, time timestamp, location varchar, ranger varchar);
* In command prompt enter: git clone https://github.com/Shabis/java_Wildlife.git
* In command prompt enter: cd java_Wildlife-Tracker
* In command prompt enter: gradle run
* In web browser navigate to: http://localhost:4567


<br/>

### Known Bugs
------

No known bugs.

<br/>

### Specifications
------

* As a user, you will be able to add an animal name to the database for later use in sightings.
* As a user, you will be able to add an endangered name, age and health into the database for later use in sightings.
* As a user, you will be able to add a sighting of an animal or an endangered animal with its location, the rangers name, and the animal from the database with was seen.
* As a user, if an item is left blank when trying to add an animal, endangered animal or a sighting an exception will be thrown prompting the user to add that all items need to be included to save.

### Support and Contact Details
------

If you have any questions, please contact me at Shelby_Clayton@hotmail.com.

<br/>

### Technology Used
------

In this project I used java, gradle, GitHub, Atom, velocity, Spark, PostgreSQL, junit and Markdown.

<br/>


Copyright (c) 2016 **_Shelby Clayton_**
