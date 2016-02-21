package net.mreunionlabs.tapgradle.greeting

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Created by abangkis on 14/02/2016.
 */
class GreetingPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        project.extensions.create("greeting", GreetingPluginExtension)
        project.task('hello') << {
            println "${project.greeting.message} from ${project.greeting.greeter}"
        }
    }
}
