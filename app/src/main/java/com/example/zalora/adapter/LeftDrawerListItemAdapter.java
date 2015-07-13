package com.example.zalora.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zalora.LeftDrawerListItem;
import com.example.zalora.R;

public class LeftDrawerListItemAdapter extends BaseAdapter {
	private Context context;
	private List<LeftDrawerListItem> items;
	
	public LeftDrawerListItemAdapter(Context context, List<LeftDrawerListItem> items) {
		this.context = context;
		this.items = items;
	}

	@Override
	public int getCount() {
		if(items!=null)
			return items.size();
		else
			return 0;
	}

	@Override
	public Object getItem(int position) {
		if(items!=null)
			return items.get(position);
		else return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.drawer_list_item_layout, parent, false);
			holder = new Holder();
			holder.name = (TextView) convertView.findViewById(R.id.name);
			holder.image = (ImageView) convertView.findViewById(R.id.image);
			holder.container = (ViewGroup) convertView
					.findViewById(R.id.container);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		LeftDrawerListItem leftDrawerListItem = (LeftDrawerListItem) getItem(position);
		if(leftDrawerListItem!=null) {
			holder.name.setText(leftDrawerListItem.getName());
		}
		return convertView;
	}
	
	static class Holder {
		TextView name;
		ImageView image;
		ViewGroup container;
	}

}
