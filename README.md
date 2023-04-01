# Lowsec

## Supported vulnerability types:

XSS、SQL inject、ShellUpload、SSRF、CSRF、XXE、CommandExecute、Deserialization、PseudoRandom


## Install:

1. Download source code:
   Git clone https://github.com/60dimension/lowsec.git.

2. Configure MySQL database:
   Create a database instance and import the table file: `./lowsec/tools/seckeep.sql`.

3. Connect to the database:
   Edit the `./lowsec/src/main/resources/config/mysql.xml` file; set the username and password of MySQL.

4. Launch project:
   Find the Maven panel in the main interface of the IntelliJ IDE -- Plugins -- tomcat7, and run Double click to launch.

5. Access:
   http://localhost:8080/index



## SQL inject

01: Vulnerability Hazards

02: Causes of Vulnerability

03: Black box detection

04: White box detection

05: Security code

Updating, pleas wait!!!