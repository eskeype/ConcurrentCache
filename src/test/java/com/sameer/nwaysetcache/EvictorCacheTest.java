import static org.junit.Assert.*;
import org.junit.Test;
import nwaysetcache.*;

public class EvictorCacheTest {
  @Test
  public void deletesLeastWithLeastEvictor() {
    EvictorCache<Integer,Integer> evictLeast = new EvictorCache<>(2, new LeastEvictor<Integer>());
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