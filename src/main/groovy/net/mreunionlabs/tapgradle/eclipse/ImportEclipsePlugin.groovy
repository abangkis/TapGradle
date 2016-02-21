package net.mreunionlabs.tapgradle.eclipse

import org.gradle.api.Plugin
import org.gradle.api.Project
/**
 * Created by abangkis on 14/02/2016.
 */
class ImportEclipsePlugin implements Plugin<Project>{

    @Override
    void apply(Project project) {
        project.extensions.create("eclipseExt", ImportEclipseExtension)
        project.task('importEclipse') << {
            println "import from ${project.eclipseExt.mainDir} to ${project.eclipseExt.targetPackagePath}"
        }
    }
}
