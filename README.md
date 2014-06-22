# db-tools #
=========
A collection of libraries and utilities for everyday database tasks.
* db.io - A library for reducing boilerplate when dealing with JDBC

## db.io Motivation ##
=====
* Are you frustrated from dealing with a complex ORM? 
* Do you fee dirty when mapping your objects
and tables/views one-to-one? 
* Have you spent way too much time looking
on StackOverflow or stepping through library code to find the elusive incantations 
needed to accomplish what should be simple?
* Are you tired of always having a synthetic key when 
natural keys are a better fit for your model? 
* Do you think foreign keys are a good thing? 
* Are you not afraid of SQL? 

In summary, db.io focuses on letting your JVM-based software control the database with SQL.
db.io can make dealing with JDBC less painful. db.io provides a minimal API. 
There's not much to get between you and your data.

## db.io Features ##
---
* Specify the columns you want via plain old java interfaces. No need to
implement or generate all that getter/setter noise. All the getInt/String/Date etc
on a ResultSet is automatic.

* Need migration support for your schema? Of course you do. A simple migration API is 
provided (backed by liquibase).

## do.io TODO ##
===
* Show some examples!
* Implement named query parameters
* Test with Oracle and possibly other JDBC drivers.


