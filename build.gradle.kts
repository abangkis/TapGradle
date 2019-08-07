import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    //    kotlin("jvm") version "1.3.31"
    `kotlin-dsl`
    `maven-publish`
}

group = "net.mreunionlabs"
version = "0.2"

gradlePlugin {
    plugins {
        register("TapGradle") {
            id = "net.mreunionlabs.tapgradle.generate"
            implementationClass = "net.mreunionlabs.tapgradle.generate.GeneratePlugin"
        }
    }
}

repositories {
    //    mavenCentral()
    jcenter()
    flatDir {
        dirs("libs")
    }
}

publishing {
    repositories {
        mavenLocal()
    }
}

dependencies {

    implementation(kotlin("stdlib-jdk8"))
    implementation("org.apache.tapestry:tapestry-core:5.4.4")
    implementation("org.jdom:jdom2:2.0.6")
    implementation("com.helger:jcodemodel:2.8.5")
    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.6.12")

    testImplementation("junit:junit:4.12")
    testImplementation("io.kotlintest:kotlintest-runner-junit5:3.3.2")
    testImplementation(gradleTestKit())
}

// must have line for kotlin test. Otherwise you will have No tests found exception
tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}




