package com.demo.mocko.service.model;

public class VersionResponse {
	private String currentVersion;
	private String stableVersion;
	private boolean needToUpdate;

	public String getCurrentVersion() {
		return currentVersion;
	}

	public void setCurrentVersion(String currentVersion) {
		this.currentVersion = currentVersion;
	}

	public String getStableVersion() {
		return stableVersion;
	}

	public void setStableVersion(String stableVersion) {
		this.stableVersion = stableVersion;
	}

	public boolean isNeedToUpdate() {
		return needToUpdate;
	}

	public void setNeedToUpdate(boolean needToUpdate) {
		this.needToUpdate = needToUpdate;
	}

}
