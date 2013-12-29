package com.amatellanes.pelicularest;

import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ItemAdapter extends BaseAdapter {

	private Context context;
	private List<Pelicula> items;

	public ItemAdapter(Context context, List<Pelicula> items) {
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

		// Create a new view into the list.
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.list_item, parent, false);

		// Set data into the view.
		ImageView ivItem = (ImageView) rowView.findViewById(R.id.ivPoster);
		TextView tvTitle = (TextView) rowView.findViewById(R.id.tvTitle);
		TextView tvWritters = (TextView) rowView.findViewById(R.id.tvWritters);
		TextView tvActors = (TextView) rowView.findViewById(R.id.tvActors);
		TextView tvPlot = (TextView) rowView.findViewById(R.id.tvPlot);

		Pelicula item = this.items.get(position);
		if (item != null) {
			tvTitle.setText(item.getTitle());
			tvWritters.setText(Arrays.toString(item.getWriters()));
			tvActors.setText(Arrays.toString(item.getActors()));
			tvPlot.setText(item.getPlot_simple());
			if (item.getPoster() != null && item.getPoster().getImdb() != null) {
				Picasso.with(context).load(item.getPoster().getImdb())
						.into(ivItem);
			}
		}

		return rowView;
	}

}
