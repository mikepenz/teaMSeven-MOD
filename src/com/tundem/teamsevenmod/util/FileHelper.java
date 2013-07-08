package com.tundem.teamsevenmod.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import utils.CMDProcessor;

import android.os.AsyncTask;
import android.util.Log;
import eu.chainfire.libsuperuser.Shell;

public class FileHelper {
	public static boolean writeStringToFile(String filePath, String content) {
		try {
			File outputFile = new File(filePath);
			if (!outputFile.exists()) {
				outputFile.createNewFile();
			}

			FileOutputStream fos = new FileOutputStream(outputFile);
			fos.write(content.getBytes());
			fos.close();

			return true;
		} catch (Exception e) {
			Log.e("com.tundem.teamsevenmod", e.toString());
			return false;
		}
	}

	public static void writeFile(String filePath, String value) {
		CMDProcessor.runSuCommand("busybox echo "+ value + " > "+filePath);
	}
	
	public static class ChangePermission extends AsyncTask<Void, Void, Void> {
		String filePath;

		public ChangePermission setFilePath(String filePath) {
			this.filePath = filePath;
			return this;
		}

		@Override
		protected void onPreExecute() {
		}

		@Override
		protected Void doInBackground(Void... params) {
			Shell.SU.run("chmod 666 " + filePath);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
		}
	}

	public static String convertStreamToString(InputStream is) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null) {
			sb.append(line).append("\n");
		}
		return sb.toString();
	}

	public static String getStringFromFile(String filePath) throws Exception {
		File fl = new File(filePath);
		FileInputStream fin = new FileInputStream(fl);
		String ret = convertStreamToString(fin);
		//Make sure you close all streams.
		fin.close();
		return ret;
	}
	public static List<String> getFileContentAsList(String filePath) throws Exception{
		String content = getStringFromFile(filePath);
		String[] splittedContent = content.split(" ");
		List<String> values = new ArrayList<String>(Arrays.asList(splittedContent));
		
		return values;
	}
}
