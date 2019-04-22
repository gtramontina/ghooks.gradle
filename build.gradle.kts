import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

tasks.withType<KotlinCompile> { kotlinOptions.jvmTarget = "1.8" }
group = "com.gtramontina.ghooks.gradle"

repositories {
    mavenCentral()
}

plugins {
    kotlin("jvm") version "1.3.30"
    id("org.jlleitschuh.gradle.ktlint") version "7.2.1"
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
}
