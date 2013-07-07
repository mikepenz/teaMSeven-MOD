package com.tundem.teamsevenmod.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fima.cardsui.objects.CardStack;
import com.fima.cardsui.views.CardUI;
import com.tundem.teamsevenmod.cardui.LibraryCard;
import com.tundem.teamsevensysfschanger.R;

public class AboutFragment extends Fragment {

	private CardUI mCardView;

	public AboutFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_cards, container, false);

		// init CardView
		mCardView = (CardUI) rootView.findViewById(R.id.cardsview);
		mCardView.setSwipeable(true);

		CardStack cs = new CardStack();
		cs.setTitle(getString(R.string.aboutapp));

		String version = "1.0.0";
		try {
			version = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0).versionName;
		} catch (Exception ex) {
		}

		cs.add(new LibraryCard(getString(R.string.app_name), "Mike Penz", getString(R.string.aboutthisapplicationdescr), "v" + version));
		mCardView.addStack(cs);

		CardStack csl = new CardStack();
		csl.setTitle(getString(R.string.libraries));
		csl.add(new LibraryCard("CardsUI", "nadavfirm", getString(R.string.library_cardsui), "#14"));
		csl.add(new LibraryCard("Crouton", "keyboardsurfer", getString(R.string.library_crouton), "v1.8.1"));
		mCardView.addStack(csl);

		mCardView.refresh();

		return rootView;
	}
}
