package com.amatellanes.android.examples;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemAdapter extends BaseAdapter {

	private Context context;
	private List<Item> items;

	public ItemAdapter(Context context, List<Item> items) {
		this.context = context;
		this.items = items;
	}

	@Override
	public int getCount() {
		return this.items.size();
	}

	@Override
	public Object getItem(int position) {
		return this.items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View rowView = convertView;

		if (convertView == null) {
			// Create a new view into the list.
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			rowView = inflater.inflate(R.layout.list_item, parent, false);
		}

		// Set data into the view.
		ImageView ivItem = (ImageView) rowView.findViewById(R.id.ivItem);
		TextView tvTitle = (TextView) rowView.findViewById(R.id.tvTitle);

		Item item = this.items.get(position);
		tvTitle.setText(item.getTitle());
		ivItem.setImageResource(item.getImage());

		return rowView;
	}

}
