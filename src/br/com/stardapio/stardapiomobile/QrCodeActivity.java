package br.com.stardapio.stardapiomobile;

import jim.h.common.android.zxinglib.integrator.IntentIntegrator;
import jim.h.common.android.zxinglib.integrator.IntentResult;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

public class QrCodeActivity extends Activity {
	private Handler handler = new Handler();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qr_code);

		IntentIntegrator.initiateScan(QrCodeActivity.this, R.layout.capture,
				R.id.viewfinder_view, R.id.preview_view, true);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case IntentIntegrator.REQUEST_CODE:
			IntentResult scanResult = IntentIntegrator.parseActivityResult(
					requestCode, resultCode, data);
			if (scanResult == null) {
				return;
			}
			final String result = scanResult.getContents();
			if (result != null) {
				handler.post(new Runnable() {
					@Override
					public void run() {
						// txtScanResult.setText(result);
						Log.i("PEDIDO", result);
					}
				});
				Toast.makeText(this, result, Toast.LENGTH_LONG).show();
			}
			break;
		default:
		}
		finish();
	}
}
