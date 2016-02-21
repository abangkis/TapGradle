package net.mreunionlabs.tapgradle.greeting

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
 * Created by abangkis on 14/02/2016.
 */
class GreetingTask extends DefaultTask {
    String greeting = 'hello from GreetingTask'

    @TaskAction
    def greet() {
        println greeting
    }
}
