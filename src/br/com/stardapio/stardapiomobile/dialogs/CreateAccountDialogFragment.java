package br.com.stardapio.stardapiomobile.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import br.com.stardapio.stardapiomobile.R;

public class CreateAccountDialogFragment extends DialogFragment {

	private View baseView;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		LayoutInflater inflater = getActivity().getLayoutInflater();
		baseView = inflater.inflate(R.layout.dialog_signin, null);

		builder.setView(baseView)
				.setPositiveButton(R.string.signin,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int id) {
								String username = ((EditText) baseView
										.findViewById(R.id.username)).getText()
										.toString();
								String password = ((EditText) baseView
										.findViewById(R.id.password)).getText()
										.toString();
								String confirmPassword = ((EditText) baseView
										.findViewById(R.id.confirm_password))
										.getText().toString();

								String msg;
								if (username.length() == 0) {
									msg = "Nome de usuário inválido";
								}
								else if( password.length() < 4) {
									msg = "Senha deve conter mais de 4 caracteres";
								}else if( !password.equals(confirmPassword)) {
									msg = "Senha não confere";
								} else {
									msg = "OK";
								}
								Toast.makeText(
										getActivity(),
										msg + " Username: " + username
												+ " Password: " + password
												+ " Confirm Password: "
												+ confirmPassword,
										Toast.LENGTH_LONG).show();
							}
						})
				.setNegativeButton(R.string.cancel,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								CreateAccountDialogFragment.this.getDialog()
										.cancel();
							}
						});
		return builder.create();
	}
}
