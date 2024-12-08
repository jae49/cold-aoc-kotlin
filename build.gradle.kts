plugins {
    // Apply the Kotlin JVM plugin to add support for Kotlin.
    id("org.jetbrains.kotlin.jvm") version "2.1.0"

    // Apply the java-library plugin for API and implementation separation.
    `java-library`
}

group = "com.convexbase"
version = "1.0.2"

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
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")

    // Add Kotlin gradle plugin for dependency export
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin")

    // Add Kotlin Notebook AoC library
    implementation("com.toldoven.aoc:aoc-kotlin-notebook:1.1.2")
    //implementation("io.ktor:ktor-client-core:2.3.13")
    //implementation("io.ktor:ktor-client-core-jvm:3.0.2")

    // Add Arrow library
    implementation(platform("io.arrow-kt:arrow-stack:1.2.4"))
    implementation("io.arrow-kt:arrow-core")
    implementation("io.arrow-kt:arrow-fx-coroutines")

    // Bouncy Castle (crypto library) dependencies
    implementation("org.bouncycastle:bcprov-jdk18on:1.79")

    // Additional Dependencies
    implementation("org.slf4j:slf4j-api:2.0.16")
    implementation("org.slf4j:slf4j-simple:2.0.16")
    // Use the Kotlin test library.
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    // Use the Kotlin JUnit integration.
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.11.3")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    implementation(kotlin("script-runtime"))
}

tasks.test {
    useJUnitPlatform()
}
