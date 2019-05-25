package com.gtramontina.ghooks.support

import com.gtramontina.ghooks.exec
import org.gradle.api.Project
import java.nio.file.Files.createDirectories
import java.nio.file.Files.createFile
import kotlin.text.RegexOption.DOT_MATCHES_ALL
import kotlin.text.RegexOption.IGNORE_CASE
import kotlin.text.RegexOption.MULTILINE

fun String.toRegexGI() = toRegex(setOf(IGNORE_CASE, DOT_MATCHES_ALL, MULTILINE))
fun Project.initializeGitRepository(): String = "git init".exec(rootDir).get()
fun Project.createCustomHooks(dirname: String, vararg hooks: String) = rootDir
    .resolve(dirname).toPath()
    .let { createDirectories(it) }
    .let { target -> hooks.map { target.resolve(it) }.forEach { createFile(it) } }
