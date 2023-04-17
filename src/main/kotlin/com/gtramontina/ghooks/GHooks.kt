package com.gtramontina.ghooks

import org.gradle.api.Plugin
import org.gradle.api.Project
import java.nio.file.Files.isDirectory

class GHooks : Plugin<Project> {
    companion object {
        private const val GIT_HOOKS_TARGET = ".githooks"
        private const val GIT_CONFIG_HOOKS = "git config core.hooksPath $GIT_HOOKS_TARGET"
        private const val GIT_HOOKS_TARGET_WARNING = """

            Something went wrong while installing your Git hooks.
            Please make sure you have the `$GIT_HOOKS_TARGET` directory on the root of your project.
            Once these conditions are satisfied, this plugin will ensure the hooks get installed.

        """
        private const val GIT_WARNING = """

            Something went wrong while installing your Git hooks.
            Please make sure you have `git` installed and that this is a Git repository.
            Once these conditions are satisfied, this plugin will ensure the hooks get installed.

        """
    }

    override fun apply(project: Project) {
        project.task("installGitHooks") { task ->
            task.description = "Installs the configured Git hooks."
            task.group = "git hooks"

            val root = project.rootDir
            val target = root.resolve(GIT_HOOKS_TARGET).toPath()

            if (!isDirectory(target)) {
                task.logger.warn(GIT_HOOKS_TARGET_WARNING.trimIndent()).also { return@task }
            }

            GIT_CONFIG_HOOKS.exec(root)
                .exceptionally { task.logger.warn(GIT_WARNING.trimIndent(), it).run { throw it } }
        }
    }
}
