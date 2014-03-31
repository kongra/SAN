package allegro.profile;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;

@Singleton
public class Memcache {

  private Map<Object, Object> cache = new HashMap<>();

  @Lock(LockType.READ)
  public Object read(Object key) {
    return cache.get(key);
  }

  @Lock(LockType.WRITE)
  public void write(Object key, Object value) {
    cache.put(key, value);
  }

  @Lock(LockType.WRITE)
  public void invalidate(Object key) {
    cache.remove(key);
  }

  @Lock(LockType.WRITE)
  public void invalidate() {
    cache.clear();
  }
}
