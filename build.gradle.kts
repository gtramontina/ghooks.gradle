import com.gradle.publish.LoginTask
import com.gradle.publish.PublishTask

group = "com.gtramontina.ghooks.gradle"

repositories {
    jcenter()
}

plugins {
    kotlin("jvm") version "1.3.30"
    id("org.jlleitschuh.gradle.ktlint") version "7.2.1"
    id("com.gradle.plugin-publish") version "0.10.1"
    `java-gradle-plugin`
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
}

pluginBundle {
    website = "https://github.com/gtramontina/ghooks.gradle"
    vcsUrl = "https://github.com/gtramontina/ghooks.gradle"
    tags = listOf("gradle", "plugin", "git", "hook", "ghook", "ghooks", "versioned")
}

gradlePlugin {
    plugins {
        create(rootProject.name) {
            displayName = "ghooks"
            description = "Simple Git hooks"
            id = group.toString()
            implementationClass = "com.gtramontina.ghooks.Main"
        }
    }
}

tasks {
    val checkPublishCredentials by creating {
        group = "plugin portal"
        description = "Checks if your environment has the publishing credentials properly setup."

        val key = System.getProperty("gradle.publish.key", System.getenv("GRADLE_PUBLISH_KEY"))
        val secret = System.getProperty("gradle.publish.secret", System.getenv("GRADLE_PUBLISH_SECRET"))
        doLast {
            if (key.isNullOrBlank()) throw Exception("Could not find either the system property 'gradle.publish.key' or the environment variable 'GRADLE_PUBLISH_KEY'")
            if (secret.isNullOrBlank()) throw Exception("Could not find either the system property 'gradle.publish.secret' or the environment variable 'GRADLE_PUBLISH_SECRET'")
            System.setProperty("gradle.publish.key", key)
            System.setProperty("gradle.publish.secret", secret)
        }

    }
    withType<PublishTask> { dependsOn(checkPublishCredentials) }
    withType<LoginTask> { enabled = false }

    create("publish") {
        group = "plugin portal"
        description = "Alias for 'publishPlugins'"
        dependsOn("publishPlugins")
    }
}
