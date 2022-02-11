package xyz.deftu.hbc

import java.io.File

class FileHandler(
    val gameDirectory: File
) {
    @JvmOverloads fun of(path: String, createIfNotExisting: Boolean = true) = File(gameDirectory, path).also { if (createIfNotExisting && !it.exists() && !it.mkdirs()) throw IllegalStateException("Could not create files. Please contact support!") }
}