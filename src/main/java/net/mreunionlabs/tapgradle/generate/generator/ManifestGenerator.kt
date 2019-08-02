package net.mreunionlabs.tapgradle.generate.generator

import net.mreunionlabs.tapgradle.generate.GeneratePluginExtension
import java.io.File
import java.io.FileNotFoundException
import java.io.PrintWriter

class ManifestGenerator {

    fun createFile(ext: GeneratePluginExtension) {
        val assetFolder = ext.assetFolder
        if (assetFolder != null) {

            val manifest = File(assetFolder, MANIFEST)

            try {
                PrintWriter(manifest).use { writer ->
                    writer.println("Manifest-Version: 1.0")
                    writer.println("Class-Path: ")
                    writer.close()
                }
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }

        } else {
            System.err.println("MANIFEST GEN: asset folder is null")
        }
    }

    companion object {

        private val MANIFEST = "MANIFEST.MF"
    }
}
