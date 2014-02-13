package br.com.stardapio.stardapiomobile.adapters;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.stardapio.stardapiomobile.R;
import br.com.stardapio.stardapiomobile.utils.WebOperations;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.stardapio.vos.CategoryVO;

public class TypesAdapter extends BaseExpandableListAdapter {

	private Context context;
	private List<CategoryVO> categories;

	public TypesAdapter(Context context, List<CategoryVO> categories) {
		this.context = context;
		this.categories = categories;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return ((List<CategoryVO>) categories.get(
				groupPosition).getSubCategories()).get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return ((List<CategoryVO>) categories.get(
				groupPosition).getSubCategories()).get(childPosition).getId();
		
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		return null;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return ((List<CategoryVO>) categories.get(
				groupPosition).getSubCategories()).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return categories.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return categories.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return categories.get(groupPosition).getId();
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.types_adapter, null);

		ImageView image = (ImageView) view.findViewById(R.id.image);
		TextView name = (TextView) view.findViewById(R.id.name);
		TextView description = (TextView) view.findViewById(R.id.description);
		
		ImageLoader.getInstance().displayImage(
				categories.get(groupPosition).getImage(), image);
		
		name.setText(categories.get(groupPosition).getName());
		Typeface tf = Typeface.createFromAsset(context.getAssets(),"fonts/Roboto-Black.ttf");
		name.setTypeface(tf,Typeface.BOLD);
		
		description.setText(categories.get(groupPosition).getSubNames());
		
		return view;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return false;
	}

}
