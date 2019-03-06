package challenge;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	private NeighborhoodMongoRepository neighborhoodMongoRepository;

	@Autowired
	private NeighborhoodRepository neighborhoodRepository;

	@Autowired
	private RestaurantMongoRepository restaurantMongoRepository;

	@Override
	public NeighborhoodRedis findInNeighborhood(double x, double y) {
		NeighborhoodMongo neighbor = neighborhoodMongoRepository.findByGeometryIntersect(x, y);
		NeighborhoodRedis neighborCache = neighborhoodRepository.find(neighbor.getId());

		if(neighborCache != null){
			return neighborCache;
			
		}else{

			List<RestaurantMongo> restaurants = restaurantMongoRepository.findAllByLocationWithinOrderByNameAsc(neighbor.getGeometry());
			List<RestaurantRedis> restaurantsCache = new ArrayList<>();
	
			restaurants.forEach(restaurant->
					restaurantsCache.add(new RestaurantRedis(restaurant.getId(), restaurant.getName(),
							restaurant.getLocation().getX(), restaurant.getLocation().getY()))
			);
	
			NeighborhoodRedis newNeighborAsCache = new NeighborhoodRedis(neighbor.getId(), neighbor.getName(), restaurantsCache);
			neighborhoodRepository.save(newNeighborAsCache);
	
			return newNeighborAsCache;
		}
	}
}
