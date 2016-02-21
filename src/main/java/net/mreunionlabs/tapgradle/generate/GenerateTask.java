package net.mreunionlabs.tapgradle.generate;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import java.io.*;

/**
 * Created by abangkis on 21/02/2016.
 */
public class GenerateTask extends DefaultTask{
    String greeting = "Generating Tapestry 5.4 structure";
    private static final String METAINF = "META-INF";
    private static final String MANIFEST = "MANIFEST.MF";

    @TaskAction
    public void generate() {
        GeneratePluginExtension ext = getProject().getExtensions().findByType(GeneratePluginExtension.class);
        if (ext == null) {
            ext = new GeneratePluginExtension();
        }

        System.out.println(greeting);
        createDirectories(ext);

//
//        String message = extension.getMessage();
//        HelloWorld helloWorld = new HelloWorld(message);
//        System.out.println(helloWorld.greet());
    }

    private void createDirectories(GeneratePluginExtension ext) {
        File f = new File(ext.getJavaDir());
        f.mkdir();
        createResourceDir(ext);
        f = new File(ext.getWebAppDir());
        f.mkdir();
    }

    private void createResourceDir(GeneratePluginExtension ext) {
        File metaInf = new File(ext.getResDir(), METAINF);
        metaInf.mkdirs();

        File assets = new File(metaInf, "assets");
        assets.mkdir();

        // create css, images and js folder
        new File(assets, "css").mkdir();
        new File(assets, "images").mkdir();
        new File(assets, "js").mkdir();

        createManifest(assets);

        File modules = new File(metaInf, "modules");
        modules.mkdir();
    }

    private void createManifest(File assets) {
        File manifest = new File(assets, MANIFEST);

        try (PrintWriter writer = new PrintWriter(manifest)) {
            writer.println("Manifest-Version: 1.0");
            writer.println("Class-Path: ");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

//    task copyWebapp(type: Copy){
//        println "Copying web files from ${webSourceDir} to ${webappDir}"
//        from(webSourceDir)
//        into(webappDir)
//
//        exclude '**/*.tml'
//    }
//
//    task copyComponentsTml(type: Copy){
//
//        File dir = new File(resDir + "/" + targetPackagePath + "/components")
//
//        from(sourceDir)
//        into(resDir)
//        include '**/*.tml'
//        exclude '**/*.java'
//        exclude 'hibernate.cfg.xml'
//        exclude 'log4j.properties'
//    }
//
//    task copyTml(type: Copy){
//
//        File dir = new File(resDir + "/" + targetPackagePath + "/pages")
//
//        from(webSourceDir)
//        into(dir)
//        include '**/*.tml'
//        exclude '**/META-INF/**'
//    }
//
//    task copyConfig(type: Copy){
//        println "Copying config from ${sourceDir} to ${resDir}"
//        from(sourceDir)
//        into(resDir)
//        include 'hibernate.cfg.xml'
//        include 'log4j.properties'
//    }
//
//    task copyRequireJS(type: Copy){
//        println "Copying require js from ${requireJSDir} to ${resDir}"
//        from(requireJSDir)
//        into(resDir)
//    }
//
//    task copyResources(dependsOn: [copyComponentsTml,copyTml, copyConfig, copyRequireJS]){
//        println "Copying resources"
//    }
//
//    task copyJava(type: Copy){
//        println "Copying java code from ${sourceDir} to ${javaDir}"
//        from(sourceDir)
//        into(javaDir)
//        include '**/*.java'
//        exclude 'hibernate.cfg.xml'
//        exclude 'log4j.properties'
//    }
//
//    task copyEclipse(dependsOn: [copyWebapp,copyResources,copyJava]) << {
//        println "Copying eclipse source code!"
//    }
//
//    task deleteEmptyDirs() {
//        def emptyDirs = []
//
//        project.fileTree(dir: 'src/main').visit {
//            def File f = it.file
//
//            if (f.isDirectory() ) {
//                def children = project.fileTree(f).filter { it.isFile() }.files
//                if (children.size() == 0) {
//                    emptyDirs << f
//                }
//            }
//        }
//
//        // reverse so that we do the deepest folders first
//        emptyDirs.reverseEach { it.delete() }
//    }

}



