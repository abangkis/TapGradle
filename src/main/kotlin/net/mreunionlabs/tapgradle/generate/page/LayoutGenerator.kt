package net.mreunionlabs.tapgradle.generate.page

import kotlinx.html.*
import kotlinx.html.stream.appendHTML
import net.mreunionlabs.tapgradle.generate.GeneratePluginExtension
import net.mreunionlabs.tapgradle.generate.util.PackageDirectoryCreator
import java.io.File

class LayoutGenerator {
    fun createFile(ext: GeneratePluginExtension) {
        println("Generating Layout Page")
        genJava(ext)
        genTml(ext)
    }

    private fun genJava(ext: GeneratePluginExtension) {
        val packageString = ext.packageString
        if ("" != packageString) {

            val packagePath = "$packageString.components"

            // todo hard to do multiple annotation with KPoet so we put the file content into a string instead
            val layoutString = "package $packagePath;\n" +
                    "\n" +
                    "import org.apache.tapestry5.BindingConstants;\n" +
                    "import org.apache.tapestry5.Block;\n" +
                    "import org.apache.tapestry5.ComponentResources;\n" +
                    "import org.apache.tapestry5.annotations.Parameter;\n" +
                    "import org.apache.tapestry5.annotations.Property;\n" +
                    "import org.apache.tapestry5.ioc.annotations.Inject;\n" +
                    "\n" +
                    "\n" +
                    "//@Import(stylesheet = {\"classpath:/META-INF/assets/vendor/metisMenu/metisMenu.css\", \"classpath:/META-INF/assets/vendor/font-awesome/css/font-awesome.css\",\n" +
                    "//\t\t\t\t\"classpath:/META-INF/assets/css/sb-admin-2.css\"})\n" +
                    "public class Layout {\n" +
                    "    /**\n" +
                    "     * The page title, for the <title> element and the <h1>element.\n" +
                    "     */\n" +
                    "    @Property\n" +
                    "    @Parameter(required = true, defaultPrefix = BindingConstants.LITERAL)\n" +
                    "    private String title;\n" +
                    "\n" +
                    "    @Property\n" +
                    "    private String pageName;\n" +
                    "\n" +
                    "    @Property\n" +
                    "    @Parameter(defaultPrefix = BindingConstants.LITERAL)\n" +
                    "    private String sidebarTitle;\n" +
                    "\n" +
                    "    @Property\n" +
                    "    @Parameter(defaultPrefix = BindingConstants.LITERAL)\n" +
                    "    private Block pagemenu;\n" +
                    "\n" +
                    "    @Property\n" +
                    "    @Parameter(defaultPrefix = BindingConstants.LITERAL)\n" +
                    "    private Block pagetitle;\n" +
                    "\n" +
                    "    @Inject\n" +
                    "    private ComponentResources resources;\n" +
                    "\n" +
                    "    public String getClassForPageName() {\n" +
                    "        return resources.getPageName().equalsIgnoreCase(pageName) ? \"current_page_item\" : null;\n" +
                    "    }\n" +
                    "\n" +
                    "    public String[] getPageNames() {\n" +
                    "        return new String[]{\"Index\"};\n" +
                    "    }\n" +
                    "\n" +
                    "    public String getSimplePageName(String pageName) {\n" +
                    "        int start = pageName.lastIndexOf(\"/\");\n" +
                    "        if (start >= 0)\n" +
                    "            return pageName.substring(start + 1);\n" +
                    "        else\n" +
                    "            return pageName;\n" +
                    "    }\n" +
                    "}\n"

            val javaFolder = File(ext.javaDir)
            val components = PackageDirectoryCreator.createSubpackageDir(javaFolder, ext, "components")
            File(components, "Layout.java").bufferedWriter().use { out -> out.write(layoutString) }

        } else {
            System.err.println("Layout GEN: package is empty")
        }
    }

    private fun genTml(ext: GeneratePluginExtension) {
        val text = buildString {
            appendln("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">")
            appendHTML().html(namespace = "http://tapestry.apache.org/schema/tapestry_5_4.xsd") {
                head {
                    meta {
                        attributes["http-equiv"] = "content-type"
                        content = "text/html; charset=UTF-8"
                    }
                    title { +"\${title}" }
//                            link {
//                                rel = "icon"
//                                type = "image/png"
//                                href = "\${context:images/favicon.png}"
//                            }
                }
                body {
                    unsafe {
                        raw("""<t:body/>""")
                    }
                }
            }
        }.replaceFirst("xmlns=", "xmlns:t=")

        val resDir = File(ext.resDir)
        val pages = PackageDirectoryCreator.createSubpackageDir(resDir, ext, "components")
        val index = File(pages, "Layout.tml")

        index.bufferedWriter().use { out -> out.write(text) }
    }
}