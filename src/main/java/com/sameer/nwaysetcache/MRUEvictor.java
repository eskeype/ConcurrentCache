package nwaysetcache;

import java.util.*;
public class MRUEvictor<K> implements Evictor<K>{

	K mostRecentKey;

	public MRUEvictor(){
		mostRecentKey = null;
	}

	/*
	Will be called when key is added to cache. Should handle reassignments
	*/
	@Override
	public void keyAdded(K key){
		mostRecentKey = key;
	}

	/*
	Will be called when key has been accessed from the cache
	*/
	@Override
	public void keyAccessed(K key){
		mostRecentKey = key;
	}

	/*
	Will be called when a key is removed from the cache
	*/
	@Override
	public void keyRemoved(K key){
		return;
	}

	/*
	Will be called when cache is cleared
	*/
	@Override
	public void cacheCleared(){
		return;
	}

	/*
	Will be called to request which key should be discarded
	Key should be discarded from this data structure as well
	*/
	@Override
	public K chooseEvict(){
		return mostRecentKey;
	}

}