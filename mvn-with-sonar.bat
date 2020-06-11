REM Requirements:
REM * available SonarQube environment
REM * modification in settings.xml(e.g. for local SonarQube):
REM   * in pluginGroups: <pluginGroup>org.sonarsource.scanner.maven</pluginGroup>
REM   * in profiles:
REM         <profile>
REM    		    <id>sonar</id>
REM    		    <activation>
REM    			    <activeByDefault>true</activeByDefault>
REM    		    </activation>
REM    	    </profile>
REM * mvn in path
REM * JAVA_HOME is set to Java Jdk 1.8+
set JAVA_HOME to jdk 8
mvn -Dmaven.test.failure.ignore=false test

set JAVA_HOME to jdk 11
mvn sonar:sonar