package com.intellij.idea.plugin.hybris.tools.remote.http

import com.intellij.idea.plugin.hybris.tools.remote.http.impex.HybrisHttpResult
import java.io.File
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.concurrent.TimeUnit

/**
 * @author Nosov Aleksandr <nosovae.dev@gmail.com>
 */
fun monitorImpexFiles(value: Int, unit: TimeUnit, pathToData: String): HybrisHttpResult {
    val resultBuilder = HybrisHttpResult.HybrisHttpResultBuilder.createResult()
    val minutesAgo = LocalDateTime.now().minusMinutes(unit.toMinutes(value.toLong()))
    val out = StringBuilder()
    File(pathToData).walk()
            .filter { file -> file.extension == "bin" }
            .filter { file -> file.lastModified().toLocalDateTime().isAfter(minutesAgo) }
            .sortedBy { it.lastModified() }
            .forEach {
                val header = "# File Path:  ${it.path}\n# file modified: ${it.lastModified().toLocalDateTime()}"
                out.append("\n#" + "-".repeat(header.length - 1) + "\n")
                out.append(header)
                out.append("\n#" + "-".repeat(header.length - 1) + "\n")
                out.append("\n${it.readText()}\n")
            }

    return resultBuilder.httpCode(200)
            .output(out.toString())
            .build()
}

private fun Long.toLocalDateTime() = Instant.ofEpochMilli(this).atZone(ZoneId.systemDefault()).toLocalDateTime()
