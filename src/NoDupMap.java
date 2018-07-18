import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NoDupMap extends HashMap<String, List<String>> {
		public void put(String k, String v) {
			if (containsKey(k)) {
				List<String> list = get(k);
				if (!list.contains(v)) {
					list.add(v);
				}
			} else {
				List<String> list = new ArrayList<String>();
				list.add(v);
				put(k, list);
			}
		}
}