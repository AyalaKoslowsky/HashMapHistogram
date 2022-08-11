package il.ac.tau.cs.sw1.ex8.histogram;

import java.util.*;

public class HashMapHistogramIterator<T extends Comparable<T>> implements Iterator<T>{

	private Iterator <Map.Entry<T, Integer>> lstIter;


	public HashMapHistogramIterator(HashMap<T, Integer> map){
		List<Map.Entry<T, Integer>> lst = new ArrayList<>(map.entrySet());
		lst.sort(new Comparator<Map.Entry<T,Integer>>(){
			@Override
			public int compare(Map.Entry<T, Integer> o1, Map.Entry<T, Integer> o2) {
				return Integer.compare(o1.getValue(), o2.getValue());
			}
		});
		Collections.reverse(lst);
		lstIter = lst.iterator();
	}


	@Override
	public boolean hasNext() {
		return lstIter.hasNext();
	}

	@Override
	public T next() {
		return lstIter.next().getKey();
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException(); //no need to change this
	}
}
