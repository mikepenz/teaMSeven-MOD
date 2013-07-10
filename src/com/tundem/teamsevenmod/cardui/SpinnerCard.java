package com.tundem.teamsevenmod.cardui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.fima.cardsui.objects.Card;
import com.tundem.teamsevensysfschanger.R;

public class SpinnerCard extends Card {
	private String settingText = "";
	private String description = "";
	private OnItemSelectedListener ossl = null;
	private ArrayAdapter<String> adapter;

	public SpinnerCard(String settingText, String description, ArrayAdapter<String> adapter, OnItemSelectedListener ossl) {
		super(settingText);

		this.settingText = settingText;
		this.description = description;
		this.ossl = ossl;
		this.adapter = adapter;
		
	}

	@Override
	public View getCardContent(Context context) {
		View view = LayoutInflater.from(context).inflate(R.layout.card_spinner, null);

		((TextView) view.findViewById(R.id.title)).setText(settingText);
		((Spinner) view.findViewById(R.id.entries)).setAdapter(adapter);
		((Spinner) view.findViewById(R.id.entries)).setOnItemSelectedListener(ossl);
		((TextView) view.findViewById(R.id.description)).setText(description);

		return view;
	}
}
