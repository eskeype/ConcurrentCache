import java.util.*;
import nwaysetcache.*;

public class LeastEvictor<K> implements Evictor<K>{
	TreeSet<K> keys;

	public LeastEvictor(){
		keys = new TreeSet<>();
	}

	/*
	Will be called when key is added to cache. Should handle reassignments
	*/
	public void keyAdded(K key){
		keys.add(key);
	}

	/*
	Will be called when key has been accessed from the cache
	*/
	public void keyAccessed(K key){
		return;
	}

	/*
	Will be called when a key is removed from the cache
	*/
	public void keyRemoved(K key){
		keys.remove(key);
	}

	/*
	Will be called when cache is cleared
	*/
	public void cacheCleared(){
		keys.clear();
	}

	/*
	Will be called to request which key should be discarded
	Key should be discarded from this data structure as well
	*/
	public K chooseEvict(){
		K least = keys.pollFirst();
		return least;
	}
}