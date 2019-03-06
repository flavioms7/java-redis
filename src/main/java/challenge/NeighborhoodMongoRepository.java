package challenge;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NeighborhoodMongoRepository extends MongoRepository<NeighborhoodMongo, String> {
    @Query("{'geometry' : { $geoIntersects : { $geometry : {type:'Point', coordinates:[?0, ?1]}}}}")
    NeighborhoodMongo findByGeometryIntersect(Double x, Double y);
}