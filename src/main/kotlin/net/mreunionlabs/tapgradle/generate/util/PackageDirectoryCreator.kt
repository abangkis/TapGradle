package net.mreunionlabs.tapgradle.generate.util

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
}
