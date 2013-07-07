package com.tundem.teamsevenmod.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fima.cardsui.views.CardUI;
import com.tundem.teamsevenmod.config.Cfg;
import com.tundem.teamsevenmod.entity.MiscSetting;
import com.tundem.teamsevenmod.helper.SettingHelper;
import com.tundem.teamsevensysfschanger.R;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import eu.chainfire.libsuperuser.Shell;

public class TeaMSevenSettingsFragment extends Fragment {

	private CardUI mCardView = null;
	private SettingHelper settingHelper = null;

	public TeaMSevenSettingsFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this.settingHelper = new SettingHelper(getActivity());

		View rootView = inflater.inflate(R.layout.fragment_cards, container, false);

		// init CardView
		mCardView = (CardUI) rootView.findViewById(R.id.cardsview);
		mCardView.setSwipeable(true);
		mCardView.refresh();

		// Let's do some background stuff
		(new Initialize()).setContext(getActivity()).execute();

		return rootView;
	}

	private class Initialize extends AsyncTask<Void, Void, Void> {
		private ProgressDialog dialog = null;
		private Context context = null;
		private boolean suAvailable = false;

		//private String suVersion = null;
		//private String suVersionInternal = null;
		//private List<String> suResult = null;

		public Initialize setContext(Context context) {
			this.context = context;
			return this;
		}

		@Override
		protected void onPreExecute() {
			// We're creating a progress dialog here because we want the user to wait.
			// If in your app your user can just continue on with clicking other things,
			// don't do the dialog thing.

			dialog = new ProgressDialog(context);
			dialog.setTitle("Aquire ROOT");
			dialog.setMessage("Waiting for ROOT permission ...");
			dialog.setIndeterminate(true);
			dialog.setCancelable(false);
			dialog.show();
		}

		@Override
		protected Void doInBackground(Void... params) {
			// Let's do some SU stuff
			suAvailable = Shell.SU.available();

			/*
			if (suAvailable) {
				suVersion = Shell.SU.version(false);
				suVersionInternal = Shell.SU.version(true);
				suResult = Shell.SU.run(new String[] {
					"id",
					"ls -l /"
				});
			}
			*/

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			dialog.dismiss();

			if (suAvailable) {
				Crouton.showText(getActivity(), "ROOT permission aquired", Style.INFO);

				// output
				/*
				StringBuilder sb = (new StringBuilder()).
					append("Root? ").append(suAvailable ? "Yes" : "No").append((char)10).
					append("Version: ").append(suVersion == null ? "N/A" : suVersion).append((char)10).
					append("Version (internal): ").append(suVersionInternal == null ? "N/A" : suVersionInternal).append((char)10).
					append((char)10);
				if (suResult != null) {
					for (String line : suResult) {
						sb.append(line).append((char)10);
					}
				}
				mCardView.addCard(new SettingCard("TestSetting", sb.toString()));
				*/

				for(MiscSetting miscSetting : Cfg.miscSettings) {
					settingHelper.initSetting(miscSetting.getSettingPath());
					mCardView.addCard(settingHelper.getCard(miscSetting));
				}

				mCardView.refresh();

				//Crouton.showText(getActivity(), sb.toString(), Style.INFO);
				//((EditText)findViewById(R.id.text)).setText(sb.toString());
			} else {
				Crouton.showText(getActivity(), "ROOT permission rejected", Style.ALERT);
			}
		}
	}
}
