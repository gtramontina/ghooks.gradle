package com.gtramontina.ghooks

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.apache.commons.io.FileUtils.forceDelete
import java.nio.file.Files.createSymbolicLink
import java.nio.file.Files.exists
import java.nio.file.Files.isDirectory
import java.nio.file.Files.isSymbolicLink
import java.nio.file.Files.readSymbolicLink
import java.nio.file.Path

class GHooks : Plugin<Project> {
    companion object {
        private const val GIT_HOOKS_TARGET = ".githooks"
        private const val GIT_HOOKS_SOURCE = ".git/hooks"
        private const val DESCRIPTION = "Installs the configured Git hooks."
        private const val GROUP = "git hooks"
        private const val TASK_NAME = "installGitHooks"
        private const val GIT_CDUP = "git rev-parse --show-cdup"
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
        project.task(TASK_NAME) { task ->
            task.description = DESCRIPTION
            task.group = GROUP

            val root = project.rootDir
            val target = root.resolve(GIT_HOOKS_TARGET).toPath()

            if (!isDirectory(target))
                task.logger.warn(GIT_HOOKS_TARGET_WARNING.trimIndent()).also { return@task }

            GIT_CDUP.exec(root)
                .thenApply { root.resolve(it.trim()) }
                .thenApply { it.resolve(GIT_HOOKS_SOURCE).toPath() }
                .thenAccept { it.symLinkTo(it.parent.relativize(target)) }
                .exceptionally { task.logger.warn(GIT_WARNING.trimIndent(), it).run { throw it } }
        }
    }

    private fun Path.symLinkTo(target: Path) {
        if (isSymLinkTo(target)) return
        deleteIfExists().also { createSymbolicLink(this, target) }
    }

    private fun Path.isSymLinkTo(target: Path) = exists(this) &&
        isSymbolicLink(this) &&
        readSymbolicLink(this) == target

    private fun Path.deleteIfExists() = forceDelete(toFile())
}
