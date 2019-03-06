package challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class NeighborhoodRepositoryImpl implements NeighborhoodRepository {
	
	@Autowired
    private RedisTemplate<String, NeighborhoodRedis> redisTemplate;
    private static String KEY = "neighborhood";

    @Override
    public void save(NeighborhoodRedis neighborhoodRedis) {
        this.redisTemplate.opsForValue().set(KEY + ":" + neighborhoodRedis.getId(), neighborhoodRedis);
    }

    @Override
    public NeighborhoodRedis find(String id) {
        return this.redisTemplate.opsForValue().get(KEY + ":" + id);
    }
}
