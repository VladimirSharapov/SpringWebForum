Project that demonstrates usage of Java technologies to create web forum.
Currently it lacks many features, but the purpose was just to demonstrate
my knowledge of Java technology stack.

Technologies used in the project:

Front-end: JSP, HTML, CSS, JQuery, Twitter bootstrap, Sitemesh( create templates )

Back-end:  Spring (Ioc, MVC, AOP) 4.2.4, Spring Security 3.2.3, 
           Hibernate 5.0.7, Hibernate Validator 5.3.0.Alpha1,  
           SLF4J + Log4j, Joda
		   
Application server: Tomcat

Database: H2, MySQL, Oracle (forum can be run on any database that is supported by liquibase)

Tools: git, maven 3 and nexus repository, liquibase (db utility) ,jenkins

Test: JUnit, Mockito, Spring test, Unitils, h2 in memory db.

// Ehcache