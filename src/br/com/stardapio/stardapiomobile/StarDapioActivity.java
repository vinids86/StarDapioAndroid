package br.com.stardapio.stardapiomobile;

import java.util.HashSet;
import java.util.Set;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import br.com.stardapio.stardapiomobile.bean.RestaurantAssociateCluster;
import br.com.stardapio.stardapiomobile.dialogs.DisplayAccountDialogFragment;
import br.com.stardapio.stardapiomobile.fragment.MapFragment;
import br.com.stardapio.stardapiomobile.utils.Extras;

import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class StarDapioActivity extends FragmentActivity
		implements
		ClusterManager.OnClusterClickListener<RestaurantAssociateCluster>,
		ClusterManager.OnClusterInfoWindowClickListener<RestaurantAssociateCluster>,
		ClusterManager.OnClusterItemClickListener<RestaurantAssociateCluster>,
		ClusterManager.OnClusterItemInfoWindowClickListener<RestaurantAssociateCluster> {

	private AccountManager mAccountManager;
	private CharSequence[] names;
	private ImageLoaderConfiguration config;

	private MapFragment mapFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		displayAccounts();

		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
				.cacheInMemory().cacheOnDisc().build();
		config = new ImageLoaderConfiguration.Builder(getApplicationContext())
				.defaultDisplayImageOptions(defaultOptions).build();
		ImageLoader.getInstance().init(config);

	}

	@Override
	protected void onResume() {
		super.onResume();
		setUpMapIfNeeded();
	}

	private void setUpMapIfNeeded() {
		if (mapFragment != null) {
			return;
		}
		mapFragment = new MapFragment();
		FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();
		transaction.replace(R.id.map, mapFragment);
		transaction.commit();
	}

	private void displayAccounts() {
		DisplayAccountDialogFragment displayAccountDialogFragment = new DisplayAccountDialogFragment();
		Bundle args = new Bundle();
		args.putCharSequenceArray("accountNames", getAccountNames());
		displayAccountDialogFragment.setArguments(args);
		displayAccountDialogFragment.show(getSupportFragmentManager(),
				"displayAccount");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.stardapio, menu);
		return true;
	}

	private CharSequence[] getAccountNames() {
		Set<CharSequence> aux;
		mAccountManager = AccountManager.get(this);
		Account[] accounts = mAccountManager.getAccounts();
		aux = new HashSet<CharSequence>();
		for (int i = 0; i < accounts.length; i++) {
			aux.add(accounts[i].name);
		}

		names = (CharSequence[]) aux.toArray(new CharSequence[aux.size()]);
		names[names.length - 1] = Extras.ADD_ACCOUNT;
		return names;
	}

	public String getBestProvider() {
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_COARSE);
		criteria.setAltitudeRequired(false);
		criteria.setCostAllowed(true);
		criteria.setPowerRequirement(Criteria.POWER_HIGH);
		return ((LocationManager) getSystemService(Context.LOCATION_SERVICE))
				.getBestProvider(criteria, true);
	}

	@Override
	public void onClusterItemInfoWindowClick(RestaurantAssociateCluster item) {
		Intent intent = new Intent(this, TypesActivity.class);
		intent.putExtra(Extras.ID_RESTAURANT, item.getId());
		startActivity(intent);
	}

	@Override
	public boolean onClusterItemClick(RestaurantAssociateCluster item) {
		return false;
	}

	@Override
	public void onClusterInfoWindowClick(
			Cluster<RestaurantAssociateCluster> cluster) {
	}

	@Override
	public boolean onClusterClick(Cluster<RestaurantAssociateCluster> cluster) {
		return false;
	}
}
