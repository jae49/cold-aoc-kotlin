plugins {
    // Apply the Kotlin JVM plugin to add support for Kotlin.
    id("org.jetbrains.kotlin.jvm") version "1.9.21"

    // Apply the java-library plugin for API and implementation separation.
    `java-library`
}

group = "com.convexbase"
version = "1.0.0"

tasks.register<Copy>("copyDependencies") {
    from(configurations["compileClasspath"])
    into("build/libs")
}

repositories {
    mavenCentral()
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "21"
}


dependencies {
    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

    // Use the Kotlin standard library.
    implementation("org.jetbrains.kotlin:kotlin-stdlib")

    // Use Kotlin Coroutines library
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

    // Add Kotlin gradle plugin for dependency export
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin")

    // Bouncy Castle (crypto library) dependencies
    implementation("org.bouncycastle:bcprov-jdk18on:1.77")

    // Additional Dependencies
    implementation("org.slf4j:slf4j-api:2.0.9")
    implementation("org.slf4j:slf4j-simple:2.0.9")
    // Use the Kotlin test library.
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    // Use the Kotlin JUnit integration.
    //testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    implementation(kotlin("script-runtime"))
}

val compileKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "21"
}
val compileTestKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "21"
}


tasks.test {
    useJUnitPlatform()
}
