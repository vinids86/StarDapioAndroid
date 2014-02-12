package br.com.stardapio.stardapiomobile;

import java.util.List;

import com.stardapio.clientrest.ClientRest;
import com.stardapio.vos.CategoryVO;

import br.com.stardapio.stardapiomobile.utils.Transaction;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.support.v7.app.ActionBarActivity;

public class TypesActivity extends ActionBarActivity implements Transaction {

	private List<CategoryVO> categories;
	ExpandableListAdapter adapter;
	ExpandableListView eListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_types);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.types, menu);
		return true;
	}

	@Override
	public void execute() throws Exception {
		categories = new ClientRest().findCategories(1);
	}

	@Override
	public void updateView() {
		if(categories != null) {
			eListView.setAdapter(adapter);
		}
	}

}
