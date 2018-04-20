import static org.junit.Assert.*;
import org.junit.Test;
import concurrentcache.*;

public class MRUCacheTest {
  @Test
  public void deletesLastAdded() {
    EvictorCache<Integer,Integer> mru = new EvictorCache<>(2, new MRUEvictor<>());
    mru.put(1,1);
    mru.put(2,2);
    mru.put(3,3);

    assertTrue(mru.size() == 2);
    assertTrue(mru.containsKey(1));
    assertTrue(!mru.containsKey(2));
    assertTrue(mru.containsKey(3));
  }

  @Test
  public void deletesLastAccessed() {
    EvictorCache<Integer,Integer> mru = new EvictorCache<>(2, new MRUEvictor<>());
  	mru.put(1,1);
  	mru.put(2,2);
  	mru.get(1);

  	mru.put(3,3);

    assertTrue(mru.size() == 2);
  	assertTrue(!mru.containsKey(1));
  	assertTrue(mru.containsKey(2));
  	assertTrue(mru.containsKey(3));
  }

  @Test
  public void containsKeyDoesntChangeAccessOrder() {
    EvictorCache<Integer,Integer> mru = new EvictorCache<>(2, new MRUEvictor<>());
    mru.put(1,1);
    mru.put(2,2);

    mru.containsKey(2);
    mru.containsKey(1);

    mru.put(3,3);

    assertTrue(mru.size() == 2);
    assertTrue(mru.containsKey(1));
    assertTrue(!mru.containsKey(2));
    assertTrue(mru.containsKey(3));
  }

  @Test
  public void removalAndReassignment() {
    EvictorCache<Integer,Integer> mru = new EvictorCache<>(2, new MRUEvictor<>());
    mru.put(1,1);
    mru.put(2,2);
    mru.put(1,-1);

    assertTrue(mru.size() == 2);
    assertTrue(mru.get(1) == -1);

    mru.put(3,3);

    assertTrue(mru.size() == 2);
    assertTrue(!mru.containsKey(1));
    assertTrue(mru.containsKey(2));
    assertTrue(mru.containsKey(3));

    mru.remove(3);

    assertTrue(mru.size() == 1);
    assertTrue(!mru.containsKey(1));
    assertTrue(mru.containsKey(2));
    assertTrue(!mru.containsKey(3));
  }
}