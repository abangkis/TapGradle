package net.mreunionlabs.tapgradle.generate.generator

import net.mreunionlabs.tapgradle.generate.GeneratePluginExtension
import net.mreunionlabs.tapgradle.generate.util.PackageDirectoryCreator

import java.io.File

/**
 * Created by abangkis on 3/19/2016.
 */
class StructureGenerator {

    fun createDirectories(ext: GeneratePluginExtension) {
        var f = File(ext.javaDir)
        println("java dir ${f.absolutePath}")
        f.mkdir()
        createResourceDir(ext)
        f = File(ext.webAppDir)
        f.mkdir()
    }

    private fun createResourceDir(ext: GeneratePluginExtension) {
        val metaInf = File(ext.resDir, METAINF)
        metaInf.mkdirs()

        val assets = File(metaInf, "assets")
        assets.mkdir()

        // create css, images and js folder
        File(assets, "css").mkdir()
        File(assets, "images").mkdir()
        File(assets, "js").mkdir()

        ext.assetFolder = assets

        val modules = File(metaInf, "modules")
        modules.mkdir()

        val resDir = File(ext.resDir)
        val packageString = ext.packageString
        val packageRoot = PackageDirectoryCreator.createPackageDir(resDir, packageString)

        val components = File(packageRoot, "components")
        components.mkdir()
        val pages = File(packageRoot, "pages")
        pages.mkdir()

    }

    companion object {
        private val METAINF = "META-INF"
    }
}
