import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    java
    id("com.diffplug.spotless") version "6.23.3"
    id("com.github.ben-manes.versions") version "0.50.0"
}

repositories {
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-core:2.16.0")
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.16.0")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.16.0")

    testImplementation("org.skyscreamer:jsonassert:1.5.1")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.1")
    testImplementation("org.assertj:assertj-core:3.24.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.1")
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
