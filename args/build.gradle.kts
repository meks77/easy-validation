plugins {
    id("at.meks.easyvalidation.java-conventions")
}

dependencies {
    api(project(":matcher"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.assertj:assertj-core:3.24.2")
    testImplementation("org.mockito:mockito-core:5.3.1")
    testImplementation("org.mockito:mockito-junit-jupiter:5.3.1")
    testImplementation("com.tngtech.archunit:archunit:0.14.1")
}

description = "Easy Validation for arguments"

java {
    withJavadocJar()
}
