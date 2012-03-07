package com.sshannin.pp.server;

import java.util.*;

import com.sshannin.pp.shared.Constants;
import com.sshannin.pp.shared.Pair;

public class DBQCache {

	private DBQCache() {
	}

	private static final Map<Integer, Pair<List<Integer>, Long>> photoRecs = new java.util.concurrent.ConcurrentHashMap<Integer, Pair<List<Integer>, Long>>();

	public static List<Integer> lookup(Integer user) {
		final Pair<List<Integer>, Long> p = photoRecs.get(user);
		if (p == null) {
			System.out.println("Photo rec cache miss for " + user + '.');
			return null;
		}

		// cache entry still valid
		if (System.currentTimeMillis() - p.q <= Constants.PHOTO_RELEVANCE_CACHE_TIMEOUT_MS) {
			System.out.println("Photo rec cache hit for " + user + '.');
			return p.p;
		}

		// entry too old; remove and return
		photoRecs.remove(user);
		System.out.println("Photo rec cache timeout for " + user + '.');
		return null;
	}

	public static void cache(Integer user, List<Integer> results) {
		System.out.println("Cached photo rec results for " + user + ".");
		photoRecs.put(
				user,
				new Pair<List<Integer>, Long>(results, System
						.currentTimeMillis()));
	}

}
