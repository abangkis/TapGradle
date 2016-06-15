package net.mreunionlabs.tapgradle.generate.generator;

import net.mreunionlabs.tapgradle.generate.GeneratePluginExtension;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by abangkis on 6/15/2016.
 */
public class AppModuleGeneratorTest {

    private GeneratePluginExtension ext;

    @BeforeClass
    public void setup() {
        ext = new GeneratePluginExtension();
        ext.setPackageString("net.mreunionlabs.gen");
        ext.setJavaDir(".");
    }

    @Test
    public void testGenerate() {
        AppModuleGenerator generator = new AppModuleGenerator();
        generator.createFile(ext);
    }
}