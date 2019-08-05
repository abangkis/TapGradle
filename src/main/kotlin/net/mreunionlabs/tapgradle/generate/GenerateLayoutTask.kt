package net.mreunionlabs.tapgradle.generate

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

open class GenerateLayoutTask : DefaultTask() {
    private var greeting = "Generating Tapestry Index and Layout Files"

    @TaskAction
    fun generate() {
        var ext = project.extensions.findByType(GeneratePluginExtension::class.java)
        if (ext == null) {
            ext = GeneratePluginExtension()
        }

        println(greeting)
        copyLayout()
    }

    private fun copyLayout() {
//        val webAppDir = File(ext.webAppDir)
//        val webInf = File(webAppDir, "WEB-INF")
//        webInf.mkdirs()
//        val webXml = File(webInf, "web.xml")
//
//        xmlOutput.output(doc, FileWriter(webXml))

        println("path: ${javaClass.getResource("/").path}")
        println("layout: ${javaClass.getResource("templates/Layout.tml")}")
//        File("LayoutTml").bufferedWriter().use { out -> out.write(javaClass.getResource("/Layout.tml").readText()) }
//        file("$buildDir/checkstyleConfig/checkstyle.xml").text = MyPlugin.getResource('/config/checkstyle/checkstyle.xml').text
    }
}



