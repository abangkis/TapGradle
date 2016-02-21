# TapGradle

Gradle plugin for creating tapestry 5.4 structure

**this plugin hasn't been published to maven central**

build.gradle sample 

```java
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
```
