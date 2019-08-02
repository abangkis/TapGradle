package net.mreunionlabs.tapgradle.generate

import org.gradle.api.Plugin
import org.gradle.api.Project

class GeneratePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.extensions.create("genExt", GeneratePluginExtension::class.java)
        project.tasks.create("genStructure", GenerateTask::class.java)
    }
}
