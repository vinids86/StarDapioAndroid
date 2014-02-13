package br.com.stardapio.stardapiomobile;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import br.com.stardapio.stardapiomobile.adapters.TypesAdapter;
import br.com.stardapio.stardapiomobile.utils.Extras;
import br.com.stardapio.stardapiomobile.utils.Transaction;
import br.com.stardapio.stardapiomobile.utils.TransactionTask;

import com.stardapio.clientrest.ClientRest;
import com.stardapio.vos.CategoryVO;

public class TypesActivity extends ActionBarActivity implements Transaction {

	private List<CategoryVO> categories;
	ExpandableListAdapter adapter;
	ExpandableListView eListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_types);
		
		new TransactionTask(this, this, R.string.wait).execute();
		
		eListView = (ExpandableListView) findViewById(R.id.expandableType);
		eListView
				.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

					@Override
					public boolean onGroupClick(ExpandableListView parent,
							View v, int groupPosition, long id) {
						// Log.i("TAG", noChild() + "");
						if (adapter.getChildrenCount(groupPosition) == 0) {
							Log.i("TAG", "NOCHILD");
							goMenuSlide(id);
						} else {
							Log.i("TAG", "HASCHILD");
						}
						return false;
					}
				});

		eListView
				.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

					@Override
					public boolean onChildClick(ExpandableListView parent,
							View v, int groupPosition, int childPosition,
							long id) {
						goMenuSlide(id);
						return false;
					}
				});
	}

	protected void goMenuSlide(long id) {
		Intent intent = new Intent(this, MenuSlideActivity.class);
		intent.putExtra(Extras.ID_RESTAURANT,
				getIntent().getExtras().getString(Extras.ID_RESTAURANT));
		intent.putExtra("idType", String.valueOf(id));
		startActivity(intent);
	}
	
	public void scan() {
		startActivity(new Intent(this, QrCodeActivity.class));
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
		if (categories != null) {
			adapter = new TypesAdapter(this, categories);
			eListView.setAdapter(adapter);
		}
	}

}
