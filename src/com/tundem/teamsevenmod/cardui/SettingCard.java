package com.tundem.teamsevenmod.cardui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.TextView;

import com.fima.cardsui.objects.Card;
import com.tundem.teamsevensysfschanger.R;

public class SettingCard extends Card {
	private String settingText = "";
	private String description = "";
	private boolean enabled = false;
	private OnCheckedChangeListener occl = null;

	public SettingCard(String settingText, String description, boolean enabled, OnCheckedChangeListener occl) {
		super(settingText);

		this.enabled = enabled;
		this.settingText = settingText;
		this.description = description;
		this.occl = occl;
	}

	@Override
	public View getCardContent(Context context) {
		View view = LayoutInflater.from(context).inflate(R.layout.card_setting, null);

		((Switch) view.findViewById(R.id.setting)).setText(settingText);
		((Switch) view.findViewById(R.id.setting)).setChecked(enabled);
		((Switch) view.findViewById(R.id.setting)).setOnCheckedChangeListener(occl);
		((TextView) view.findViewById(R.id.description)).setText(description);

		return view;
	}
}
