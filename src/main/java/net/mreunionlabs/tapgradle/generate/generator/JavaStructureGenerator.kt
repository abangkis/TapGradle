package net.mreunionlabs.tapgradle.generate.generator
import net.mreunionlabs.tapgradle.generate.GeneratePluginExtension
import net.mreunionlabs.tapgradle.generate.util.PackageDirectoryCreator

import java.io.File

class JavaStructureGenerator {

    //TODO validate that java dir parent has been created
    fun createDirectories(ext: GeneratePluginExtension) {
        val f = File(ext.javaDir)
        f.mkdir()

        val packageString = ext.packageString
        val packageRoot = PackageDirectoryCreator.createPackageDir(f, packageString)

        val pages = File(packageRoot, "pages")
        pages.mkdir()

        // TODO: 6/15/2016 make this customable
        val annotations = File(packageRoot, "annotations")
        annotations.mkdir()

        // Todo create layout component by default
        val components = File(packageRoot, "components")
        components.mkdir()

        // todo create package-info.java in this directory
        val compModel = File(packageRoot, "compModel")
        compModel.mkdir()

        val entities = File(packageRoot, "entities")
        entities.mkdir()

    }
}
