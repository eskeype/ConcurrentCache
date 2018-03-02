import static org.junit.Assert.*;
import org.junit.Test;
import nwaysetcache.*;

public class LRUCacheTest {
  @Test
  public void deletesEarliestAdded() {
    EvictorCache<Integer,Integer> lru = new EvictorCache<Integer,Integer>(2, new LRUEvictor<Integer>());
    lru.put(1,1);
    lru.put(2,2);
    lru.put(3,3);

    assertTrue(lru.size() == 2);
    assertTrue(!lru.containsKey(1));
    assertTrue(lru.containsKey(2));
    assertTrue(lru.containsKey(3));
  }

  @Test
  public void deletesLeastRecentlyAccessed() {
  	EvictorCache<Integer,Integer> lru = new EvictorCache<Integer,Integer>(2, new LRUEvictor<Integer>());
  	lru.put(1,1);
  	lru.put(2,2);
  	lru.get(1);

  	lru.put(3,3);

    assertTrue(lru.size() == 2);
  	assertTrue(lru.containsKey(1));
  	assertTrue(!lru.containsKey(2));
  	assertTrue(lru.containsKey(3));
  }

  @Test
  public void containsKeyDoesntChangeAccessOrder() {
    EvictorCache<Integer,Integer> lru = new EvictorCache<Integer,Integer>(2, new LRUEvictor<Integer>());
    lru.put(1,1);
    lru.put(2,2);

    lru.containsKey(2);
    lru.containsKey(1);

    lru.put(3,3);

    assertTrue(lru.size() == 2);
    assertTrue(!lru.containsKey(1));
    assertTrue(lru.containsKey(2));
    assertTrue(lru.containsKey(3));
  }

  @Test
  public void removalAndReassignment() {
    EvictorCache<Integer,Integer> lru = new EvictorCache<Integer,Integer>(2, new LRUEvictor<Integer>());
    lru.put(1,1);
    lru.put(2,2);
    lru.put(1,-1);

    assertTrue(lru.size() == 2);
    assertTrue(lru.get(1) == -1);

    lru.put(3,3);

    assertTrue(lru.size() == 2);
    assertTrue(lru.containsKey(1));
    assertTrue(!lru.containsKey(2));
    assertTrue(lru.containsKey(3));

    lru.remove(3);

    assertTrue(lru.size() == 1);
    assertTrue(lru.containsKey(1));
    assertTrue(!lru.containsKey(2));
    assertTrue(!lru.containsKey(3));
  }
}