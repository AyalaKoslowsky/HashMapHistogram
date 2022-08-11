package il.ac.tau.cs.sw1.ex8.histogram;

import java.util.*;

public class HashMapHistogram<T extends Comparable<T>> implements IHistogram<T>{

	public HashMap<T, Integer> hashMap = new HashMap<>();

	@Override
	public Iterator<T> iterator(){
		return new HashMapHistogramIterator<>(hashMap);
	}

	@Override
	public void addItem(T item) {
		boolean exist = false;
		for (T key : hashMap.keySet()){
			if(item.equals(key)){
				hashMap.put(key, hashMap.get(key)+1);
				exist = true;
			}
		}
		if (!exist) {hashMap.put(item, 1);}
	}

	@Override
	public void removeItem(T item) throws IllegalItemException {
		if(hashMap.containsKey(item) && hashMap.get(item)==1){
			hashMap.remove(item);
		}
		else if(hashMap.containsKey(item)){
			hashMap.put(item, hashMap.get(item)-1);
		}
		else {throw new IllegalItemException();}
	}

	@Override
	public void addItemKTimes(T item, int k) throws IllegalKValueException {
		if(k<1){throw new IllegalKValueException(k);}
		if (hashMap.containsKey(item)) {
			hashMap.put(item, hashMap.get(item) + k);
		}
		else{hashMap.put(item, k);}
	}

	@Override
	public void removeItemKTimes(T item, int k) throws IllegalItemException, IllegalKValueException {
		if (!hashMap.containsKey(item)){
			throw new IllegalItemException();
		}
		else if (k<1 || k>hashMap.get(item)){
			throw new IllegalKValueException(k);
		}
		else if(hashMap.get(item)==k){
			hashMap.remove(item);
		}
		else{
			hashMap.put(item, hashMap.get(item)-k);
		}
	}

	@Override
	public int getCountForItem(T item) {
		if (hashMap.containsKey(item)){
			return hashMap.get(item);
		}
		return 0;
	}

	@Override
	public void addAll(Collection<T> items) {
		for (T it: items){
			if (hashMap.containsKey(it)){
				hashMap.put(it, hashMap.get(it)+1);
			}
			else{
				hashMap.put(it, 1);
			}
		}
	}

	@Override
	public void clear() {
		for(T key : hashMap.keySet()){
			hashMap.remove(key);
		}
	}

	@Override
	public Set<T> getItemsSet() {
		Set<T> set = new HashSet<>();
		for (T it: hashMap.keySet()){
			if (hashMap.get(it)>0){
				set.add(it);
			}
		}
		return set;
	}

	@Override
	public void update(IHistogram<T> anotherHistogram) {
		for (T it:anotherHistogram){
			if (hashMap.containsKey(it)){
				hashMap.put(it, hashMap.get(it)+anotherHistogram.getCountForItem(it));
			}
			else{
				hashMap.put(it, anotherHistogram.getCountForItem(it));
			}
		}
	}
}
