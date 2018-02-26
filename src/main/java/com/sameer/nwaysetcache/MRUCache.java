package nwaysetcache;

import java.util.*;
public class MRUCache<K,V> implements Cache<K,V>{

	//interface will change so that keys can be removed by a user. Come on dude.
	
	private final int capacity;
	private HashMap<K,DoubleListNode<K,V>> map;
	private DoubleListNode<K,V> head;
	private DoubleListNode<K,V> tail;


	public MRUCache(int capacity){
		head = new DoubleListNode<K,V>(null,null);
		tail = new DoubleListNode<K,V>(null,null);

		head.next = tail;
		tail.prev = head;

		map = new HashMap<>();

		this.capacity = capacity;
	}


	/*
	Returns the current number of key value pairs contained in the cache
	*/
	@Override
	public int size(){
		return map.size();
	}

	/*
	Returns true if key is associated with a value in the cache, and false if not
	*/
	@Override
	public boolean containsKey(K key){

		return map.containsKey(key);
	}

	/*
	Returns value associated with input key
	*/
	@Override
	public V get(K key){
		if(!map.containsKey(key))
			return null;

		DoubleListNode<K,V> valueNode = map.get(key);
		//remove valueNode from its place in linked list
		valueNode.prev.next = valueNode.next;
		valueNode.next.prev = valueNode.prev;

		//insert it right before tail
		valueNode.next = tail;
		valueNode.prev = tail.prev;

		//update its neighbors
		tail.prev.next = valueNode;
		tail.prev = valueNode;

		return valueNode.val;
	}

	/*
	Associates key value pair into cache
	*/
	@Override
	public void put(K key, V value){

		DoubleListNode<K,V> valueNode = new DoubleListNode<K,V>(key,value);
		

		if(map.containsKey(key)){
			remove(key);
		}

		else if(map.size() == capacity){
			K delete = tail.prev.key;
			remove(delete);
		}

		//inserts valueNode right before tail
		valueNode.next = tail;
		valueNode.prev = tail.prev;

		//updates its neighbors
		tail.prev.next = valueNode;
		tail.prev = valueNode;

		map.put(key,valueNode);
	}

	/*
	Removes key value pair associated with key
	*/
	@Override
	public void remove(K key){

		if(!map.containsKey(key)){
			return;
		}

		DoubleListNode<K,V> oldValueNode = map.get(key);
		oldValueNode.prev.next = oldValueNode.next;
		oldValueNode.next.prev = oldValueNode.prev;

		map.remove(key);
		
	}

	@Override
	public void clear(){
		map.clear();
		head.next = tail;
		tail.next = head;
	}
	private static class DoubleListNode<K,V>{
		K key;
		V val;
		DoubleListNode<K,V> prev;
		DoubleListNode<K,V> next;

		DoubleListNode(K key, V val){
			this.key = key;
			this.val = val;
		}
	}
}