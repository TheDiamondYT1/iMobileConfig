package tk.thediamondyt.mobileconfig;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.content.Intent;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import tk.thediamondyt.mobileconfig.activities.SettingsActivity;
import tk.thediamondyt.mobileconfig.exceptions.PlistException;

public class MainActivity extends AppCompatActivity {
    
    private NodeList nodeList = null;
    private DocumentBuilder builder = null;
    private DocumentBuilderFactory factory = null;
    private Document plist = null;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        try {
            parse(android.os.Environment.getExternalStorageDirectory() + "/Android/test.mobileconfig"); // Test
        } catch (Exception ex) {
           // err("try parse " + ex.getMessage());
        }
    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
		    case R.id.actionSettings:
				startActivity(new Intent(this, SettingsActivity.class));
				break;
		}
		return super.onOptionsItemSelected(item);
	}
	
    
    public void parse(String file) throws PlistException {
        factory = DocumentBuilderFactory.newInstance(); 
        try {
            builder = factory.newDocumentBuilder(); 
            plist = builder.parse(new File(file));
        } catch(Exception ex) {
            ex.printStackTrace();
			throw new PlistException(this, "Failed to parse document: \n\n" + ex.getMessage());
        }
        
        Element element = plist.getDocumentElement();
        element.normalize();  
        
        if(!element.getNodeName().equals("plist")) {
             throw new PlistException(this, "Document element should be <plist>");
        }
		
        nodeList = plist.getElementsByTagName("*");
		
        for(int i = 0; i < nodeList.getLength(); i++) {
            Element el = (Element) nodeList.item(i);       
            NodeList nl = nodeList.item(i).getChildNodes();
            int id = 0;
			
            for(int j = 0; j < nodeList.getLength(); j++) {
                Node childNode = nodeList.item(j);
                if(childNode.getNodeName().equals("key")) {
                    if(getTextValue(childNode).equals("PayloadDisplayName")) {
                        ((TextView) findViewById(R.id.payloadNameTextView)).setText(nodeList.item(j+1).getTextContent().trim());
                    }
                    if(getTextValue(childNode).equals("PayloadDescription")) {
                        ((TextView) findViewById(R.id.payloadDescTextView)).setText(nodeList.item(j+1).getTextContent().trim());
                    }
					if(getTextValue(childNode).equals("PayloadOrganization")) {
                        ((TextView) findViewById(R.id.payloadOrgTextView)).setText(nodeList.item(j+1).getTextContent().trim());
                    }
					if(getTextValue(childNode).equals("PayloadType")) {
                        ((TextView) findViewById(R.id.payloadTypeTextView)).setText(nodeList.item(j+1).getTextContent().trim());
                    }
                } 
            }
        }
    }
    
    private String getTextValue(Node node) {
        StringBuffer value = new StringBuffer();
        int length = node.getChildNodes().getLength();
		
        for(int i = 0; i < length; i ++) {
            Node c = node.getChildNodes().item(i);
            if(c.getNodeType() == Node.TEXT_NODE) 
				value.append(c.getNodeValue());
        }
        return value.toString().trim();
    }
    
	// For debugging
    public void err(String t) {
        new android.app.AlertDialog.Builder(this).setMessage(t).create().show();
    }
}
