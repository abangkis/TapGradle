package net.mreunionlabs.tapgradle.generate

import java.io.File

open class GeneratePluginExtension {
    var javaDir = "src/main/java"
    var resDir = "src/main/resources"
    var webAppDir = "src/main/webapp"

    // fields from user
    var packageString: String = "net.mreunionlabs.gen"
    var webDisplayName = "Tap Gradle By MReunionlabs"

    var assetFolder: File? = null
}
