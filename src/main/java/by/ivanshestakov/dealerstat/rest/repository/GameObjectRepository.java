package by.ivanshestakov.dealerstat.rest.repository;

import by.ivanshestakov.dealerstat.rest.entity.GameObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameObjectRepository extends JpaRepository<GameObject, Integer> {
}
