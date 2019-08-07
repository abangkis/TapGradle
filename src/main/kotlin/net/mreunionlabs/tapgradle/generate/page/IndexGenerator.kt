package net.mreunionlabs.tapgradle.generate.page

import com.helger.jcodemodel.JClassAlreadyExistsException
import com.helger.jcodemodel.JCodeModel
import net.mreunionlabs.tapgradle.generate.GeneratePluginExtension
import java.io.File
import java.io.IOException
import kotlinx.html.*
import kotlinx.html.consumers.filter
import kotlinx.html.stream.appendHTML
import net.mreunionlabs.tapgradle.generate.util.PackageDirectoryCreator

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
//                val pageFolder = File(javaFolder, ext.packageString + "/pages")
//                pageFolder.mkdirs()

                val dc = cm._class("$packageString.pages.Index")
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
                title = "Bot Crow"
                body {
                    h1 { +"Hello Crow" }
                }
            }
        }.replaceFirst("xmlns=", "xmlns:t=")

        val resDir = File(ext.resDir)
        val packageString = ext.packageString
        val packageRoot = PackageDirectoryCreator.createPackageDir(resDir, packageString)
        val pages = File(packageRoot, "pages")
        pages.mkdirs() // fixme not necessary we do a lot of reapeted mkdirs on the same path but it's okay for now
        val index = File(pages, "Index.tml")

        print(text)
        index.bufferedWriter().use { out -> out.write(text) }

//        val components = File(packageRoot, "components")
//        components.mkdir()

//        val text2 = buildString {
//            //            appendln("<!DOCTYPE html>")
//            appendHTML().html {
//                body {
//                    a("http://kotlinlang.org") { +"link" }
//                }
//            }
//            appendln()
//        }
    }

}
