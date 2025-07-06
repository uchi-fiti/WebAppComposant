Very little web app using Java and jsp on a project about "Composants"
Note: needs to be inside the webapps folder in the tomcat folder 
The version used to test this was 10.1.40
And need to compile with javac -d WEB-INF/classes -cp path/to/tomcat/lib/jakarta.servlet-api-6.0.0.jar src/java/*.java
So you need this in your lib folder of tomcat too: jakarta.servlet-api-6.0.0.jar (download it using wget https://repo1.maven.org/maven2/jakarta/servlet/jakarta.servlet-api/6.0.0/jakarta.servlet-api-6.0.0.jar -P ~/Downloads)
This is not complete at all but hey, I hope I will finish it by Tuesday.
