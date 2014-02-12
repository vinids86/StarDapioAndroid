package br.com.stardapio.stardapiomobile.fragment;

import static br.com.stardapio.stardapiomobile.utils.WebOperations.LoadImageFromWebOperations;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import br.com.stardapio.stardapiomobile.StarDapioActivity;
import br.com.stardapio.stardapiomobile.bean.RestaurantAssociateCluster;
import br.com.stardapio.stardapiomobile.dialogs.NetworkProviderDialogFragment;
import br.com.stardapio.stardapiomobile.utils.AssociateRenderer;
import br.com.stardapio.stardapiomobile.utils.BasicRenderer;
import br.com.stardapio.stardapiomobile.utils.CompositeListener;
import br.com.stardapio.stardapiomobile.utils.ConvertLayer;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterManager;
import com.stardapio.clientrest.ClientRest;
import com.stardapio.vos.ResultRestaurants;

public class MapFragment extends SupportMapFragment {

	private ResultRestaurants commercialPoints = null;
	private GoogleMap mMap;
	private CompositeListener composite;
	private ClusterManager<RestaurantAssociateCluster> mClusterManagerRestaurant;
	private ClusterManager<RestaurantAssociateCluster> mClusterManagerAssociate;
	private StarDapioActivity activity;
	private List<RestaurantAssociateCluster> restaurantsAssociates = new ArrayList<RestaurantAssociateCluster>();
	private List<RestaurantAssociateCluster> restaurantsBasic = new ArrayList<RestaurantAssociateCluster>();

	public void onResume() {
		super.onResume();
		mMap = getMap();

		activity = (StarDapioActivity) getActivity();
		composite = new CompositeListener();

		Location location = ((LocationManager) activity
				.getSystemService(Context.LOCATION_SERVICE))
				.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

		if (location == null) {
			new NetworkProviderDialogFragment().show(
					activity.getSupportFragmentManager(), "networkProvider");
		} else {

			LatLng cameraInitial = new LatLng(location.getLatitude(),
					location.getLongitude());

			try {
				commercialPoints = new ClientRest().findCommercialPoints(
						cameraInitial.latitude, cameraInitial.longitude);
			} catch (Exception e) {
				System.out.println("Fuuuuu...");
			}

			List<RestaurantAssociateCluster> restaurants = ConvertLayer
					.convertToCluster((commercialPoints
							.getRestauranteAssociates()));

			separateRestaurants(restaurants);

			mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cameraInitial,
					15.5f));

			addRestaurantsBasic();
			addRestaurantsAssociate();

			mMap.setOnCameraChangeListener(composite);
			mMap.setOnMarkerClickListener(composite);
			mMap.setOnInfoWindowClickListener(composite);

			mClusterManagerAssociate.setOnClusterClickListener(activity);
			mClusterManagerAssociate
					.setOnClusterInfoWindowClickListener(activity);
			mClusterManagerAssociate.setOnClusterItemClickListener(activity);
			mClusterManagerAssociate
					.setOnClusterItemInfoWindowClickListener(activity);
			
			mClusterManagerRestaurant.setOnClusterClickListener(activity);
			mClusterManagerRestaurant
					.setOnClusterInfoWindowClickListener(activity);
			mClusterManagerRestaurant.setOnClusterItemClickListener(activity);
			mClusterManagerRestaurant
					.setOnClusterItemInfoWindowClickListener(activity);

			mClusterManagerRestaurant.cluster();
			mClusterManagerAssociate.cluster();

			// mMap.setMyLocationEnabled(true);
		}
	}

	private void addRestaurantsBasic() {

		mClusterManagerRestaurant = new ClusterManager<RestaurantAssociateCluster>(
				getActivity(), mMap);
		mClusterManagerRestaurant.setRenderer(new BasicRenderer(
				getActivity(), mMap, mClusterManagerRestaurant));
		composite.registerCameraChangeListener(mClusterManagerRestaurant);
		composite.registerMarkerClickListener(mClusterManagerRestaurant);
		composite.registerInfoWindowClickListener(mClusterManagerRestaurant);
		mClusterManagerRestaurant.addItems(restaurantsBasic);
	}

	private void addRestaurantsAssociate() {

		mClusterManagerAssociate = new ClusterManager<RestaurantAssociateCluster>(
				getActivity(), mMap);
		mClusterManagerAssociate.setRenderer(new AssociateRenderer(
				getActivity(), mMap, mClusterManagerAssociate));
		composite.registerCameraChangeListener(mClusterManagerAssociate);
		composite.registerMarkerClickListener(mClusterManagerAssociate);
		composite.registerInfoWindowClickListener(mClusterManagerAssociate);
		new GetAsync().execute(restaurantsAssociates);
	}

	private void separateRestaurants(

	List<RestaurantAssociateCluster> restaurants) {
		for (RestaurantAssociateCluster r : restaurants) {
			if (r.isAssociate()) {
				restaurantsAssociates.add(r);
			} else {
				restaurantsBasic.add(r);
			}
		}
	}

	private class GetAsync
			extends
			AsyncTask<List<RestaurantAssociateCluster>, Void, List<RestaurantAssociateCluster>> {

		@Override
		protected List<RestaurantAssociateCluster> doInBackground(
				List<RestaurantAssociateCluster>... restaurants) {
			for (RestaurantAssociateCluster r : restaurants[0]) {
				r.setDrawableImage(LoadImageFromWebOperations(r.getImage()));
			}
			return restaurants[0];
		}

		@Override
		protected void onPostExecute(List<RestaurantAssociateCluster> result) {
			mClusterManagerAssociate.addItems(result);
		}

	}
}
