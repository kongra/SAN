package ewus.profile;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;

@Singleton
public class GlobalCache {

  private Map<Object, Object> cache = new HashMap<>();

  @Lock(LockType.READ)
  public Object get(Object key, Object notFound) {
    if (cache.containsKey(key)) {
      return cache.get(key);
    }
    return notFound;
  }

  @Lock(LockType.WRITE)
  public Object put(Object key, Object value) {
    return cache.put(key, value);
  }

  @Lock(LockType.WRITE)
  public void clear() {
    cache.clear();
  }
}
