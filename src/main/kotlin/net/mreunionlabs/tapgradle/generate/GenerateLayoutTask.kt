package net.mreunionlabs.tapgradle.generate

import net.mreunionlabs.tapgradle.generate.page.IndexGenerator
import net.mreunionlabs.tapgradle.generate.page.LayoutGenerator
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.gradle.kotlin.dsl.findByType

open class GenerateLayoutTask : DefaultTask() {
    private var greeting = "Generating Tapestry Index and Layout Files"

    @TaskAction
    fun generate() {
        var ext = project.extensions.findByType(GeneratePluginExtension::class)
        if (ext == null) {
            ext = GeneratePluginExtension()
        }

        println(greeting)
        IndexGenerator().createFile(ext)
        LayoutGenerator().createFile(ext)
    }

}



