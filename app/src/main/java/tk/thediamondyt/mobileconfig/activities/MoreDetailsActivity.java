package tk.thediamondyt.mobileconfig.activities;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import tk.thediamondyt.mobileconfig.R;

public class MoreDetailsActivity extends AppCompatActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_more_details);
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId() == android.R.id.home)
			super.onBackPressed();
			
		return super.onOptionsItemSelected(item);
	}
}
