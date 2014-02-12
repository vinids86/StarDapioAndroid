package br.com.stardapio.stardapiomobile.utils;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import br.com.stardapio.stardapiomobile.MultiDrawable;
import br.com.stardapio.stardapiomobile.R;
import br.com.stardapio.stardapiomobile.bean.RestaurantAssociateCluster;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.google.maps.android.ui.IconGenerator;

public class AssociateRenderer extends
		DefaultClusterRenderer<RestaurantAssociateCluster> {
	private Activity activity;
	private final IconGenerator mIconGenerator;
	private final IconGenerator mClusterIconGenerator;
	private final ImageView mImageView;
	private final ImageView mClusterImageView;
	private final int mDimension;
	public Drawable drawable = null;

	public AssociateRenderer(Activity activity, GoogleMap map,
			ClusterManager<RestaurantAssociateCluster> clusterManager) {
		super(activity, map, clusterManager);
		// new GetAsyncTask().execute(clusterManager);
		this.activity = activity;
		mIconGenerator = new IconGenerator(activity);
		mClusterIconGenerator = new IconGenerator(activity);
		View multiProfile = this.activity.getLayoutInflater().inflate(
				R.layout.multi_profile, null);
		mClusterIconGenerator.setContentView(multiProfile);
		mClusterImageView = (ImageView) multiProfile.findViewById(R.id.image);

		mImageView = new ImageView(this.activity);
		mDimension = (int) this.activity.getResources().getDimension(
				R.dimen.custom_profile_image);
		mImageView.setLayoutParams(new ViewGroup.LayoutParams(mDimension,
				mDimension));
		int padding = (int) this.activity.getResources().getDimension(
				R.dimen.custom_profile_padding);
		mImageView.setPadding(padding, padding, padding, padding);
		mIconGenerator.setContentView(mImageView);
	}

	@Override
	protected void onBeforeClusterItemRendered(
			RestaurantAssociateCluster associate, MarkerOptions markerOptions) {
		// Draw a single person.
		// Set the info window to show their name.
		mImageView.setImageDrawable(associate.getDrawableImage());
		Bitmap icon = mIconGenerator.makeIcon();
		markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon)).title(
				associate.getName());
	}

	@Override
	protected void onBeforeClusterRendered(
			Cluster<RestaurantAssociateCluster> cluster,
			MarkerOptions markerOptions) {
		// Draw multiple people.
		// Note: this method runs on the UI thread. Don't spend too much
		// time in here (like in this example).
		// new GetAsyncTask(cluster, markerOptions).execute();
		Log.i("TAG", cluster.getItems().toString());
		List<Drawable> profilePhotos = new ArrayList<Drawable>(Math.min(4,
				cluster.getSize()));
		int width = mDimension;
		int height = mDimension;

		for (RestaurantAssociateCluster a : cluster.getItems()) {
			// Draw 4 at most.
			if (profilePhotos.size() == 4)
				break;
			Drawable drawable = a.getDrawableImage();
			drawable.setBounds(0, 0, width, height);
			profilePhotos.add(drawable);
		}

		MultiDrawable multiDrawable = new MultiDrawable(profilePhotos);
		multiDrawable.setBounds(0, 0, width, height);

		mClusterImageView.setImageDrawable(multiDrawable);
		Bitmap icon = mClusterIconGenerator.makeIcon(String.valueOf(cluster
				.getSize()));
		markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon)).title("INFO");

	}

	@Override
	protected boolean shouldRenderAsCluster(Cluster cluster) {
		// Always render clusters.
		return cluster.getSize() > 1;
	}

}
