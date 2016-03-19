package net.mreunionlabs.tapgradle.generate.generator;

import net.mreunionlabs.tapgradle.generate.GeneratePluginExtension;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Created by abangkis on 3/19/2016.
 */
public class OldAppModuleGenerator {

    public static final String SERVICES = "services";


    // E:\data\IDEA-workspace\SIPPS\src\main\java\net\mreunionlabs\sipps\server\services
    public void createFile(GeneratePluginExtension ext) {
        String packageString = ext.getPackageString();
        if (packageString != null && !"".equals(packageString)) {
            File packageFolder = new File(ext.getJavaDir() + packageString);
            File appModule = new File(packageFolder, SERVICES + File.separator + "AppModule.java");

            try (PrintWriter writer = new PrintWriter(appModule)) {

                writer.println("package " + packageString+ "." + SERVICES + ";");
                writeJavaDoc(writer);

                writer.println("public class AppModule");
                writer.println("{");
                writeBindMethod(writer);
                writer.println("}");
                writer.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("AppModule GEN: package is empty");
        }

    }

    private void writeBindMethod(PrintWriter writer) {
        writer.println("public static void bind(ServiceBinder binder)");
        writeEmptyClosure(writer);
    }

        {

        }

    private void writeJavaDoc(PrintWriter writer) {
        writer.println("/**");
        writer.println("* This module is automatically included as part of the Tapestry IoC Registry, it's a good place to");
        writer.println("* configure and extend Tapestry, or to place your own service definitions.");
        writer.println("*/");
    }

    private void writeEmptyClosure(PrintWriter writer) {
        writer.println("{");
        writer.println("}");
    }

}
