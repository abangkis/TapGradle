package net.mreunionlabs.tapgradle.generate.generator;

import com.helger.jcodemodel.*;
import net.mreunionlabs.tapgradle.generate.GeneratePluginExtension;
import net.mreunionlabs.tapgradle.generate.util.HMACGenerator;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by abangkis on 3/19/2016.
 */
public class AppModuleGenerator {

    public static final String SERVICES = "services";
    private GeneratePluginExtension ext;


    // E:\data\IDEA-workspace\SIPPS\src\main\java\net\mreunionlabs\sipps\server\services
    public void createFile(GeneratePluginExtension ext) {
        this.ext = ext;
        System.out.println("Generating app module class");

        String packageString = ext.getPackageString();
        if (packageString != null && !"".equals(packageString)) {
            JCodeModel cm = new JCodeModel();
            try {
                File javaFolder = new File(ext.getJavaDir());
                javaFolder.mkdirs();

                JDefinedClass dc = cm._class(packageString + ".services.AppModule");


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

        JBlock body = method.body();

        body.directStatement("\n");
        body.addSingleLineComment("Contributions to ApplicationDefaults will override any contributions to FactoryDefaults (with the same key)");
        // configuration.add(SymbolConstants.SUPPORTED_LOCALES, "en");
        JFieldRef locales = cm.ref(SymbolConstants.class).staticRef("SUPPORTED_LOCALES");
        body.add(configuration.invoke("add").arg(locales).arg("en"));

        body.directStatement("\n");
        body.addSingleLineComment("The factory default is true but during the early stages of an application\n" +
                "        // overriding to false is a good idea");
        // configuration.add(SymbolConstants.PRODUCTION_MODE, "false")
        JFieldRef productionMode = cm.ref(SymbolConstants.class).staticRef("PRODUCTION_MODE");
        body.add(configuration.invoke("add").arg(productionMode).arg("false"));

        body.directStatement("\n");
        body.addSingleLineComment("Replace prototype with JQuery. Erase this if you want to stick with prototype");
        // configuration.add(SymbolConstants.JAVASCRIPT_INFRASTRUCTURE_PROVIDER, "jquery");
        JFieldRef jquery = cm.ref(SymbolConstants.class).staticRef("JAVASCRIPT_INFRASTRUCTURE_PROVIDER");
        body.add(configuration.invoke("add").arg(jquery).arg("jquery"));

        body.directStatement("\n");
        body.addSingleLineComment("Set a random HMAC key for form signing (not cluster safe)");
        // configuration.add(SymbolConstants.HMAC_PASSPHRASE, random);
        JFieldRef passphrase = cm.ref(SymbolConstants.class).staticRef("HMAC_PASSPHRASE");
        HMACGenerator generator = new HMACGenerator();
        try {
            String random = generator.computeHmac(ext.getPackageString(), UUID.randomUUID().toString());
            body.add(configuration.invoke("add").arg(passphrase).arg(random));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }




    public void writeIfElse(JCodeModel codeModel, JDefinedClass c){
        //        method.body().assign(JExpr._this().ref(configuration.name()), JExpr.ref(configuration.name()));


//        cm.ref(org.junit.Assert.class).staticInvoke("assertEquals").arg(staticTYPEVar).arg(JExpr.invoke(uutField, "getType"));

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
