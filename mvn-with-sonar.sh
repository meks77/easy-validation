#!/bin/sh
# Requirements:
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
export JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk-amd64
mvn -Dmaven.test.failure.ignore=false test

export JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64
mvn sonar:sonar