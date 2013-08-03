# WORK IN PROGRESS #

# Basic Spring MVC Archetype created with AppFuse #

The project object model (pom) is defined in the file pom.xml.
The application is ready to run as a web application. The pom.xml file is
pre-defined with Hibernate as a persistence model and Spring MVC as the web
framework.

To get started, please complete the following steps:

- Download and install a MySQL 5.x database from
   http://dev.mysql.com/downloads/mysql/5.0.html#downloads.

- Set the root password in pom.xml

- Install an SMTP server on localhost port 25, or configure an SMTP server in mail.properties.

- Run "mvn jetty:run" and view the application at http://localhost:8080.

- Most of the project is standard Maven + Spring + Spring MVC + Spring Security + Hibernate.  Some of the configuration files may be appfuse specific, see:  http://appfuse.org/display/APF/AppFuse+QuickStart

