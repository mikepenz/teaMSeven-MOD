package com.tundem.teamsevenmod.helper;

import java.util.List;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.tundem.teamsevenmod.cardui.SettingCard;
import com.tundem.teamsevenmod.cardui.SpinnerCard;
import com.tundem.teamsevenmod.entity.CPUSetting;
import com.tundem.teamsevenmod.entity.MiscSetting;
import com.tundem.teamsevenmod.util.FileHelper;
import com.tundem.teamsevenmod.util.FileHelper.ChangePermission;
import com.tundem.teamsevensysfschanger.R;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class SettingHelper {
	public static final String PREFS_NAME = "com.tundem.teamsevenmod-PREFS";

	SharedPreferences settings = null;
	SharedPreferences.Editor editor = null;
	Activity act = null;

	public SettingHelper(Activity act) {
		this.act = act;

		this.settings = act.getSharedPreferences(PREFS_NAME, 0);
		this.editor = this.settings.edit();
	}

	public SettingCard getCard(final MiscSetting miscSetting) {
		boolean enabled = false;
		try {
			String settingFile = FileHelper.getStringFromFile(miscSetting.getSettingPath());
			settingFile = settingFile.replace("\n", "");
			if (settingFile.equals(miscSetting.getValueDisabled())) {
				enabled = false;
			} else if (settingFile.equals(miscSetting.getValueEnabled())) {
				enabled = true;
			}

			//Set setting from read file!
			String setting = buildSetting(enabled, miscSetting.getValueEnabled(), miscSetting.getValueDisabled());
			editor.putString(miscSetting.getSettingPath(), setting);
			editor.commit();
		} catch (Exception e) {
			Log.e("com.tundem.teamsevenmod", miscSetting.getSettingName() + "Error: " + e.toString());
		}

		SettingCard setting = new SettingCard(miscSetting.getSettingName(), miscSetting.getSettingDescr(), enabled, new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				String setting = buildSetting(isChecked, miscSetting.getValueEnabled(), miscSetting.getValueDisabled());

				boolean result = FileHelper.writeStringToFile(miscSetting.getSettingPath(), setting);

				if (result) {
					String changedTo = act.getString(R.string.disabled);
					if (isChecked) {
						changedTo = act.getString(R.string.enabled);
					}

					Crouton.showText(act, miscSetting.getSettingName() + " changed to: " + changedTo, Style.INFO);

					//Set setting from selection
					editor.putString(miscSetting.getSettingPath(), setting);
					editor.commit();
				} else {
					Crouton.showText(act, "ERROR: " + miscSetting.getSettingName() + " not saved", Style.ALERT);
					buttonView.setChecked(!isChecked);
				}
			}
		});

		return setting;
	}


	public SpinnerCard getSpinnerCard(final CPUSetting cpuSetting) throws Exception {

		List<String> spinnerContent = FileHelper.getFileContentAsList(cpuSetting.getSettingDefaultsPath());

		ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(act,
				android.R.layout.simple_spinner_dropdown_item,
				spinnerContent);

		SpinnerCard spinnersetting = new SpinnerCard(cpuSetting.getSettingName(), cpuSetting.getSettingDescr(), spinnerArrayAdapter, new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
				String value = parent.getItemAtPosition(pos).toString();
				
				//DISABLE SAVING THIS SETTING TEMPORARY
				//FileHelper.writeFile(cpuSetting.getSettingPath(), value);

				String changedTo = value ;
				Crouton.showText(act, cpuSetting.getSettingName() + " changed to: " + changedTo, Style.INFO);
				Log.d("-----FILEPATH-----", cpuSetting.getSettingPath() + "-----------------" +value);
				
				//Set setting from selection
				//editor.putString(cpuSetting.getSettingPath(), value);
				//editor.commit();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
		return spinnersetting;
	}


	public String buildSetting(boolean enabled, String eqEnabled, String eqDisabled) {
		String setting = "";
		if (enabled) {
			setting = eqEnabled + "\n";
		} else {
			setting = eqDisabled + "\n";
		}

		return setting;
	}

	public void initSetting(String filePath) {
		(new ChangePermission()).setFilePath(filePath).execute();
	}
}
