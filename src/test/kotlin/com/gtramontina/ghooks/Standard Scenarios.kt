package com.gtramontina.ghooks

import com.gtramontina.ghooks.support.createCustomHooks
import com.gtramontina.ghooks.support.initializeGitRepository
import io.kotlintest.matchers.collections.containExactlyInAnyOrder
import io.kotlintest.should
import io.kotlintest.shouldBe
import org.awaitility.Awaitility.await
import org.awaitility.Duration.ONE_SECOND
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.nio.file.Files.isSymbolicLink
import java.nio.file.Files.list
import java.nio.file.Files.readSymbolicLink
import java.nio.file.Paths
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
            val hooksDir = project.rootDir.resolve(".git/hooks").toPath()
            val ls = list(hooksDir).collect(toList())
            val installed = ls.map { it.fileName.toString() }

            isSymbolicLink(hooksDir) shouldBe true
            readSymbolicLink(hooksDir) shouldBe Paths.get("../.githooks")
            installed should containExactlyInAnyOrder(customHooks)
        }
    }
}
