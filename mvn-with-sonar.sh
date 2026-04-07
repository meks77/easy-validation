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
mvn -Dmaven.test.failure.ignore=false test
mvn sonar:sonar