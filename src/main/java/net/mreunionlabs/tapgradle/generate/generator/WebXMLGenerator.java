package net.mreunionlabs.tapgradle.generate.generator;

import net.mreunionlabs.tapgradle.generate.GeneratePluginExtension;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by abangkis on 05-Sep-16.
 */
public class WebXMLGenerator {

    public void createFile(GeneratePluginExtension ext) {
        try {

            Element webApp = new Element("web-app");
            Document doc = new Document(webApp);

            Element displayName = new Element("display-name").setText(ext.getWebDisplayName());
            doc.getRootElement().addContent(displayName);

            Element packageParam = new Element("context-param");
            packageParam.addContent(new Element("param-name").setText("tapestry.app-package"));
            packageParam.addContent(new Element("param-value").setText(ext.getPackageString()));
            doc.getRootElement().addContent(packageParam);

            Element prodModeParam = new Element("context-param");
            prodModeParam.addContent(new Element("param-name").setText("tapestry.production-mode"));
            prodModeParam.addContent(new Element("param-value").setText("false"));
            doc.getRootElement().addContent(prodModeParam);


            Element filter = new Element("filter");
            filter.addContent(new Element("filter-name").setText("app"));
            filter.addContent(new Element("filter-class").setText("org.apache.tapestry5.TapestryFilter"));
            doc.getRootElement().addContent(filter);

            Element filterMapping = new Element("filter-mapping");
            filterMapping.addContent(new Element("filter-name").setText("app"));
            filterMapping.addContent(new Element("url-pattern").setText("/*"));
            doc.getRootElement().addContent(filterMapping);

//            Element staff = new Element("display-name");
//            staff.setAttribute(new Attribute("id", "1"));
//            staff.addContent(new Element("firstname").setText("yong"));
//            doc.getRootElement().addContent(staff);

            // new XMLOutputter().output(doc, System.out);
            XMLOutputter xmlOutput = new XMLOutputter();

            // display nice nice
            xmlOutput.setFormat(Format.getPrettyFormat());

            File webAppDir = new File(ext.getWebAppDir());
            File webInf = new File(webAppDir, "WEB-INF");
            webInf.mkdirs();
            File webXml = new File(webInf, "web.xml");

            xmlOutput.output(doc, new FileWriter(webXml));

            System.out.println("File Saved!");
        } catch (IOException io) {
            System.out.println(io.getMessage());
        }
    }
}
