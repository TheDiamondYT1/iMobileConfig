package tk.thediamondyt.mobileconfig;

import org.w3c.dom.Node;

public class Utils {
	
	public static String getTextValue(Node node) {
        StringBuffer value = new StringBuffer();
        int length = node.getChildNodes().getLength();

        for(int i = 0; i < length; i ++) {
            Node c = node.getChildNodes().item(i);
            if(c.getNodeType() == Node.TEXT_NODE) 
				value.append(c.getNodeValue());
        }
        return value.toString().trim();
    }
}
