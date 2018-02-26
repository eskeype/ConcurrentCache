import static org.junit.Assert.*;
import org.junit.Test;
import nwaysetcache.LRUCache;

public class LRUCacheTest {
  @Test
  public void deletesEarliestAdded() {
    LRUCache<Integer,Integer> lru = new LRUCache<>(2);
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
  	LRUCache<Integer,Integer> lru = new LRUCache<>(2);
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
    LRUCache<Integer,Integer> lru = new LRUCache<>(2);
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
    LRUCache<Integer,Integer> lru = new LRUCache<>(2);
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