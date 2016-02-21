package net.mreunionlabs.tapgradle.generate;

/**
 * Created by abangkis on 21/02/2016.
 */
public class GeneratePluginExtension {
    private String javaDir = "src/main/java";
    private String resDir = "src/main/resources";
    private String webAppDir = "src/main/webapp";

    private String packagePath;

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

    public String getPackagePath() {
        return packagePath;
    }

    public void setPackagePath(String packagePath) {
        this.packagePath = packagePath;
    }
}
