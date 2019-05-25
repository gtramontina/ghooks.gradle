package com.gtramontina.ghooks

import com.gtramontina.ghooks.support.createCustomHooks
import com.gtramontina.ghooks.support.initializeGitRepository
import io.kotlintest.matchers.collections.containExactlyInAnyOrder
import io.kotlintest.should
import org.awaitility.Awaitility.await
import org.awaitility.Duration.ONE_SECOND
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.nio.file.Files.list
import java.util.stream.Collectors.toList

class `Standard Scenarios` {
    private lateinit var project: Project
    private var customHooks = listOf("pre-commit", "prepare-commit-msg")

    @BeforeEach
    fun `setup project as a git repository`() {
        project = ProjectBuilder.builder().build()
        project.createCustomHooks(".githooks", *customHooks.toTypedArray())
        project.initializeGitRepository()
    }

    @Test
    fun `has proper plugin ID`() {
        project.pluginManager.apply("com.gtramontina.ghooks.gradle")
    }

    @Test
    fun `installs the git hooks`() {
        project.pluginManager.apply(GHooks::class.java)

        await().atMost(ONE_SECOND).untilAsserted {
            val ls = list(project.rootDir.resolve(".git/hooks").toPath()).collect(toList())
            val installed = ls.map { it.fileName.toString() }
            installed should containExactlyInAnyOrder(customHooks)
        }
    }
}
