# TapGradle

Gradle plugin for creating tapestry 5.4 structure

build.gradle sample

apply plugin: 'net.mreunionlabs.tapgradle.generate'

buildscript {
    repositories {
        mavenCentral()
        mavenLocal()
    }
    dependencies {
        classpath 'net.mreunionlabs:TapGradle:1.0-SNAPSHOT'
    }
}
