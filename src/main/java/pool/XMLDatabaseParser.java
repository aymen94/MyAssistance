package pool;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * The Class XMLDatabaseParser.
 */
public class XMLDatabaseParser {

    /**
     * The driver.
     */
    private String driver;

    /**
     * Gets the driver.
     *
     * @return the driver
     */
    public final String getDriver() {
        return driver;
    }

    /**
     * Gets the user.
     *
     * @return the user
     */
    public final String getUser() {
        return user;
    }

    /**
     * Gets the password.
     *
     * @return the password
     */
    public final String getPassword() {
        return password;
    }

    /**
     * Gets the url.
     *
     * @return the url
     */
    public final String getUrl() {
        return url;
    }

    /**
     * The user.
     */
    private String user;

    /**
     * The password.
     */
    private String password;

    /**
     * The url.
     */
    private String url;

    /**
     * Instantiates a new XML database parser.
     *
     * @param fileName     the file name
     * @param databaseName the database name
     * @throws Exception the exception
     */
    public XMLDatabaseParser(final String fileName, final String databaseName)
            throws Exception {
        parseXML(fileName, databaseName);
    }

    /**
     * Parses the XML.
     *
     * @param fileName     the file name
     * @param databaseName the database name
     * @return true, if successful
     * @throws Exception the exception
     */
    private boolean parseXML(final String fileName, final String databaseName)
            throws Exception {
        final File inputFile = getFile(fileName);
        final DocumentBuilderFactory dbFactory = DocumentBuilderFactory
                .newInstance();
        final DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        final Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();

        final NodeList nList = doc.getElementsByTagName("database");

        System.out.println("Parsing Database xml");
        for (int i = 0; i < nList.getLength(); i++) {
            final Node nNode = nList.item(i);

            final Element eElement = (Element) nNode;
            if (eElement.getAttribute("name").equalsIgnoreCase(databaseName)) {
                System.out.println("Database Config Name: " + databaseName);

                driver = eElement.getElementsByTagName("driver").item(0)
                        .getTextContent();
                System.out.println("Driver : " + driver);
                user = eElement.getElementsByTagName("user").item(0)
                        .getTextContent();
                System.out.println("User : " + user);
                password = eElement.getElementsByTagName("password").item(0)
                        .getTextContent();
                System.out.println("Password : " + "***");

                url = eElement.getElementsByTagName("url").item(0)
                        .getTextContent();
                final NodeList parameters = (eElement
                        .getElementsByTagName("parameter"));

                for (int j = 0; j < parameters.getLength(); j++) {
                    if (!url.contains("?")) {
                        url += "?";
                    } else {
                        url += "&";
                    }
                    url += parameters.item(j).getTextContent();
                }

                System.out.println("Url : " + url);

            }
        }
        return true;
    }

    /**
     * Gets the file.
     *
     * @param fileName the file name
     * @return the file
     */
    private File getFile(final String fileName) {

        // Get file from resources folder
        final ClassLoader classLoader = getClass().getClassLoader();
        final File file = new File(classLoader.getResource(fileName).getFile());
        return file;

    }
}
