package com.tundem.teamsevenmod.entity;

public class MiscSetting {
	private String settingName = "";
	private String settingDescr = "";
	private String settingPath = "";
	private String valueEnabled = "1";
	private String valueDisabled = "0";

	public MiscSetting(String settingName, String settingDescr, String settingPath, String valueEnabled, String valueDisabled) {
		super();
		this.settingPath = settingPath;
		this.settingName = settingName;
		this.settingDescr = settingDescr;
		this.valueEnabled = valueEnabled;
		this.valueDisabled = valueDisabled;
	}

	public String getSettingName() {
		return settingName;
	}

	public void setSettingName(String settingName) {
		this.settingName = settingName;
	}

	public String getSettingDescr() {
		return settingDescr;
	}

	public void setSettingDescr(String settingDescr) {
		this.settingDescr = settingDescr;
	}

	public String getSettingPath() {
		return settingPath;
	}

	public void setSettingPath(String settingPath) {
		this.settingPath = settingPath;
	}

	public String getValueEnabled() {
		return valueEnabled;
	}

	public void setValueEnabled(String valueEnabled) {
		this.valueEnabled = valueEnabled;
	}

	public String getValueDisabled() {
		return valueDisabled;
	}

	public void setValueDisabled(String valueDisabled) {
		this.valueDisabled = valueDisabled;
	}

}
