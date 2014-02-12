package br.com.stardapio.stardapiomobile.bean;

import android.graphics.drawable.Drawable;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;
import com.stardapio.vos.RestaurantAssociate;

public class RestaurantAssociateCluster extends RestaurantAssociate implements ClusterItem {
	private final LatLng mPosition;
	private Drawable drawableImage;
	
	public Drawable getDrawableImage() {
		return drawableImage;
	}

	public void setDrawableImage(Drawable drawableImage) {
		this.drawableImage = drawableImage;
	}

	public RestaurantAssociateCluster(RestaurantAssociate restaurant){
		super(restaurant);
		Double latitude = restaurant.getPoint().getLatitude();
		Double longitude = restaurant.getPoint().getLongitude();
		mPosition = new LatLng(latitude, longitude);
	}

	@Override
	public LatLng getPosition() {
		return mPosition;
	}
}