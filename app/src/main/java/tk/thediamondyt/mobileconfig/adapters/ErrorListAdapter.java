package tk.thediamondyt.mobileconfig.adapters;

import tk.thediamondyt.mobileconfig.models.Error;
import tk.thediamondyt.mobileconfig.R;

import android.widget.BaseAdapter;
import android.widget.TextView;
import android.content.Context;
import android.view.*;

import java.util.ArrayList;

public class ErrorListAdapter extends BaseAdapter {

	private ArrayList<Error> types;
	private LayoutInflater inflater;

	public class ViewHolder {
		public TextView title;
	}

	public ErrorListAdapter(Context context, ArrayList<Error> types) {
		this.types = types;
		this.inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return types.size();
	}

	@Override
	public Object getItem(int position) {
		return types.get(position);	
	}

	@Override
	public long getItemId(int position) {
	    return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		Error type = types.get(position);
		ViewHolder holder;

		if(view == null) {
			view = inflater.inflate(R.layout.error_list_item, null);
			holder = new ViewHolder();
			holder.title = (TextView) view.findViewById(R.id.title);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}	

		holder.title.setText(type.getText());
		return view;
	}
}
