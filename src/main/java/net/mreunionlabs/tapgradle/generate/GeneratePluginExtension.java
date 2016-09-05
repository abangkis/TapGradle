package net.mreunionlabs.tapgradle.generate;

import java.io.File;

/**
 * Created by abangkis on 21/02/2016.
 */
public class GeneratePluginExtension {
    private String javaDir = "src/main/java";
    private String resDir = "src/main/resources";
    private String webAppDir = "src/main/webapp";

    // fields from user
    private String packageString;
    private String webDisplayName = "Tap Gradle By MReunionlabs";

    private File assetFolder;

    public String getJavaDir() {
        return javaDir;
    }

    public void setJavaDir(String javaDir) {
        this.javaDir = javaDir;
    }

    public String getResDir() {
        return resDir;
    }

    public void setResDir(String resDir) {
        this.resDir = resDir;
    }

    public String getWebAppDir() {
        return webAppDir;
    }

    public void setWebAppDir(String webAppDir) {
        this.webAppDir = webAppDir;
    }

    public String getPackageString() {
        return packageString;
    }

    public void setPackageString(String packageString) {
        this.packageString = packageString;
    }

    public String getWebDisplayName() {
        return webDisplayName;
    }

    public void setWebDisplayName(String webDisplayName) {
        this.webDisplayName = webDisplayName;
    }

    public File getAssetFolder() {
        return assetFolder;
    }

    public void setAssetFolder(File assetFolder) {
        this.assetFolder = assetFolder;
    }
}
