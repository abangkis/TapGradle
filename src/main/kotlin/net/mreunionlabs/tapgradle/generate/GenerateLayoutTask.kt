package net.mreunionlabs.tapgradle.generate

import net.mreunionlabs.tapgradle.generate.page.IndexGenerator
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
        IndexGenerator().createFile(ext)
//        copyLayout()
    }

    private fun copyLayout() {
        // todo don't know why but the resource root path points to build/classes/kotlin/test/ instead of the resource folder
        //  so we ditch the copy file approach and generate the file instead
        println("root path: ${javaClass.getResource("/")?.path}")
        println("layout: ${javaClass.getResource("templates/Layout.tml")}")
//        File("LayoutTml").bufferedWriter().use { out -> out.write(javaClass.getResource("/Layout.tml").readText()) }
//        file("$buildDir/checkstyleConfig/checkstyle.xml").text = MyPlugin.getResource('/config/checkstyle/checkstyle.xml').text

//        println("layout tml content ${project.resources.text.fromFile("templates/layout.tml")}")

    }
}



