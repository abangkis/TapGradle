package net.mreunionlabs.tapgradle.generate.generator;

import com.helger.jcodemodel.*;
import net.mreunionlabs.tapgradle.generate.GeneratePluginExtension;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by abangkis on 3/19/2016.
 */
public class AppModuleGenerator {

    public static final String SERVICES = "services";


    // E:\data\IDEA-workspace\SIPPS\src\main\java\net\mreunionlabs\sipps\server\services
    public void createFile(GeneratePluginExtension ext) {
        System.out.println("Generating app module class");

        String packageString = ext.getPackageString();
        if (packageString != null && !"".equals(packageString)) {
            JCodeModel cm = new JCodeModel();
            try {
                File javaFolder = new File(ext.getJavaDir());
                javaFolder.mkdirs();

                JDefinedClass dc = cm._class(packageString + ".AppModule");


//                writeIfElse(cm, dc);
//                writeForLoop(cm, dc);
//                writeSomething(cm, dc);
                writeBindMethod(cm, dc);
                writeAppDefaultMethod(cm, dc);

                cm.build(javaFolder);
            } catch (JClassAlreadyExistsException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            System.err.println("AppModule GEN: package is empty");
        }


    }

    private void writeSomething(JCodeModel cm, JDefinedClass dc) {
        JMethod m = dc.method(JMod.PUBLIC, cm.VOID, "foo");
        m.body().decl(cm.INT, "getCount");

        // This is not exactly right because we need to
        // support generics
        AbstractJClass arrayListclass = cm.ref(ArrayList.class);
        JVar $list = m.body().decl(arrayListclass, "alist",
                JExpr._new(arrayListclass));

        AbstractJClass $integerclass = cm.ref(Integer.class);
        JForEach foreach = m.body().forEach($integerclass, "count", $list);
        JVar $count1 = foreach.var();
        foreach.body().assign(JExpr.ref("getCount"), JExpr.lit(10));

        // printing out the variable
        JFieldRef out1 = cm.ref(System.class).staticRef("out");
        // JInvocation invocation =
        foreach.body().invoke(out1, "println").arg($count1);
    }

    private void writeBindMethod(JCodeModel cm, JDefinedClass dc) {
        JMethod method = dc.method(JMod.PUBLIC | JMod.STATIC,
                Void.TYPE,
                "bind");

        method.param(ServiceBinder.class, "binder");
    }

    private void writeAppDefaultMethod(JCodeModel cm, JDefinedClass dc) {
        // create contributeApplicationDefaults
        JMethod method = dc.method(JMod.PUBLIC | JMod.STATIC,
                Void.TYPE, "contributeApplicationDefaults");

        JVar configuration = method.param(
                cm.ref(MappedConfiguration.class).narrow(cm.ref(String.class), cm.ref(String.class)),
                "configuration");

//        method.body().assign(JExpr._this().ref(configuration.name()), JExpr.ref(configuration.name()));

        // configuration.add(SymbolConstants.SUPPORTED_LOCALES, "en");
//        cm.ref(org.junit.Assert.class).staticInvoke("assertEquals").arg(staticTYPEVar).arg(JExpr.invoke(uutField, "getType"));

        JFieldRef fieldRef = cm.ref(SymbolConstants.class).staticRef("SUPPORTED_LOCALES");
        method.body().add(configuration.invoke("add").arg(fieldRef).arg("en"));
    }




    public void writeIfElse(JCodeModel codeModel, JDefinedClass c){
        JMethod method = c.method(JMod.PUBLIC, codeModel.VOID, "testIf");

        JVar input = method.param(codeModel.INT, "input");
        JBlock body = method.body();

        JConditional condition = body._if(input.lt(JExpr.lit(42)));
        condition._then().add(
                codeModel.ref(System.class).staticRef("out").invoke("println").arg(JExpr.lit("hello")));

        condition._else().add(
                codeModel.ref(System.class).staticRef("out").invoke("println").arg(JExpr.lit("world")));
    }

    public void writeForLoop(JCodeModel codeModel, JDefinedClass c){
        JMethod method = c.method(JMod.PUBLIC, codeModel.VOID, "testFor");

        JVar input = method.param(int.class, "input");

        JBlock body = method.body();

        JForLoop forLoop = body._for();
        JVar ivar = forLoop.init(codeModel.INT, "i", JExpr.lit(0));
        forLoop.test(ivar.lt(JExpr.lit(42)));
        forLoop.update(ivar.assignPlus(JExpr.lit(1)));

        forLoop.body().add(
                codeModel.ref(System.class).staticRef("out").invoke("println").arg(ivar));

    }

}
