
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class ToyShop {

	public static int findMaxNoToys(int[] price, int[] count, int rs) {

		// Initializing the Max Value
		int maxNoToys = 0;

		// Adding Price values into ToyPriceMap
		Map<String, Integer> toyPriceMap = new TreeMap<>();
		toyPriceMap.put("A", price[0]);
		toyPriceMap.put("B", price[1]);
		toyPriceMap.put("C", price[2]);
		toyPriceMap.put("D", price[3]);
		toyPriceMap.put("E", price[4]);

		// Adding Count values into ToyCountMap
		Map<String, Integer> toyCountMap = new HashMap<>();
		toyCountMap.put("A", count[0]);
		toyCountMap.put("B", count[1]);
		toyCountMap.put("C", count[2]);
		toyCountMap.put("D", count[3]);
		toyCountMap.put("E", count[4]);

		System.out.println("Toy Price Map : " + toyPriceMap);
		System.out.println("Toy Count Map : " + toyCountMap);

		// Sorting the ToyPriceMap
		Map<String, Integer> sortedPriceMap = toyPriceMap
				.entrySet()
				.stream()
				.sorted(Map.Entry.comparingByValue())
				.collect(
						Collectors.toMap(e -> e.getKey(), e -> e.getValue(), (
								e1, e2) -> e2, LinkedHashMap::new));

		System.out.println("Sorted Toy Price Map : " + sortedPriceMap);
		System.out.println();

		Set<Entry<String, Integer>> m = sortedPriceMap.entrySet();

		int remaining = rs;

		// Iterating the Price Map and count..Generating the Maxcount
		for (Entry<String, Integer> e : m) {
			System.out.println("=================Toy Name " + e.getKey()
					+ "==================");
			if (toyCountMap.containsKey(e.getKey())) {
				int cnt = toyCountMap.get(e.getKey());
				if (cnt != 0 && ((remaining / e.getValue()) > cnt)) {
					System.out.println("    Toy Price : " + e.getValue());
					System.out.println("    Toy Count : " + cnt);
					System.out.println("    Before update Toys Count "
							+ maxNoToys);
					maxNoToys = maxNoToys + cnt;
					remaining = remaining - (cnt * e.getValue());
					System.out.println("    After Update Toys count "
							+ maxNoToys);
					System.out.println("    Spent Amount " + (rs - remaining));
					System.out.println("    Remaining Amount " + remaining);
					System.out.println("=========Toy " + e.getKey()
							+ "'s bought count is " + cnt + "===========");
					System.out.println();
				} else if (cnt != 0 && remaining != 0
						&& (remaining / e.getValue()) < cnt) {
					System.out.println("    Toy Price : " + e.getValue());
					System.out.println("    Toy Count : " + cnt);
					System.out.println("    Before update Toys count "
							+ maxNoToys);
					cnt = remaining / e.getValue();
					maxNoToys = maxNoToys + cnt;
					remaining = remaining
							- ((remaining / e.getValue()) * e.getValue());
					System.out.println("    After Update Toys Count "
							+ maxNoToys);
					System.out.println("    Spent Amount " + (rs - remaining));
					System.out.println("    Remaining Amount " + remaining);
					System.out.println("=========Toy " + e.getKey()
							+ "'s bought count is " + cnt + "===========");
					System.out.println();
					break;
				}

			}
		}

		return maxNoToys;
	}

	public static void main(String[] args) {

		// Input the Price for Toys A,B,C,D,E respectively
		int price[] = { 5, 1, 3, 2, 8 };

		// Input the No of Toys for each Toy A,B,C,D,E respectively
		int count[] = { 1, 10, 5, 3, 0 };

		// Input the Amount Given
		int rs = 25;

		// Calling finaMaxnoToys to find the Max count
		int maxNoToys = ToyShop.findMaxNoToys(price, count, rs);

		System.out.println("======Max No of Toys one Could buy is " + maxNoToys
				+ "======");

	}

}
