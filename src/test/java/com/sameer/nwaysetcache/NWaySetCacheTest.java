import static org.junit.Assert.*;
import org.junit.Test;
import nwaysetcache.*;

public class NWaySetCacheTest {
  @Test
  public void putAndGet() {
  	NWaySetCache<Integer, Integer> cache = new NWaySetCache<>(3,3);

  	cache.put(1,1);
  	cache.put(2,2);
  	cache.put(3,3);

  	assertTrue(cache.size() == 3);
  	assertTrue(cache.get(1) == 1);
  	assertTrue(cache.get(2) == 2);
  	assertTrue(cache.get(3) == 3);
  }

  @Test
  public void overWrite() {
  	NWaySetCache<Integer, Integer> cache = new NWaySetCache<>(3,3);
  	cache.put(1,1);
  	cache.put(2,2);
  	cache.put(3,3);

  	cache.put(1,-1);

  	assertTrue(cache.size() == 3);
  	assertTrue(cache.get(1) == -1);
  	assertTrue(cache.get(2) == 2);
  	assertTrue(cache.get(3) == 3);

  }

  @Test
  public void replacesWithinSet(){
  	NWaySetCache<Integer, Integer> cache = new NWaySetCache<>(3,1);
  	cache.put(0,0);
  	cache.put(3,3);

  	/*
  	using the fact that hashCode of Integer i is i and 0 % 3 == 3 % 3 == 0, key 3 should have 
  	REPLACED key 0, even though the cache does have enough space for 3 keys
	*/

  	assertTrue(cache.size()==1);
  	assertTrue(!cache.containsKey(0));
  	assertTrue(cache.containsKey(3));

  	/*
  	Since 1 % 3 != 3 % 3, both keys can exist in the cache simultaneously 
  	*/
  	cache.put(1,1);
  	assertTrue(cache.size()==2);
  	assertTrue(cache.containsKey(3));
  	assertTrue(cache.containsKey(1));

  }

  @Test
  public void overrideWithEvictor(){
  	NWaySetCache<Integer, Integer> lru = new NWaySetCache<Integer,Integer>(1,2);

  	lru.put(1,1);
  	lru.put(2,2);
  	lru.put(3,3);

  	assertTrue(lru.size() == 2);
  	assertTrue(!lru.containsKey(1));
  	assertTrue(lru.containsKey(2));
  	assertTrue(lru.containsKey(3));

  	NWaySetCache<Integer, Integer> mru = new NWaySetCache<Integer, Integer>(1,2){
  		@Override	
  		protected Evictor<Integer> createEvictor(){
        return new MRUEvictor<Integer>();
		  }
    };

  	mru.put(1,1);
  	mru.put(2,2);
  	mru.put(3,3);

  	assertTrue(mru.size() == 2);
  	assertTrue(mru.containsKey(1));
  	assertTrue(!mru.containsKey(2));
  	assertTrue(mru.containsKey(3));
  }

  @Test
  public void overrideWithCache(){

    NWaySetCache<Integer,Integer> evictLeast = new NWaySetCache<Integer,Integer>(1,2){
      @Override
      protected Cache<Integer,Integer> createSubCache(int subcapacity){
        return new EvictorCache<Integer,Integer>(subcapacity, new LeastEvictor<Integer>());
      }

    };
    
    evictLeast.put(1,1);
    evictLeast.put(3,3);
    evictLeast.put(2,2);

    assertTrue(evictLeast.size() == 2);
    assertTrue(!evictLeast.containsKey(1));
    assertTrue(evictLeast.containsKey(2));
    assertTrue(evictLeast.containsKey(3));

    evictLeast.put(0,0);

    assertTrue(evictLeast.size() == 2);
    assertTrue(evictLeast.containsKey(0));
    assertTrue(!evictLeast.containsKey(2));
    assertTrue(evictLeast.containsKey(3));

    evictLeast.put(4,4);

    assertTrue(evictLeast.size() == 2);
    assertTrue(!evictLeast.containsKey(0));
    assertTrue(evictLeast.containsKey(3));
    assertTrue(evictLeast.containsKey(4));
  }

}