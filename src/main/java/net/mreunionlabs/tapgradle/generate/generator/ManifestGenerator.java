package net.mreunionlabs.tapgradle.generate.generator;

import net.mreunionlabs.tapgradle.generate.GeneratePluginExtension;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Created by abangkis on 3/19/2016.
 */
public class ManifestGenerator {

    private static final String MANIFEST = "MANIFEST.MF";

    public void createFile(GeneratePluginExtension ext) {
        File assetFolder = ext.getAssetFolder();
        if (assetFolder != null) {

            File manifest = new File(assetFolder, MANIFEST);

            try (PrintWriter writer = new PrintWriter(manifest)) {
                writer.println("Manifest-Version: 1.0");
                writer.println("Class-Path: ");
                writer.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        else {
            System.err.println("MANIFEST GEN: asset folder is null");
        }
    }
}
