package fujitsu.proovit.demo.repositories;

import fujitsu.proovit.demo.entities.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// Repository to hold all the feedbacks.
public interface FeedbackRepo extends JpaRepository<Feedback, Long> {
}

