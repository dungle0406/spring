package spring.test.mentor.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.test.mentor.entity.Mentor;
@Repository
public interface MentorRepository extends JpaRepository<Mentor, Long> {
}
