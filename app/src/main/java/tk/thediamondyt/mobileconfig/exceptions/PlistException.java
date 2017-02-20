package tk.thediamondyt.mobileconfig.exceptions;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AlertDialog;
import android.content.*;

public class PlistException extends Exception {
    
    public PlistException(final AppCompatActivity activity, String text) {
        super(text);
		new AlertDialog.Builder(activity)
		    .setTitle("Oh noes it broke :(").setMessage(text)
			.setPositiveButton("Alright", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int p1) {
					activity.finish();
				}
			}).create().show();
    }
}
