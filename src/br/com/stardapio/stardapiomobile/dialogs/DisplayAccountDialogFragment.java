package br.com.stardapio.stardapiomobile.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class DisplayAccountDialogFragment extends DialogFragment {
	private CharSequence[] accountNames;
	private CharSequence selected;

	private static final String ADD_ACCOUNT = "Adicionar conta";

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		accountNames = (CharSequence[]) getArguments().getCharSequenceArray(
				"accountNames");
		
		selected = accountNames[0];

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		builder.setTitle("Escolha uma Conta")
				.setSingleChoiceItems(accountNames, 0,
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {

								/* User clicked on a radio button do some stuff */
								selected = accountNames[which];
							}
						})
				.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						if (selected.equals(ADD_ACCOUNT)) {
							new CreateAccountDialogFragment().show(
									DisplayAccountDialogFragment.this
											.getActivity()
											.getSupportFragmentManager(),
									"createAccount");
						}
					}
				})
				.setNegativeButton("Cancelar",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								DisplayAccountDialogFragment.this.getDialog()
										.cancel();
							}
						});

		return builder.create();
	}
}
