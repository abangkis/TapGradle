package net.mreunionlabs.tapgradle.generate.util

import net.mreunionlabs.tapgradle.generate.GeneratePluginExtension
import java.io.File

/**
 * Created by abangkis on 6/16/2016.
 */
object PackageDirectoryCreator {
    fun createPackageDir(root: File, packageString: String): File {
        val split = packageString.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

        var packageDir = root
        for (dir in split) {
            if ("" != dir) {
                packageDir = File(packageDir, dir)
                packageDir.mkdir()
            }
        }
        return packageDir
    }

    fun createSubpackageDir(root: File, ext: GeneratePluginExtension, dir: String): File {
        val packageString = ext.packageString
        val packageRoot = PackageDirectoryCreator.createPackageDir(root, packageString)
        val pages = File(packageRoot, dir)
        pages.mkdirs() // fixme we do a lot of reapeted mkdirs on the same path but it's okay for now

        return pages
    }
}
