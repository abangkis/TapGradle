# TapGradle

Gradle plugin for creating tapestry 5.4 structure. It will generate the necessarry file such as
- AppModule.java (Including random HMAC Pass phrase)

**this plugin hasn't been published to maven central**

Because this plugin hasn't been publish to maven central, you need to publish it your self to your maven local repository
* open the gradle window in TapGradle Project
* Task > Other
* Right click and run Install (task)

After you install it to your local maven. Create your new gradle project. Add this code to the build.gradle. Don't forget to change the packageString with your own package. 

```groovy
apply plugin: 'war'
apply from: 'https://raw.github.com/akhikhl/gretty/master/pluginScripts/gretty.plugin'
apply plugin: 'net.mreunionlabs.tapgradle.generate'

genExt {
    packageString = "net.mreunionlabs.sample"
    webDisplayName = "Tap Gradle By MReunionlabs"
}

repositories {
    jcenter()
}

buildscript {
    repositories {
        jcenter()
        mavenLocal()
    }
    dependencies {
        classpath 'net.mreunionlabs:TapGradle:1.0-SNAPSHOT'
    }
}

dependencies {
  
    def tapestry_version = '5.4.4'

    compile "com.google.code.gson:gson:2.8.1"
    compile "com.mchange:c3p0:0.9.5.2"
    compile "com.squareup.okhttp3:okhttp:3.9.0"

    compile "mysql:mysql-connector-java:5.1.38"
    compile "org.hibernate:hibernate-validator-annotation-processor:4.3.2.Final"
    compile "org.hibernate:hibernate-c3p0:4.3.8.Final"
    compile "org.apache.tapestry:tapestry-core:$tapestry_version"
    compile "org.apache.tapestry:tapestry-beanvalidator:$tapestry_version"
    compile "org.apache.tapestry:tapestry-hibernate:$tapestry_version"
    compile "org.apache.tapestry:tapestry-upload:$tapestry_version"

    compile "org.slf4j:slf4j-log4j12:1.7.14"
//    compile ""org.apache.commons:commons-lang3:3.3.2""

//    providedCompile 'javax.servlet:javax.servlet-api:3.1.0'
    testCompile group: 'junit', name: 'junit', version: '4.12'
}
```

config for gradle pre 4.0 version

```groovy
apply plugin: 'jetty' // include java and war plugin
apply plugin: 'net.mreunionlabs.tapgradle.generate'

genExt {
    packageString = "net.mreunionlabs.sample"
    webDisplayName = "Tap Gradle By MReunionlabs"
}

repositories {
    jcenter()
}

buildscript {
    repositories {
        jcenter()
        mavenLocal()
    }
    dependencies {
        classpath 'net.mreunionlabs:TapGradle:1.0-SNAPSHOT'
    }
}

dependencies {
    compile 'org.apache.tapestry:tapestry-core:5.4.0'
    compile 'org.apache.tapestry:tapestry-beanvalidator:5.4.0'
    compile 'org.apache.tapestry:tapestry-hibernate:5.4.0'
    compile 'org.hibernate:hibernate-validator-annotation-processor:4.3.2.Final'
    compile 'c3p0:c3p0:0.9.1.2'
    compile 'mysql:mysql-connector-java:5.1.38'
    compile 'com.squareup.okhttp3:okhttp:3.2.0'
    compile 'org.slf4j:slf4j-log4j12:1.7.14'
    compile 'org.apache.commons:commons-lang3:3.3.2'
    compile 'com.google.code.gson:gson:2.6.2'
//    providedCompile 'javax.servlet:javax.servlet-api:3.1.0'
    testCompile group: 'junit', name: 'junit', version: '4.11'
}
```

* Open the gradle window, refresh it, 
* Task > Other
* Run genStructure (Task)

[markdown link](https://guides.github.com/features/mastering-markdown/)
