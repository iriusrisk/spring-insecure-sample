# WORK IN PROGRESS #

# Insecure Sample Web Application #

To get started, please complete the following steps:

- Download and install a MySQL 5.x database from
   http://dev.mysql.com/downloads/mysql/5.0.html#downloads.

- Set the root password in pom.xml, by default the password is "winston"

- Install an SMTP server on localhost port 25, or configure an SMTP server in mail.properties.

- Run <pre>
mvn jetty:run
</pre>
 and view the application at http://localhost:8080.

- Most of the project is standard Maven + Spring + Spring MVC + Spring Security + Hibernate.  Some of the configuration files may be appfuse specific, see:  http://appfuse.org/display/APF/AppFuse+QuickStart

- The initial DB data is contained in src/test/resources/sample-data.xml, you can repopulate the DB by running:

<pre>
 mvn dbunit:operation
</pre>

The Jetty run command will also repopulate the DB.

# Users

admin:password
bob:banana
alice:qwerty

