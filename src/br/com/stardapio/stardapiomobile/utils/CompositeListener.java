package br.com.stardapio.stardapiomobile.utils;

import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Marker;

public class CompositeListener implements OnCameraChangeListener,
		OnMarkerClickListener, OnInfoWindowClickListener {

	private List<OnCameraChangeListener> cameraChangeListener = new ArrayList<OnCameraChangeListener>();;
	private List<OnMarkerClickListener> markerClickListener = new ArrayList<OnMarkerClickListener>();;
	private List<OnInfoWindowClickListener> infoWindowClickListener = new ArrayList<OnInfoWindowClickListener>();;

	public void registerCameraChangeListener(OnCameraChangeListener listener) {
		cameraChangeListener.add(listener);
	}

	public void registerMarkerClickListener(OnMarkerClickListener listener) {
		markerClickListener.add(listener);
	}

	public void registerInfoWindowClickListener(
			OnInfoWindowClickListener listener) {
		infoWindowClickListener.add(listener);
	}

	@Override
	public void onCameraChange(CameraPosition event) {
		for (OnCameraChangeListener listener : cameraChangeListener)
			listener.onCameraChange(event);
	}

	@Override
	public boolean onMarkerClick(Marker marker) {
		for (OnMarkerClickListener listener : markerClickListener)
			listener.onMarkerClick(marker);
		return false;
	}

	@Override
	public void onInfoWindowClick(Marker marker) {
		for(OnInfoWindowClickListener listener : infoWindowClickListener)
			listener.onInfoWindowClick(marker);
	}
}
