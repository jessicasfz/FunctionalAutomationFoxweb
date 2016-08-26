package com.travelex.framework.common;

public class EnvironmentParameter {

	private String browserName;
	private String browserVersion;
	private String platform;
	private String testDay;
	public String targetLocationPath;

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

	public String getTestDay() {
		return testDay;
	}

	public void setTestDay(String testDay) {
		this.testDay = testDay;
	}

	public String getTargetLocationPath() {
		return targetLocationPath;
	}

	public void setTargetLocationPath(String targetLocationPath) {
		this.targetLocationPath = targetLocationPath;
	}

	public EnvironmentParameter clone() {
		EnvironmentParameter returnObject = new EnvironmentParameter();
		returnObject.browserName = this.browserName;
		returnObject.browserVersion = this.browserVersion;
		returnObject.platform = this.platform;
		returnObject.testDay = this.testDay;
		returnObject.targetLocationPath = this.targetLocationPath;
		return returnObject;
	}
}
