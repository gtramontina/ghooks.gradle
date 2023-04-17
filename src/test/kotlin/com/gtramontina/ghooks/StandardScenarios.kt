package com.gtramontina.ghooks

import com.gtramontina.ghooks.support.createCustomHooks
import com.gtramontina.ghooks.support.initializeGitRepository
import io.kotlintest.matchers.string.shouldContain
import org.awaitility.Awaitility.await
import org.awaitility.Durations.ONE_SECOND
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.io.path.createTempDirectory

class StandardScenarios {
    private lateinit var project: Project
    private var customHooks = mapOf(
        Pair("pre-commit", """echo "ran pre-commit hook!" """),
        Pair("prepare-commit-msg", """echo "ran prepare-commit-msg hook!" """)
    )

    @BeforeEach
    fun `setup project as a git repository`() {
        project = ProjectBuilder
            .builder()
            .withProjectDir(createTempDirectory().toFile().apply { deleteOnExit() })
            .build()
        project.createCustomHooks(".githooks", customHooks)
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
            val output = "git commit --allow-empty -m 'test-commit'".exec(project.rootDir).get()
            output shouldContain "ran pre-commit hook!"
            output shouldContain "ran prepare-commit-msg hook!"
        }
    }
}
