# db-tools 

A collection of libraries and utilities for everyday database tasks.

* db.io - A library for reducing boilerplate when dealing with JDBC
* other libraries to come...

#db.io Motivation 

* Are you comfortable writing SQL? 
* Are you frustrated with a complex ORM? 
* Have you spent way too much time looking
on StackOverflow or stepping through library code to find the elusive incantations 
needed to accomplish what should be simple?

In summary, db.io focuses on letting your JVM-based software manipulate the database with SQL.
db.io can make dealing with JDBC less painful. db.io provides a minimal API. 
There's not much to get between you and your data. SQL is the DSL, not db-io.

#db.io Features 

* Specify the columns you want a query to return via plain old java interfaces. No need to
implement or generate all that getter/setter java-bean noise. You don't even need to 
annotate the plain old java interface.
* All the getInt, getString, getDate, etc. on a jdbc ResultSet is automatic.

* Need migration support for your schema? Of course you do. A naive migration API is 
provided (currently a trivial liquibase wrapper).

#db.io Caveats

* I'm not happy with the update/insert interface.
* Performance is not a goal at the moment. Vanilla java.lang.reflect.Proxy is used to
map column metadata to an inteface.
* Only an H2 client is bundled at the moment. Other databases should not be a major effort, provided you 
implement a couple of interfaces.

 
#do.io TODO 

* Show some examples! (There are are tests, but that is no excuse for proper documentation)
* Implement named query parameters
* Test with Oracle and possibly other JDBC drivers.


