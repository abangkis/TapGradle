package net.mreunionlabs.tapgradle.generate.generator;

import net.mreunionlabs.tapgradle.generate.GeneratePluginExtension;
import net.mreunionlabs.tapgradle.generate.util.PackageDirectoryCreator;

import java.io.File;

/**
 * Created by abangkis on 3/19/2016.
 */
public class StructureGenerator {
    private static final String METAINF = "META-INF";

    public void createDirectories(GeneratePluginExtension ext) {
        File f = new File(ext.getJavaDir());
        f.mkdir();
        createResourceDir(ext);
        f = new File(ext.getWebAppDir());
        f.mkdir();
    }

    private void createResourceDir(GeneratePluginExtension ext) {
        File metaInf = new File(ext.getResDir(), METAINF);
        metaInf.mkdirs();

        File assets = new File(metaInf, "assets");
        assets.mkdir();

        // create css, images and js folder
        new File(assets, "css").mkdir();
        new File(assets, "images").mkdir();
        new File(assets, "js").mkdir();

        ext.setAssetFolder(assets);

        File modules = new File(metaInf, "modules");
        modules.mkdir();

        File resDir = new File(ext.getResDir());
        String packageString = ext.getPackageString();
        File packageRoot = PackageDirectoryCreator.createPackageDir(resDir, packageString);

        File components = new File(packageRoot, "components");
        components.mkdir();
        File pages = new File(packageRoot, "pages");
        pages.mkdir();

    }
}
