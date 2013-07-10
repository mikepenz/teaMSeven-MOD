package com.tundem.teamsevenmod.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fima.cardsui.views.CardUI;
import com.tundem.teamsevenmod.config.Cfg;
import com.tundem.teamsevenmod.entity.CPUSetting;
import com.tundem.teamsevenmod.helper.SettingHelper;
import com.tundem.teamsevensysfschanger.R;

public class CPUFragment extends Fragment {

	private CardUI mCardView = null;
	private SettingHelper settingHelper = null;

	public CPUFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this.settingHelper = new SettingHelper(getActivity());

		View rootView = inflater.inflate(R.layout.fragment_cards, container, false);

		// init CardView
		mCardView = (CardUI) rootView.findViewById(R.id.cardsview);
		mCardView.setSwipeable(false);
		mCardView.refresh();

		return rootView;
	}

	public void notifyRootAvailable() {
		for (CPUSetting cpuSetting : Cfg.cpuSettings) {
			try {
				mCardView.addCard(settingHelper.getSpinnerCard(cpuSetting));
			} catch (Exception e) {
				Log.e("com.tundem.teamsevenmod", "Error with CPUSettingCard");
			}
		}
		mCardView.refresh();

	}
}
