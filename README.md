# Food Ordering System

A Food Ordering System written in Java and MySQL.

## Requirements

Make sure you have [JDK](https://www.oracle.com/java/technologies/javase-downloads.html) and [MySQL Shell](https://dev.mysql.com/downloads/shell/).

## Database Management

Navigate via the CLI to the **Food Ordering System** directory, then:

- Start a MySQL server:

  MacOS/Linux: `mysqld`

  Windows: `"C:\Program Files\MySQL\MySQL Server 8.0\bin\mysqld" --console`

- In a new CLI tab, execute the database statements: `mysql -u root < db/foodsystem.sql`

- Start a MySQL session: `mysql -u root`

- To stop the server:

  MacOS/Linux: `mysqladmin -u root shutdown`

  Windows: `"C:\Program Files\MySQL\MySQL Server 8.0\bin\mysqladmin" -u root shutdown`

## Compile and Run

Navigate via the CLI to the **Food Ordering System** directory, then:

- To compile: `javac -sourcepath src src/Main.java -d out`

- To run: `java -cp lib/mysql-connector-java-8.0.23.jar:out Main`
