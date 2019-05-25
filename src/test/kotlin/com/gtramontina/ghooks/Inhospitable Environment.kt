package com.gtramontina.ghooks

import com.gtramontina.ghooks.support.TestLogger
import com.gtramontina.ghooks.support.createCustomHooks
import com.gtramontina.ghooks.support.initializeGitRepository
import com.gtramontina.ghooks.support.toRegexGI
import io.kotlintest.matchers.collections.shouldHaveSize
import io.kotlintest.matchers.string.shouldMatch
import org.awaitility.Awaitility.await
import org.awaitility.Duration.ONE_SECOND
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class `Inhospitable Environment` {
    private lateinit var project: Project
    private lateinit var logger: TestLogger

    @BeforeEach
    fun beforeEach() {
        project = ProjectBuilder.builder().build()
        logger = TestLogger()
    }

    @AfterEach
    fun afterEach() = logger.reset()

    @Test
    fun `warns when not a git repository`() {
        project.createCustomHooks(".githooks")
        project.pluginManager.apply(GHooks::class.java)

        await().atMost(ONE_SECOND).untilAsserted {
            logger.warnings shouldHaveSize 1
            logger.warnings.first() shouldMatch ".*something went wrong.*".toRegexGI()
            logger.warnings.first() shouldMatch ".*that this is a git repository.*".toRegexGI()
        }
    }

    @Test
    fun `warns when the target directory does not exist`() {
        project.initializeGitRepository()
        project.pluginManager.apply(GHooks::class.java)

        await().atMost(ONE_SECOND).untilAsserted {
            logger.warnings shouldHaveSize 1
            logger.warnings.first() shouldMatch ".*something went wrong.*".toRegexGI()
            logger.warnings.first() shouldMatch ".*make sure you have the `.*` directory on the root of your project.*".toRegexGI()
        }
    }
}
