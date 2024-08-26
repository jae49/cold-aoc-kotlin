plugins {
    // Apply the Kotlin JVM plugin to add support for Kotlin.
    id("org.jetbrains.kotlin.jvm") version "2.0.20"

    // Apply the java-library plugin for API and implementation separation.
    `java-library`
}

group = "com.convexbase"
version = "1.0.1"

tasks.register<Copy>("copyDependencies") {
    from(configurations["compileClasspath"])
    into("build/libs")
}

repositories {
    mavenCentral()
}


dependencies {
    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

    // Use the Kotlin standard library.
    implementation("org.jetbrains.kotlin:kotlin-stdlib")

    // Use Kotlin Coroutines library
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")

    // Add Kotlin gradle plugin for dependency export
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin")

    // Bouncy Castle (crypto library) dependencies
    implementation("org.bouncycastle:bcprov-jdk18on:1.78.1")

    // Additional Dependencies
    implementation("org.slf4j:slf4j-api:2.0.12")
    implementation("org.slf4j:slf4j-simple:2.0.12")
    // Use the Kotlin test library.
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    // Use the Kotlin JUnit integration.
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.2")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    implementation(kotlin("script-runtime"))
}

tasks.test {
    useJUnitPlatform()
}
