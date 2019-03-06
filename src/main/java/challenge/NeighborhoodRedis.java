package challenge;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;

/**
 * Classe para mapear o bairro no Redis
 *
 */
public class NeighborhoodRedis implements Serializable {
	
	@Id
	private String id;
	private String name;
	private List<RestaurantRedis> restaurants;
	
	public NeighborhoodRedis(String id, String name, List<RestaurantRedis> restaurants) {
        this.id = id;
        this.name = name;
        this.restaurants = restaurants;
    }

    public NeighborhoodRedis(){
    	
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RestaurantRedis> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<RestaurantRedis> restaurants) {
        this.restaurants = restaurants;
    }
}
