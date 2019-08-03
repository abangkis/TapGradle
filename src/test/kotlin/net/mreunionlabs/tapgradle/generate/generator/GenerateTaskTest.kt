package net.mreunionlabs.tapgradle.generate.generator

import io.kotlintest.TestCase
import io.kotlintest.shouldNotBe
import io.kotlintest.specs.StringSpec
import net.mreunionlabs.tapgradle.generate.GeneratePlugin
import net.mreunionlabs.tapgradle.generate.GeneratePluginExtension
import net.mreunionlabs.tapgradle.generate.GenerateTask
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder

class GenerateTaskTest : StringSpec() {

    private lateinit var project: Project

    override fun beforeTest(testCase: TestCase) {
        project = ProjectBuilder.builder().build()
    }

    init {
        "get GeneratePlugin should not return null" {
            project.pluginManager.apply("net.mreunionlabs.tapgradle.generate")
            project.plugins.getPlugin(GeneratePlugin::class.java) shouldNotBe null
        }

        // see https://docs.gradle.org/current/userguide/custom_plugins.html for understanding how extensions works
        "run generate structure should not fail" {
            val ext = project.extensions.findByType(GeneratePluginExtension::class.java)
            ext?.packageString = "net.mreunionlabs.test"
            ext?.javaDir = "test/gen"
            //fixme why setting ext programatically not working?

            val genTask = project.tasks.create("genStructure", GenerateTask::class.java)
            genTask.generate()
        }
    }
}
