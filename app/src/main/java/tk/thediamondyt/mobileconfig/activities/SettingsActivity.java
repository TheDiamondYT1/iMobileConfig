package tk.thediamondyt.mobileconfig.activities;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import tk.thediamondyt.mobileconfig.R;
import tk.thediamondyt.mobileconfig.fragments.SettingsFragment;

public class SettingsActivity extends AppCompatActivity {

	@Override
	public void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsFragment()).commit();
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId() == android.R.id.home)
			super.onBackPressed();

		return super.onOptionsItemSelected(item);
	}
}
