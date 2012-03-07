package com.sshannin.pp.shared;

/**
 * Central place to tune system parameters.
 * 
 */
public final class Constants {
	private Constants() {
	};

	public static final String XML_DUMP_FNAME = "mydump.xml";
	
	public static final int TRENDING_PHOTO_AMOUNT = 4;

	private static final long SECONDS_PER_INVERTED_INDEX_REBUILD = 20;

	private static final long SECONDS_PER_DB_TO_XML_DUMP = 30;

	private static final long PHOTO_RELEVANCE_CACHE_TIMEOUT_SECONDS = 60;
	
	public static final int CHAT_POLL_MS = 1000;

	

	// DON'T TOUCH ANYTHING BELOW THIS LINE
	// THESE ARE BUILT OFF OF VALUES ABOVE
	public static final long MS_PER_INVERTED_INDEX_REBUILD = SECONDS_PER_INVERTED_INDEX_REBUILD * 1000;

	public static final long MS_PER_DB_TO_XML_DUMP = SECONDS_PER_DB_TO_XML_DUMP * 1000;

	public static final long PHOTO_RELEVANCE_CACHE_TIMEOUT_MS = PHOTO_RELEVANCE_CACHE_TIMEOUT_SECONDS * 1000;

}
