package net.mreunionlabs.tapgradle.generate.structure

import net.mreunionlabs.tapgradle.generate.GeneratePluginExtension
import org.jdom2.Document
import org.jdom2.Element
import org.jdom2.output.Format
import org.jdom2.output.XMLOutputter

import java.io.File
import java.io.FileWriter
import java.io.IOException

/**
 * Created by abangkis on 05-Sep-16.
 */
class WebXmlGenerator {

    fun createFile(ext: GeneratePluginExtension) {
        try {

            val webApp = Element("web-app")
            val doc = Document(webApp)

            val displayName = Element("display-name").setText(ext.webDisplayName)
            doc.rootElement.addContent(displayName)

            val packageParam = Element("context-param")
            packageParam.addContent(Element("param-name").setText("tapestry.app-package"))
            packageParam.addContent(Element("param-value").setText(ext.packageString))
            doc.rootElement.addContent(packageParam)

            val prodModeParam = Element("context-param")
            prodModeParam.addContent(Element("param-name").setText("tapestry.production-mode"))
            prodModeParam.addContent(Element("param-value").setText("false"))
            doc.rootElement.addContent(prodModeParam)


            val filter = Element("filter")
            filter.addContent(Element("filter-name").setText("app"))
            filter.addContent(Element("filter-class").setText("org.apache.tapestry5.TapestryFilter"))
            doc.rootElement.addContent(filter)

            val filterMapping = Element("filter-mapping")
            filterMapping.addContent(Element("filter-name").setText("app"))
            filterMapping.addContent(Element("url-pattern").setText("/*"))
            doc.rootElement.addContent(filterMapping)

            //            Element staff = new Element("display-name");
            //            staff.setAttribute(new Attribute("id", "1"));
            //            staff.addContent(new Element("firstname").setText("yong"));
            //            doc.getRootElement().addContent(staff);

            // new XMLOutputter().output(doc, System.out);
            val xmlOutput = XMLOutputter()

            // display nice nice
            xmlOutput.format = Format.getPrettyFormat()

            val webAppDir = File(ext.webAppDir)
            val webInf = File(webAppDir, "WEB-INF")
            webInf.mkdirs()
            val webXml = File(webInf, "web.xml")

            xmlOutput.output(doc, FileWriter(webXml))

            println("File Saved!")
        } catch (io: IOException) {
            println(io.message)
        }

    }
}
