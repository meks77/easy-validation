/*
 * This file was generated by the Gradle 'init' task.
 */

plugins {
    id("at.meks.easyvalidation.java-conventions")
}

dependencies {
    api(project(":core"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
}

description = "Easy Validation matchers"

java {
    withJavadocJar()
}
