package com.gtramontina.ghooks

import java.io.File
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CompletableFuture.supplyAsync
import java.util.concurrent.TimeUnit.SECONDS

private const val SUCCESSFUL = 0
private val BLANK_SPACES = "\\s".toRegex()

internal fun String.exec(cwd: File, timeoutInSeconds: Long = 60): CompletableFuture<String> = supplyAsync {
    ProcessBuilder(*split(BLANK_SPACES).toTypedArray())
        .redirectErrorStream(true)
        .directory(cwd)
        .start()
        .apply { waitFor(timeoutInSeconds, SECONDS) }
        .let { it.exitValue() to it.inputStream.bufferedReader().readText() }
        .let { (code, output) ->
            when (code) {
                SUCCESSFUL -> output
                else -> throw Error(output)
            }
        }
}
