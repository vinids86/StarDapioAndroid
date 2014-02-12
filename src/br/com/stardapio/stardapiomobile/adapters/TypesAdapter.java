package br.com.stardapio.stardapiomobile.adapters;

import java.util.List;
import java.util.Set;

import com.stardapio.vos.CategoryVO;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

public class TypesAdapter extends BaseExpandableListAdapter {

	private Context context;
	private List<CategoryVO> categories;

	public TypesAdapter(Context context, List<CategoryVO> categories) {
		this.context = context;
		this.categories = categories;
	}
	
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		List<CategoryVO> subCategories = (List<CategoryVO>) categories.get(groupPosition).getSubCategories();
		return subCategories.get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		List<CategoryVO> subCategories = (List<CategoryVO>) categories.get(groupPosition).getSubCategories();
		return subCategories.get(childPosition).getId();
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		return null;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		List<CategoryVO> subCategories = (List<CategoryVO>) categories.get(groupPosition).getSubCategories();
		return subCategories.size();
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
		return null;
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
