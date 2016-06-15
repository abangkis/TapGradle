package net.mreunionlabs.tapgradle.generate.generator;

import net.mreunionlabs.tapgradle.generate.GeneratePluginExtension;

import java.io.File;

/**
 * Created by abangkis on 6/15/2016.
 */
public class JavaStructureGenerator {

    //TODO validate that java dir parent has been created
    public void createDirectories(GeneratePluginExtension ext) {
        File f = new File(ext.getJavaDir());
        f.mkdir();

        String packageString = ext.getPackageString();
        String[] split = packageString.split("\\.");

        File packageRoot = f;
        for (String dir : split) {
            if (dir != null && !"".equals(dir)) {
                packageRoot = new File(packageRoot, dir);
                packageRoot.mkdir();
            }
        }

        File pages = new File(packageRoot, "pages");
        pages.mkdir();

        // TODO: 6/15/2016 make this customable
        File annotations = new File(packageRoot, "annotations");
        annotations.mkdir();

        File components = new File(packageRoot, "components");
        components.mkdir();

        File entities = new File(packageRoot, "entities");
        entities.mkdir();

    }
}
