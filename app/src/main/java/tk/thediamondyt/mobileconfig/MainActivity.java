package tk.thediamondyt.mobileconfig;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AlertDialog;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;
import android.content.DialogInterface;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import tk.thediamondyt.mobileconfig.activities.SettingsActivity;
import tk.thediamondyt.mobileconfig.activities.MoreDetailsActivity;
import tk.thediamondyt.mobileconfig.exceptions.PlistException;
import javax.xml.transform.*;

public class MainActivity extends AppCompatActivity {
    
    private NodeList nodeList = null;
    private DocumentBuilder builder = null;
    private DocumentBuilderFactory factory = null;
    private static Document plist = null;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		
		if(getIntent().getData() == null) {
			setContentView(R.layout.no_intent);
		} else {
			setContentView(R.layout.activity_main);
            try {
                parse(getIntent().getData().getPath()); 
            } catch (Exception ex) {
				new AlertDialog.Builder(this)
					.setTitle("Oh noes it broke :(").setMessage(ex.getMessage())
					.setPositiveButton("Alright", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int p1) {
							finish();
						}
					}).create().show();
            }
			((Button) findViewById(R.id.moreDetailsBtn)).setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					startActivity(new Intent(MainActivity.this, MoreDetailsActivity.class));
				}
			});
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
			throw new PlistException("Failed to parse document: \n\n" + ex.getMessage());
        }
        
        Element element = plist.getDocumentElement();
        element.normalize();  
        
        if(!element.getNodeName().equals("plist")) {
             throw new PlistException("Document element should be <plist>");
        }
		
        nodeList = plist.getElementsByTagName("*");
		
        for(int i = 0; i < nodeList.getLength(); i++) {
            Node childNode = nodeList.item(i);
            if(childNode.getNodeName().equals("key")) {
                if(Utils.getTextValue(childNode).equals("PayloadDisplayName")) {
                    ((TextView) findViewById(R.id.payloadNameTextView)).setText(nodeList.item(i+1).getTextContent().trim());
                }
                if(Utils.getTextValue(childNode).equals("PayloadDescription")) {
                   ((TextView) findViewById(R.id.payloadDescTextView)).setText(nodeList.item(i+1).getTextContent().trim());
                }
		        if(Utils.getTextValue(childNode).equals("PayloadOrganization")) {
                    ((TextView) findViewById(R.id.payloadOrgTextView)).setText(nodeList.item(i+1).getTextContent().trim());
                }
				if(Utils.getTextValue(childNode).equals("PayloadType")) {
                    ((TextView) findViewById(R.id.payloadTypeTextView)).setText(nodeList.item(i+1).getTextContent().trim());
                } 
            } 
        } 
    }
	
	public static Document getPlist() {
		return plist;
	}
    
	// For debugging
    private void err(String t) {
        new android.app.AlertDialog.Builder(this).setMessage(t).create().show();
    }
}
