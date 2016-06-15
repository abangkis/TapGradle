package net.mreunionlabs.tapgradle.generate.util;

import java.io.File;

/**
 * Created by abangkis on 6/16/2016.
 */
public class PackageDirectoryCreator {
    public static File createPackageDir(File root, String packageString) {
        String[] split = packageString.split("\\.");

        File packageDir = root;
        for (String dir : split) {
            if (dir != null && !"".equals(dir)) {
                packageDir = new File(packageDir, dir);
                packageDir.mkdir();
            }
        }
        return packageDir;
    }
}
