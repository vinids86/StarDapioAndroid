package br.com.stardapio.stardapiomobile.utils;

import android.content.Context;
import br.com.stardapio.stardapiomobile.bean.RestaurantAssociateCluster;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;

public class BasicRenderer extends
		DefaultClusterRenderer<RestaurantAssociateCluster> {

	public BasicRenderer(Context context, GoogleMap map,
			ClusterManager<RestaurantAssociateCluster> clusterManager) {
		super(context, map, clusterManager);
	}

	@Override
	protected void onBeforeClusterItemRendered(
			RestaurantAssociateCluster associate, MarkerOptions markerOptions) {
		markerOptions.icon(null).title(
				associate.getName());
	}
}
