package tk.thediamondyt.mobileconfig.activities;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

import tk.thediamondyt.mobileconfig.R;
import tk.thediamondyt.mobileconfig.MainActivity;
import tk.thediamondyt.mobileconfig.Utils;

public class MoreDetailsActivity extends AppCompatActivity {
	
	private Document plist = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_more_details);
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		plist = MainActivity.getPlist(); 
		NodeList nodeList = plist.getElementsByTagName("*");
	
        for(int i = 0; i < nodeList.getLength(); i++) {
            Node childNode = nodeList.item(i);
            if(childNode.getNodeName().equals("key") || isTrue(childNode) || isFalse(childNode)) {
                if(Utils.getTextValue(childNode).equals("PayloadRemovalDisallowed")) {
				    String value = isTrue(nodeList.item(i+1)) ? "No" : "Yes";
                    ((TextView) findViewById(R.id.payloadRemovalTextView)).setText(value);
                }
			} 
		} 
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId() == android.R.id.home)
			super.onBackPressed();
			
		return super.onOptionsItemSelected(item);
	}
	
	private boolean isTrue(Node node) {
		return node.getNodeName().equals("true");
	}

	private boolean isFalse(Node node) {
		return node.getNodeName().equals("false");
	}
}
