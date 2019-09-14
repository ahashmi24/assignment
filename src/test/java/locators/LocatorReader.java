package locators;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;


public class LocatorReader {

	private Document doc;
	
	
	public LocatorReader(String xmlName) {
		SAXReader reader = new SAXReader();
		try {
			
			String localPath = System.getProperty("user.dir")+"//src//test//java//locators//";	
			doc = reader.read(localPath+xmlName);
		} catch (DocumentException e) {
			
			e.printStackTrace();
		}
	}
	
	public String getLocator(String locator){
		return doc.selectSingleNode("//" + locator.replace('.', '/')).getText();
		
	}
}
