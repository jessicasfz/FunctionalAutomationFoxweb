package com.travelex.framework.common;

public class EnvironmentParameter {

	private String browserName;
	private String browserVersion;
	private String platform;

	public String getBrowserName() {
		return browserName;
	}

	public void setBrowserName(String browserName) {
		this.browserName = browserName;
	}

	public String getBrowserVersion() {
		return browserVersion;
	}

	public void setBrowserVersion(String browserVersion) {
		this.browserVersion = browserVersion;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public EnvironmentParameter clone() {
		EnvironmentParameter returnObject = new EnvironmentParameter();
		returnObject.browserName = this.browserName;
		returnObject.browserVersion = this.browserVersion;
		returnObject.platform = this.platform;
		return returnObject;
	}
}
