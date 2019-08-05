package net.mreunionlabs.tapgradle.generate.structure

import com.helger.jcodemodel.*
import net.mreunionlabs.tapgradle.generate.GeneratePluginExtension
import net.mreunionlabs.tapgradle.generate.util.HMACGenerator
import org.apache.tapestry5.SymbolConstants
import org.apache.tapestry5.ioc.MappedConfiguration
import org.apache.tapestry5.ioc.ServiceBinder

import java.io.File
import java.io.IOException
import java.io.UnsupportedEncodingException
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import java.util.ArrayList
import java.util.UUID

/**
 * Created by abangkis on 3/19/2016.
 */
class AppModuleGenerator {
    private lateinit var ext: GeneratePluginExtension

    // E:\data\IDEA-workspace\SIPPS\src\main\java\net\mreunionlabs\sipps\server\services
    fun createFile(ext: GeneratePluginExtension) {
        this.ext = ext
        println("Generating app module class")

        val packageString = ext.packageString
        if ("" != packageString) {
            val cm = JCodeModel()
            try {
                val javaFolder = File(ext.javaDir)
                javaFolder.mkdirs()

                val dc = cm._class("$packageString.services.AppModule")


                //                writeIfElse(cm, dc);
                //                writeForLoop(cm, dc);
                //                writeSomething(cm, dc);
                writeBindMethod(cm, dc)
                writeAppDefaultMethod(cm, dc)

                cm.build(javaFolder)
            } catch (e: JClassAlreadyExistsException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }

        } else {
            System.err.println("AppModule GEN: package is empty")
        }


    }

    private fun writeSomething(cm: JCodeModel, dc: JDefinedClass) {
        val m = dc.method(JMod.PUBLIC, cm.VOID, "foo")
        m.body().decl(cm.INT, "getCount")

        // This is not exactly right because we need to
        // support generics
        val arrayListclass = cm.ref(ArrayList::class.java)
        val `$list` = m.body().decl(arrayListclass, "alist",
                JExpr._new(arrayListclass))

        val `$integerclass` = cm.ref(Int::class.java)
        val foreach = m.body().forEach(`$integerclass`, "count", `$list`)
        val `$count1` = foreach.`var`()
        foreach.body().assign(JExpr.ref("getCount"), JExpr.lit(10))

        // printing out the variable
        val out1 = cm.ref(System::class.java).staticRef("out")
        // JInvocation invocation =
        foreach.body().invoke(out1, "println").arg(`$count1`)
    }

    private fun writeBindMethod(cm: JCodeModel, dc: JDefinedClass) {
        val method = dc.method(JMod.PUBLIC or JMod.STATIC,
                Void.TYPE,
                "bind")

        method.param(ServiceBinder::class.java, "binder")
    }

    private fun writeAppDefaultMethod(cm: JCodeModel, dc: JDefinedClass) {
        // create contributeApplicationDefaults
        val method = dc.method(JMod.PUBLIC or JMod.STATIC,
                Void.TYPE, "contributeApplicationDefaults")

        val configuration = method.param(
                cm.ref(MappedConfiguration::class.java).narrow(cm.ref(String::class.java), cm.ref(String::class.java)),
                "configuration")

        val body = method.body()

        body.directStatement("\n")
        body.addSingleLineComment("Contributions to ApplicationDefaults will override any contributions to FactoryDefaults (with the same key)")
        // configuration.add(SymbolConstants.SUPPORTED_LOCALES, "en");
        val locales = cm.ref(SymbolConstants::class.java).staticRef("SUPPORTED_LOCALES")
        body.add(configuration.invoke("add").arg(locales).arg("en"))

        body.directStatement("\n")
        body.addSingleLineComment("The factory default is true but during the early stages of an application\n" + "        // overriding to false is a good idea")
        // configuration.add(SymbolConstants.PRODUCTION_MODE, "false")
        val productionMode = cm.ref(SymbolConstants::class.java).staticRef("PRODUCTION_MODE")
        body.add(configuration.invoke("add").arg(productionMode).arg("false"))

        body.directStatement("\n")
        body.addSingleLineComment("Replace prototype with JQuery. Erase this if you want to stick with prototype")
        // configuration.add(SymbolConstants.JAVASCRIPT_INFRASTRUCTURE_PROVIDER, "jquery");
        val jquery = cm.ref(SymbolConstants::class.java).staticRef("JAVASCRIPT_INFRASTRUCTURE_PROVIDER")
        body.add(configuration.invoke("add").arg(jquery).arg("jquery"))

        body.directStatement("\n")
        body.addSingleLineComment("Set a random HMAC key for form signing (not cluster safe)")
        // configuration.add(SymbolConstants.HMAC_PASSPHRASE, random);
        val passphrase = cm.ref(SymbolConstants::class.java).staticRef("HMAC_PASSPHRASE")
        val generator = HMACGenerator()
        try {
            val random = generator.computeHmac(ext!!.packageString, UUID.randomUUID().toString())
            body.add(configuration.invoke("add").arg(passphrase).arg(random))
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: InvalidKeyException) {
            e.printStackTrace()
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }

    }


    fun writeIfElse(codeModel: JCodeModel, c: JDefinedClass) {
        //        method.body().assign(JExpr._this().ref(configuration.name()), JExpr.ref(configuration.name()));


        //        cm.ref(org.junit.Assert.class).staticInvoke("assertEquals").arg(staticTYPEVar).arg(JExpr.invoke(uutField, "getType"));

        val method = c.method(JMod.PUBLIC, codeModel.VOID, "testIf")

        val input = method.param(codeModel.INT, "input")
        val body = method.body()

        val condition = body._if(input.lt(JExpr.lit(42)))
        condition._then().add(
                codeModel.ref(System::class.java).staticRef("out").invoke("println").arg(JExpr.lit("hello")))

        condition._else().add(
                codeModel.ref(System::class.java).staticRef("out").invoke("println").arg(JExpr.lit("world")))
    }

    fun writeForLoop(codeModel: JCodeModel, c: JDefinedClass) {
        val method = c.method(JMod.PUBLIC, codeModel.VOID, "testFor")

        val input = method.param(Int::class.javaPrimitiveType!!, "input")

        val body = method.body()

        val forLoop = body._for()
        val ivar = forLoop.init(codeModel.INT, "i", JExpr.lit(0))
        forLoop.test(ivar.lt(JExpr.lit(42)))
        forLoop.update(ivar.assignPlus(JExpr.lit(1)))

        forLoop.body().add(
                codeModel.ref(System::class.java).staticRef("out").invoke("println").arg(ivar))

    }

    companion object {

        val SERVICES = "services"
    }

}
