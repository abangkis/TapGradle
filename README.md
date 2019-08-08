# TapGradle

Gradle plugin for creating tapestry 5.4 structure. It will generate the necessarry file such as
- AppModule.java (Including random HMAC Pass phrase)
- Layout.tml, Layout.java, Index.tml and Index.java

**this plugin hasn't been published to maven central**
**and we also moved from build.gradle(groovy) to build.gradle.kts(kotlin) format**

for old [groovy script](https://github.com/abangkis/TapGradle/edit/master/README-groovy.md)

Because this plugin hasn't been publish to maven central, you need to publish it your self to your maven local repository
* clone this project
* open the gradle window in TapGradle Project
* Task > publishing
* Right click and publish to maven local 

After you install it to your local maven. Create your new gradle project. 

Add this code to the build.gradle.kts. Don't forget to change the packageString with your own package. 

```kotlin
import net.mreunionlabs.tapgradle.generate.GeneratePluginExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("net.mreunionlabs.tapgradle.generate") version "0.2"
}

repositories {
    mavenCentral()
    mavenLocal()
}

configure<GeneratePluginExtension> {
    packageString = "net.mreunionlabs.tpu"
    webDisplayName = "Tap Plugin User"
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    val tapestryVersion = "5.4.4"

    implementation("org.apache.tapestry:tapestry-core:$tapestryVersion")
    implementation("org.apache.tapestry:tapestry-beanvalidator:$tapestryVersion")
    implementation("org.slf4j:slf4j-log4j12:1.7.14")
}
```

and add this to settings.gradle.kts

```kotlin
pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
    }
}
```

* Open the gradle window, refresh it, 
* Task > Other
* Run genLayout (gen index & layout) or genStructure (only gen the structure)

[markdown link](https://guides.github.com/features/mastering-markdown/)
