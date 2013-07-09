package com.tundem.teamsevenmod.fragment;

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
		mCardView.setSwipeable(false);
		mCardView.refresh();

		return rootView;
	}

	public void notifyRootAvailable() {
		for (MiscSetting miscSetting : Cfg.miscSettings) {
			settingHelper.initSetting(miscSetting.getSettingPath());
			mCardView.addCard(settingHelper.getCard(miscSetting));
		}

		mCardView.refresh();
	}
}
