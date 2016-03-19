package net.mreunionlabs.tapgradle.generate.generator;

import com.helger.jcodemodel.JClassAlreadyExistsException;
import com.helger.jcodemodel.JCodeModel;
import com.helger.jcodemodel.JDefinedClass;
import net.mreunionlabs.tapgradle.generate.GeneratePluginExtension;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by abangkis on 3/19/2016.
 */
public class JAppModuleGenerator {

    public static final String SERVICES = "services";


    // E:\data\IDEA-workspace\SIPPS\src\main\java\net\mreunionlabs\sipps\server\services
    public void createFile(GeneratePluginExtension ext) {
        System.out.println("Generating app module class");

        String packageString = ext.getPackageString();
        if (packageString != null && !"".equals(packageString)) {
            JCodeModel codeModel = new JCodeModel();
            try {
                JDefinedClass definedClass = codeModel._class(packageString + ".AppModule");
                codeModel.build(new File("."));
            } catch (JClassAlreadyExistsException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            System.err.println("AppModule GEN: package is empty");
        }


    }


}
