package com.gilberto.test.features.media.photo.utils

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import javax.inject.Inject

class FileManager @Inject constructor(
    private val context: Context,
) {

    fun getRootPath() = File(context.getExternalFilesDir(MAIN_DIRECTORY), "")

    fun getPrivateFileDirectory(dir: String): File? {
        val directory = File(context.getExternalFilesDir(MAIN_DIRECTORY), dir)
        return if (directory.exists() || directory.mkdirs()) {
            directory
        } else {
            context.filesDir
        }
    }

    suspend fun createTemporalFile(directory: String, ext: String): String {
        return withContext(Dispatchers.IO) {
            val temporalFileName = "temporalFile"
            return@withContext File(
                getPrivateFileDirectory(directory),
                "$temporalFileName.$ext",
            ).canonicalPath
        }
    }

    suspend fun listFiles(folderPath: String): List<File> = withContext(Dispatchers.IO) {
        val fileList: MutableList<File> = ArrayList()
        val folder = File(folderPath)
        if (folder.exists() && folder.isDirectory) {
            val files = folder.listFiles()
            if (files != null) {
                for (file in files) {
                    fileList.add(file)
                }
            }
        }
        return@withContext fileList
    }

    fun listFilesInDirectory(directory: File): List<File> {
        val fileList = mutableListOf<File>()
        if (directory.isDirectory) {
            val files = directory.listFiles()
            if (files != null) {
                for (file in files) {
                    if (file.isDirectory) {
                        val subDirectoryFiles = listFilesInDirectory(file)
                        if (subDirectoryFiles.isNotEmpty()) {
                            fileList.addAll(subDirectoryFiles)
                        } else {
                            file.delete() // remove if is empty
                        }
                    } else {
                        fileList.add(file)
                    }
                }
            }
        }
        return fileList
    }

    fun getNestedDirectories(rootDirectory: File, maxLevels: Int): List<File> {
        if (maxLevels <= 0 || !rootDirectory.isDirectory) {
            return emptyList()
        }

        val nestedDirectories = mutableListOf<File>()
        rootDirectory.listFiles { file -> file.isDirectory }?.forEach { subDirectory ->
            nestedDirectories.add(subDirectory)
            nestedDirectories.addAll(getNestedDirectories(subDirectory, maxLevels - 1))
        }

        return nestedDirectories
    }

    fun deleteDirectory(directory: File) {
        directory.deleteRecursively()
    }

    fun deleteFile(file: File): Boolean {
        if (file.exists()) {
            return file.delete()
        }
        return false
    }

    companion object {
        const val MAIN_DIRECTORY = "integra"
        const val INVENTORY_DIRECTORY = "inventory"
        const val FORM_DIRECTORY = "form"
    }
}
