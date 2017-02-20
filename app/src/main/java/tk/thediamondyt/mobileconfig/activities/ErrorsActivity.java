package tk.thediamondyt.mobileconfig.activities;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import tk.thediamondyt.mobileconfig.adapters.ErrorListAdapter;
import tk.thediamondyt.mobileconfig.models.Error;
import tk.thediamondyt.mobileconfig.R;

public class ErrorsActivity extends AppCompatActivity {
	
	private ArrayList<Error> errors = new ArrayList<Error>();
	private ErrorListAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_errors);
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		errors.add(new Error("Lol", "Something nlt right"));
		errors.add(new Error("Lol", "Something not right"));
		
		ListView list = (ListView) findViewById(R.id.errorsListView);
		adapter = new ErrorListAdapter(getApplicationContext(), errors);
		list.setAdapter(adapter);
	}
}
