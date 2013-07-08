package com.tundem.teamsevenmod.config;

import java.util.Arrays;
import java.util.List;

import com.tundem.teamsevenmod.entity.MiscSetting;

public class Cfg {
	public static List<MiscSetting> miscSettings = Arrays.asList(
			new MiscSetting("sweep2Wake", "Some description of sweep2wake...", "/sys/android_touch/sweep2wake", "1", "0"), 
			new MiscSetting("dt2wake", "Some description of dt2wake...", "/sys/android_touch/doubletap2wake", "1", "0"), 
			new MiscSetting("logo2menu", "Some description of logo2menu...", "/sys/android_touch/logo2menu", "1", "0"), 
			new MiscSetting("logo2wake", "Some description of logo2wake...", "/sys/android_touch/logo2wake", "1", "0"), 
			new MiscSetting("blink_buttons", "Some description of blink_buttons...", "/sys/class/leds/button-backlight/blink_buttons", "1", "0"), 
			new MiscSetting("pocket_detect", "Some description of pocket_detect...", "/sys/android_touch/pocket_detect", "1", "0"));
	
	public static List<MiscSetting> cpuSettings = Arrays.asList(
			new MiscSetting("CPU max frequency", "Change CPU max frequency", "/sys/devices/system/cpu/cpu0/cpufreq/scaling_available_frequencies", "1", "0"),
			new MiscSetting("CPU min frequency", "Change CPU min frequency", "/sys/devices/system/cpu/cpu0/cpufreq/scaling_available_frequencies", "1", "0"),
			new MiscSetting("CPU Governor", "Change CPU Governor", "/sys/devices/system/cpu/cpu0/cpufreq/scaling_available_governors", "1", "0"),
			new MiscSetting("I/O Scheduler", "Change I/O Scheduler", "/sys/block/mmcblk0/queue/scheduler", "1", "0"));
}
