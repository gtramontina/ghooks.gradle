package com.gtramontina.ghooks

import java.io.File
import java.lang.System.getProperty
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CompletableFuture.supplyAsync
import java.util.concurrent.TimeUnit.SECONDS

private const val SUCCESSFUL = 0

internal fun String.exec(cwd: File, timeoutInSeconds: Long = 60): CompletableFuture<String> = supplyAsync {
    val process = if (getProperty("os.name").startsWith("Windows")) {
        ProcessBuilder("cmd", "/C", this)
    } else {
        ProcessBuilder("/bin/sh", "-c", this)
    }

    process.redirectErrorStream(true)
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
