import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class OvodaTeszt {
	
	public static LinkedHashMap<Child, LinkedHashSet<Child>> mapVoterFavs = new LinkedHashMap<>(); 
	
	public static void main (String args []) throws IOException {
		File file = new File ("nevLista.txt");
		LinkedList<String> names = new LinkedList<>();		
		
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String line = reader.readLine();
			
			while(line != null) {				
				for (String namesInLine : line.split(",")) {
					names.add(namesInLine);
				}				
				line = reader.readLine();				
			}
			reader.close();
			createListWithinList(names);
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		finally {
			System.out.println("Keszult a Nemzeti Ovodakonzultacios Miniszterium megbizasabol.");
		}
	}

	public static Map<String, Integer> countPopular(Map<String, List<String>> finalmap) {
	    Map<String, Integer> occurance = new HashMap<String, Integer>();
	    for(String k : finalmap.keySet()) {
	        for(String element : finalmap.get(k)) {
	            int count;
	            if(occurance.containsKey(element)) {
	                count = occurance.get(element);
	            } else {
	                count = 0;
	            }
	            occurance.put(element, count + 1);
	        }
	    }	 
	    Map.Entry<String, Integer> maxEntry = null;
	    for (Map.Entry<String, Integer> entry : occurance.entrySet()) {
	        if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0){
	            maxEntry = entry;
	        }
	    }	    
	    System.out.println("\n\nA legnepszerubb ovis:		" + maxEntry.getKey() + "	 szavazatok szama:	"+ maxEntry.getValue() +"\n");
	    return  occurance;
	}
	
	private static void createListWithinList(LinkedList list) {			
		NoDupMap  noDupMap = new NoDupMap();	
		
		Iterator it = list.iterator();
		while (it.hasNext()) {			
			LinkedHashSet<String> lhs = new LinkedHashSet<>(Arrays.asList(it.next().toString().split("\\s")));				
			String [] array = new String[lhs.size()];
			String [] lhsAsArray = lhs.toArray(array);				
			String voter = lhsAsArray[0];
			String favs="";
			
			for (int i = 1; i < lhsAsArray.length; i++) {				
				favs += lhsAsArray[i];
				 favs += " ";	 	
			}
			 	for (String s : favs.toString().split(" ")) {
			 		LinkedHashSet<Child> children = new LinkedHashSet<Child>();
			 		children.add(new Child(s)); 
			 		mapVoterFavs.put(new Child(voter),children);				 
			 	}			 
	
			Set set = mapVoterFavs.entrySet();			
			Iterator iterator = set.iterator();
			 while (iterator.hasNext()) {
		            Map.Entry item = (Map.Entry) iterator.next();		         
		            noDupMap.put(item.getKey().toString(), item.getValue().toString());
			 }			 
		}
		System.out.println("Tovabbi szavazatok: 		" + countPopular(noDupMap));
	}
}
