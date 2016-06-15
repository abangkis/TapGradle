package net.mreunionlabs.tapgradle.generate.generator;

import net.mreunionlabs.tapgradle.generate.GeneratePluginExtension;
import org.apache.tools.ant.taskdefs.Java;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by abangkis on 6/16/2016.
 */
public class JavaStructureGeneratorTest {
    private GeneratePluginExtension ext;

    @BeforeMethod
    public void setUp() throws Exception {
        ext = new GeneratePluginExtension();
        ext.setPackageString("net.mreunionlabs.gen");
        ext.setJavaDir(".");
    }

    @Test
    public void testCreateDirectories() throws Exception {
        JavaStructureGenerator generator = new JavaStructureGenerator();
        generator.createDirectories(ext);
    }

}