package com.tundem.teamsevenmod.entity;

public class CPUSetting {
	private String settingName = "";
	private String settingDescr = "";
	private String settingPath = "";
	private String settingDefaultsPath = "";

	public CPUSetting(String settingName, String settingDescr, String settingDefaultsPath, String settingPath) {
		super();
		this.settingName = settingName;
		this.settingDescr = settingDescr;
		this.settingDefaultsPath = settingDefaultsPath;
		this.settingPath = settingPath;
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

	public String getSettingDefaultsPath() {
		return settingDefaultsPath;
	}

	public void setSettingDefaultsPath(String settingDefaultsPath) {
		this.settingDefaultsPath = settingDefaultsPath;
	}

}
