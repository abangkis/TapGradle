package net.mreunionlabs.tapgradle.generate

import org.gradle.api.Plugin
import org.gradle.api.Project

class GeneratePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            extensions.create("genExt", GeneratePluginExtension::class.java)
            val structureTask = tasks.create("genStructure", GenerateStructureTask::class.java)
            val layoutTask = tasks.create("genLayout", GenerateLayoutTask::class.java)
            layoutTask.dependsOn(structureTask)
        }
    }
}

