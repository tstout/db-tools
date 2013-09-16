Goals
=====

This code is not intended to be some large, complex object relational
mapper. Nor will it replace tools such as liquibase. There may be some
overlap however. The core is focused on only a few aspects of database
interaction:

-   Schema evolution

-   Generating mundane code needed for database I/O

-   DB independence, but only for DDL. It does not attempt to provide an
    object model for queries.

Planned Components
==================

An external DSL that is similar, but not limited to, the typical DDL
supported by most databases. The DSL will provide the metadata needed
for various types of code generation. The code generation will probably
be a combination of textual code gen via StringTemplate and class file
creation with the assistance of cglib, or something similar.
