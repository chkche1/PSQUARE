package com.sshannin.pp.server.tests;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.sshannin.pp.server.clientservices.DBRoutinesImpl;

public final class DBRoutinesTest {

	public static void main(String[] args) {
		try{
			assert(false);
			System.out.println("Assertions not active...exiting.");
			System.exit(1);
		} catch(AssertionError e){
			System.out.println("Assertions verified as enabled.");
		}
		
		System.out.println("Runnings some tests (could take some time).");
		final int base = 100000;
		final int amount = 24;

		DBRoutinesImpl impl = new DBRoutinesImpl();
		List<Integer> ls = impl.getFriends(base + 1);
		
		System.out.print("Checking number of friends...");
		assert (ls.size() == amount - 1);
		for (int i = base + 2; i <= base + amount; i++)
			assert (ls.contains(i));
		System.out.println("done");

		
		System.out.print("Checking number of friends of friends...");
		Collection<Integer> ls2 = impl.getFriendsOfFriends(100009);

		assert (ls2.size() == amount - 1);
		for (int i = base + 1; i <= base + amount; i++)
			if (i != 100009)
				assert (ls2.contains(i));
		System.out.println("done");

		System.out.print("Checking mutual friends...");
		ls2 = impl.getMutualFriends(100002, 100003); // 1 & multiples of 6
		assert (ls2.size() == 5);
		for (int i = 100006; i <= base + amount; i += 6)
			assert (ls2.contains(i));

		assert (ls2.contains(base + 1));
		System.out.println("done");

		System.out.print("Checking friend recs...");
		ls = impl.getRecommendationsByFriends(100002);

		assert (ls.size() == (amount / 2) - 1);
		System.out.println("done");

		System.out.print("Checking are friends...");
		for (int i = 2; i <= amount; i++) {
			assert (impl.areFriends(base + i, base + 1));
			assert (impl.areFriends(base + i, base + 1));
		}

		for (int i = 3; i <= amount; i++) {
			if (i % 2 == 1) {
				assert (!impl.areFriends(base + 2, base + i));
				assert (!impl.areFriends(base + i, base + 2));
			} else {
				assert (impl.areFriends(base + 2, base + i));
				assert (impl.areFriends(base + i, base + 2));
			}
		}
		System.out.println("done");


		assert (impl.getAverageRating(100001) == 2.5);
		assert (impl.getAverageRatingByFriends(100001, 100001) == 2.5);

		ls = impl.getPhotosByRelevanceCached(100003);

		for (Integer i : ls)
			System.out.println(i);

		// try again for cache
		ls = impl.getPhotosByRelevanceCached(100003);

		for (Integer i : ls)
			System.out.println(i);
		// System.out.println(impl.getRelevance(100001, 100006));
				
		Map<Integer, String> trending = impl.getRecentPics(100001);
		
		for(Integer i : trending.keySet())
			System.out.println(i + ":" + trending.get(i));
				
		Map<Integer, Set<String>> tags = impl.getTagsForPics(impl.getRecentPics(100001).keySet());
		
		for(Entry<Integer, Set<String>> e : tags.entrySet()){
			System.out.print(e.getKey() + ": [");
			for (String s: e.getValue())
				System.out.print(s + ',');
			System.out.println("]\n");
		}

		System.out.println("Passed some sanity tests");
	}
	
}
