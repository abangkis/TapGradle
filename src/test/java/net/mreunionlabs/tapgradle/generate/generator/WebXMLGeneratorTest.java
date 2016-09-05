package net.mreunionlabs.tapgradle.generate.generator;

import net.mreunionlabs.tapgradle.generate.GeneratePluginExtension;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by abangkis on 05-Sep-16.
 */
public class WebXMLGeneratorTest {
    private GeneratePluginExtension ext;

    @BeforeMethod
    public void setUp() throws Exception {
        ext = new GeneratePluginExtension();
        ext.setPackageString("net.mreunionlabs.gen");
        ext.setJavaDir(".");
    }
    @Test
    public void testCreateFile() throws Exception {
        WebXMLGenerator webXMLGenerator = new WebXMLGenerator();
        webXMLGenerator.createFile(ext);
    }

}