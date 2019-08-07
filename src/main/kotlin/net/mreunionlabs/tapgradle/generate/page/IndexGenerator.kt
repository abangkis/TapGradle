package net.mreunionlabs.tapgradle.generate.page

import com.grosner.kpoet.*
import net.mreunionlabs.tapgradle.generate.GeneratePluginExtension
import java.io.File
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

            javaFile("$packageString.pages") {
                `class`("Index") { modifiers(public, final)
//                    `private final field`(String::class, "title") { `@`(Property::class), `@`(Parameter::class) } // can add annotations on fields
//                    field(`@`(Nullable::class), String::class, "android") { `=`("THE BEST".S) } // or this way

//                    `public static`(TypeName.VOID, "main",
//                            param(Array<String>::class, "args")) {
//                        statement("\$T.out.println(${"Hello, JavaPoet!"}.S)", System::class.java)
                }
            }.writeTo(File(ext.javaDir))

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
