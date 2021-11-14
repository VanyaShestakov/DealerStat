package by.ivanshestakov.dealerstat.rest.repository;

import by.ivanshestakov.dealerstat.rest.entity.ConfirmationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmationCodeRepository extends JpaRepository<ConfirmationCode, Integer> {
}
