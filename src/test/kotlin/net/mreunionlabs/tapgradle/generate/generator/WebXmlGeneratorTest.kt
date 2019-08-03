package net.mreunionlabs.tapgradle.generate.generator

import io.kotlintest.TestCase
import io.kotlintest.specs.StringSpec
import net.mreunionlabs.tapgradle.generate.GeneratePluginExtension
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder

class WebXmlGeneratorTest : StringSpec(){
    private lateinit var project: Project
    private lateinit var ext: GeneratePluginExtension

    override fun beforeTest(testCase: TestCase) {
        project = ProjectBuilder.builder().build()
        ext = GeneratePluginExtension()
        ext.packageString = "net.mreunionlabs.test"
        ext.webAppDir = "gen/webapp"
    }

    init {
        "create web xml file test should run" {
            WebXmlGenerator().createFile(ext)
        }
    }
}