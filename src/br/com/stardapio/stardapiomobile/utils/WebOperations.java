package br.com.stardapio.stardapiomobile.utils;

import java.io.InputStream;
import java.net.URL;

import android.graphics.drawable.Drawable;

public class WebOperations {

	public static Drawable LoadImageFromWebOperations(String url) {
		try {
			InputStream is = (InputStream) new URL(url).getContent();
			return Drawable.createFromStream(is, "src name");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
