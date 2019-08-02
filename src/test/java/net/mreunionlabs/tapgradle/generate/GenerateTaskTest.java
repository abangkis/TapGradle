package net.mreunionlabs.tapgradle.generate;

import org.gradle.api.Project;
import org.gradle.testfixtures.ProjectBuilder;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by abangkis on 6/15/2016.
 */
public class GenerateTaskTest {
    private GeneratePluginExtension ext;
    private GenerateTask genTask;

    @BeforeMethod
    public void setUp() throws Exception {

        Project project = ProjectBuilder.builder().build();
        genTask = project.getTasks().create("genStructure", GenerateTask.class);

        ext = new GeneratePluginExtension();
        ext.setPackageString("net.mreunionlabs.test");
        ext.setJavaDir("test/gen");
    }

    @Test
    public void testGenerate() throws Exception {
        genTask.generate();
    }

}