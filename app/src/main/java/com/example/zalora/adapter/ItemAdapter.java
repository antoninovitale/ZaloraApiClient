package com.example.zalora.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zalora.ApplicationStatus;
import com.example.zalora.model.Result;
import com.example.zalora.utils.ImageManager;
import com.example.zalora.R;

public class ItemAdapter extends BaseAdapter {
	private ImageManager imageManager;
	private Context context;
	private static final int DEFAULT_NO_IMAGE = R.drawable.ic_launcher;
	
	public ItemAdapter() {
		this.context = ApplicationStatus.getInstance().getApplicationContext();
		imageManager = new ImageManager(context);
	}
	
	public ItemAdapter(Context context) {
		this.context = context;
		imageManager = new ImageManager(context);
	}

	@Override
	public int getCount() {
		if(ApplicationStatus.getInstance().getResults()!=null)
			return ApplicationStatus.getInstance().getResults().size();
		else
			return 0;
	}

	@Override
	public Object getItem(int position) {
		if(ApplicationStatus.getInstance().getResults()!=null)
			return ApplicationStatus.getInstance().getResults().get(position);
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
			convertView = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
			holder = new Holder();
			holder.name = (TextView) convertView.findViewById(R.id.name);
			holder.brand = (TextView) convertView.findViewById(R.id.brand);
			holder.price = (TextView) convertView.findViewById(R.id.price);
			holder.image = (ImageView) convertView.findViewById(R.id.image);
			holder.container = (ViewGroup) convertView
					.findViewById(R.id.container);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		Result result = (Result) getItem(position);
		if(result!=null) {
			if(result.getImages()!=null)
				imageManager.displayImage(result.getImages().get(0).getPath(), holder.image, holder.image.getWidth(), DEFAULT_NO_IMAGE);
			if(result.getData()!=null) {
				holder.name.setText(result.getData().getName());
				holder.price.setText(result.getData().getPrice()+ context.getString(R.string.euro_symbol));
			}
							
		}
		return convertView;
	}
	
	static class Holder {
		TextView name;
		TextView brand;
		TextView price;
		ImageView image;
		ViewGroup container;
	}

}
