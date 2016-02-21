package net.mreunionlabs.tapgradle.generate;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * Created by abangkis on 21/02/2016.
 */
public class GeneratePlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        project.getExtensions().create("genExt", GeneratePluginExtension.class);
        project.getTasks().create("genStructure", GenerateTask.class);
    }
}
