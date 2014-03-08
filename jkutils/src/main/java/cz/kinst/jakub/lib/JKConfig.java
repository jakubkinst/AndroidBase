package cz.kinst.jakub.lib;

public class JKConfig {
	public static final int LOG_LEVEL_DEBUG = 1;
	public static final int LOG_LEVEL_ERROR = 2;
	int logLevel;
	String logTag;

	public JKConfig(int logLevel, String logTag) {
		super();
		this.logLevel = logLevel;
		this.logTag = logTag;
	}

	public int getLogLevel() {
		return logLevel;
	}

	public String getLogTag() {
		return "JK-"+logTag;
	}
}
