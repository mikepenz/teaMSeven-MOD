package com.tundem.teamsevenmod.cardui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.fima.cardsui.objects.Card;
import com.tundem.teamsevensysfschanger.R;

public class LibraryCard extends Card {
	private String libraryName = "";
	private String libraryCreator = "";
	private String description = "";
	private String libraryVersion = "";

	public LibraryCard(String libraryName, String libraryCreator, String description, String libraryVersion) {
		super(libraryName);
		
		this.libraryName = libraryName;
		this.libraryCreator = libraryCreator;
		this.description = description;
		this.libraryVersion = libraryVersion;
	}

	@Override
	public View getCardContent(Context context) {
		View view = LayoutInflater.from(context).inflate(R.layout.card_library, null);

		((TextView) view.findViewById(R.id.libraryname)).setText(libraryName);
		((TextView) view.findViewById(R.id.librarycreator)).setText(libraryCreator);
		((TextView) view.findViewById(R.id.libraryversion)).setText(libraryVersion);
		((TextView) view.findViewById(R.id.description)).setText(description);

		return view;
	}
}
