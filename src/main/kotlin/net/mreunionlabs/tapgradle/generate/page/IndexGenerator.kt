package net.mreunionlabs.tapgradle.generate.page

import com.helger.jcodemodel.JClassAlreadyExistsException
import com.helger.jcodemodel.JCodeModel
import kotlinx.html.body
import kotlinx.html.consumers.filter
import kotlinx.html.h1
import kotlinx.html.html
import kotlinx.html.stream.appendHTML
import kotlinx.html.title
import net.mreunionlabs.tapgradle.generate.GeneratePluginExtension
import net.mreunionlabs.tapgradle.generate.util.PackageDirectoryCreator
import java.io.File
import java.io.IOException

class IndexGenerator {

    fun createFile(ext: GeneratePluginExtension) {
        println("Generating Index Page")
        genJava(ext)
        genTml(ext)

    }

    private fun genJava(ext: GeneratePluginExtension) {
        val packageString = ext.packageString
        if ("" != packageString) {

            val cm = JCodeModel()
            try {
                val javaFolder = File(ext.javaDir)
                javaFolder.mkdirs()
                cm._class("$packageString.pages.Index")
                cm.build(javaFolder)
            } catch (e: JClassAlreadyExistsException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }

        } else {
            System.err.println("Index GEN: package is empty")
        }
    }

    private fun genTml(ext: GeneratePluginExtension) {
        val text = buildString {
            appendHTML().filter { if (it.tagName == "body") SKIP else PASS }.html(namespace = "http://tapestry.apache.org/schema/tapestry_5_4.xsd") {
                attributes["t:type"] = "Layout"
                title = ext.webDisplayName
                body {
                    h1 { +"Hello Tapestry 5.4" }
                }
            }
        }.replaceFirst("xmlns=", "xmlns:t=")

        val resDir = File(ext.resDir)
        val pages = PackageDirectoryCreator.createSubpackageDir(resDir, ext, "pages")
        File(pages, "Index.tml").bufferedWriter().use { out -> out.write(text) }
    }

}
