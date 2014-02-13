package br.com.stardapio.stardapiomobile.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class TransactionTask extends AsyncTask<Void, Void, Boolean> {

	private Context context;
	private Transaction transaction;
	private int waitMessage;
	private Throwable exceptionError;
	private ProgressDialog progress;

	public TransactionTask(Context context, Transaction transaction,
			int waitMessage) {
		this.context = context;
		this.transaction = transaction;
		this.waitMessage = waitMessage;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		openProgress();
	}

	@Override
	protected Boolean doInBackground(Void... params) {
		try {
			transaction.execute();
		} catch (Throwable e) {
			Log.e(Extras.TAG, e.getMessage(), e);
			this.exceptionError = e;
			return false;
		} finally {
			try {
				closeProgress();
			} catch (Exception e) {
				Log.e(Extras.TAG, e.getMessage(), e);
			}
		}
		return true;
	}

	@Override
	protected void onPostExecute(Boolean result) {
		if(result) {
			transaction.updateView();
		} else {
			// AlertDialog erro
		}
	}

	private void openProgress() {
		try {
			progress = ProgressDialog.show(context, "",
					context.getString(waitMessage));
		} catch (Throwable e) {
			Log.e(Extras.TAG, e.getMessage(), e);
		}
	}

	private void closeProgress() {
		try {
			if (progress != null) {
				progress.dismiss();
			}
		} catch (Throwable e) {
			Log.e(Extras.TAG, e.getMessage(), e);
		}
	}

}
