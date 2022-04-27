import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    java
    eclipse
    idea
    id("com.diffplug.spotless") version "6.5.0"
    id("com.github.ben-manes.versions") version "0.42.0"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-core:2.13.2")
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.13.2")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.2")

    testImplementation("org.skyscreamer:jsonassert:1.5.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testImplementation("org.assertj:assertj-core:3.22.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")
}

spotless {
    java {
        googleJavaFormat()
    }
    kotlinGradle {
        ktlint()
    }
}

val test by tasks.getting(Test::class) {
    useJUnitPlatform()
    testLogging {
        events = setOf(TestLogEvent.PASSED, TestLogEvent.SKIPPED, TestLogEvent.FAILED)
    }
}
