package net.mreunionlabs.tapgradle.generate

import io.kotlintest.TestCase
import io.kotlintest.shouldNotBe
import io.kotlintest.specs.StringSpec
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder

// see https://guides.gradle.org/testing-gradle-plugins
class GenerateTaskTest : StringSpec() {
    private lateinit var project: Project
    private lateinit var ext: GeneratePluginExtension

    override fun beforeTest(testCase: TestCase) {
        project = ProjectBuilder.builder().build()
        ext = GeneratePluginExtension()
        ext.packageString = "net.mreunionlabs.test"
        ext.javaDir = "gen/java"
        ext.resDir = "gen/resources"
        ext.webAppDir = "gen/webapp"
        ext.webDisplayName = "Project Testing"

    }

    init {

//        "get GeneratePlugin should not return null" {
//            project.pluginManager.apply("net.mreunionlabs.tapgradle.generate")
//            project.plugins.getPlugin(GeneratePlugin::class.java) shouldNotBe null
//        }
//
//        // see https://docs.gradle.org/current/userguide/custom_plugins.html for understanding how extensions works
        "run generate structure should not fail" {
            val ext = project.extensions.findByType(GeneratePluginExtension::class.java)
            ext?.packageString = "net.mreunionlabs.test"
            ext?.javaDir = "test/gen"
            //fixme why setting ext programatically not working?

//            val genTask = project.tasks.create("genStructure", GenerateStructureTask::class.java)
            val genTask = project.tasks.create("genLayout", GenerateLayoutTask::class.java)
            genTask.generate()
        }
    }
}
