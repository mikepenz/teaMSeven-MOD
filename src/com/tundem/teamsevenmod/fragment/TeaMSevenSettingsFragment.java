package com.tundem.teamsevenmod.fragment;

import java.io.File;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fima.cardsui.objects.CardStack;
import com.fima.cardsui.views.CardUI;
import com.tundem.teamsevenmod.cardui.LibraryCard;
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
		Log.d("MODEL","---------------------------"+ android.os.Build.DEVICE+"------------------------------");

		// init CardView
		mCardView = (CardUI) rootView.findViewById(R.id.cardsview);
		mCardView.setSwipeable(false);
		mCardView.refresh();

		return rootView;
	}

	public void notifyRootAvailable() {
		for (MiscSetting miscSetting : Cfg.miscSettings) {
			//settingHelper.initSetting(miscSetting.getSettingPath());
			File file = new File(miscSetting.getSettingPath());
			if(file.exists()){
				mCardView.addCard(settingHelper.getCard(miscSetting));
			}
		}
		CardStack csl = new CardStack();
		csl.setTitle(getString(R.string.cpustack));
		for (MiscSetting misc : Cfg.cpuSettings){
			//settingHelper.initSetting(misc.getSettingPath());
			String filePath = "";
			if(misc.getSettingName().contains("max")){
				filePath = "/sys/devices/system/cpu/cpu0/cpufreq/scaling_max_freq";
			}
			else if (misc.getSettingName().contains("min")){
				filePath = "/sys/devices/system/cpu/cpu0/cpufreq/scaling_min_freq";
			}
			else if (misc.getSettingName().contains("Governor")){
				filePath = "/sys/devices/system/cpu/cpu0/cpufreq/scaling_governor";
			}
			else if (misc.getSettingName().contains("Scheduler")){
				filePath = "/sys/block/mmcblk0/queue/scheduler";
			}
			try {
				csl.add(settingHelper.getSpinnerCard(misc, filePath));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		mCardView.addStack(csl);
		mCardView.refresh();

	}
}
