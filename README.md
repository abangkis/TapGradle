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

default usage. Create a gradle project. Add this code to the build.gradle. Don't forget to change the packageString with your own package. 
```groovy
apply plugin: 'net.mreunionlabs.tapgradle.generate'

genExt {
    packageString = "net.mreunionlabs.tpu"
}

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

[markdown link](https://guides.github.com/features/mastering-markdown/)
