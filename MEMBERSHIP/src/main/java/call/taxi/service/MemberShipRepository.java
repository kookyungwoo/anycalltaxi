package call.taxi.service;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="memberShips", path="memberShips")
public interface MemberShipRepository extends PagingAndSortingRepository<MemberShip, Long>{


}
