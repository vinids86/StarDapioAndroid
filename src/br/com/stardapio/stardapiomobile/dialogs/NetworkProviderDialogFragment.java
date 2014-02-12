package br.com.stardapio.stardapiomobile.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class NetworkProviderDialogFragment extends DialogFragment {
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		Builder dialog = new AlertDialog.Builder(getActivity());
		dialog.setMessage("Ative recurso de localização");
		dialog.setPositiveButton("Setting",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface paramDialogInterface,
							int paramInt) {
						startActivityForResult(
								new Intent(
										android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS),
								100);
					}
				});
		dialog.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface paramDialogInterface,
							int paramInt) {

					}
				});

		return dialog.create();
	}
}
