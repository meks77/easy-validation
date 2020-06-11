#!/bin/sh
# Requi#ents:
# * available SonarQube environment
# * modification in settings.xml(e.g. for local SonarQube):
#   * in pluginGroups: <pluginGroup>org.sonarsource.scanner.maven</pluginGroup>
#   * in profiles:
#         <profile>
#    		    <id>sonar</id>
#    		    <activation>
#    			    <activeByDefault>true</activeByDefault>
#    		    </activation>
#    	    </profile>
# * maven in  in path
# * JAVA_HOME is set to Java Jdk 1.8+
set JAVA_HOME to jdk 8
mvn -Dmaven.test.failure.ignore=false clean org.jacoco:jacoco-maven-plugin:prepare-agent release:prepare release:perform

set JAVA_HOME to jdk 11
mvn sonar:sonar