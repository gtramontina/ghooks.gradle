package com.gtramontina.ghooks.support

import org.gradle.api.logging.LogLevel
import org.gradle.api.logging.LogLevel.WARN
import org.gradle.internal.logging.events.LogEvent
import org.gradle.internal.logging.slf4j.OutputEventListenerBackedLoggerContext
import org.slf4j.impl.StaticLoggerBinder.getSingleton

internal class TestLogger {
    fun reset() = context.reset()
    val warnings: List<String> get() = entries.getValue(WARN).toList()
    private val context = getSingleton().loggerFactory as OutputEventListenerBackedLoggerContext
    private val entries = with(mutableMapOf<LogLevel, MutableList<String>>()) {
        withDefault { getOrPut(it) { mutableListOf() } }
    }

    init {
        context.setOutputEventListener { it as LogEvent; entries.getValue(it.logLevel!!).add(it.message) }
    }
}
