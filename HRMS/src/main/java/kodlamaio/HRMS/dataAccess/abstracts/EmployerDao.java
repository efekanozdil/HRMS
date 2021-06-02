package kodlamaio.HRMS.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.HRMS.entities.concretes.Employer;

public interface EmployerDao extends JpaRepository<Employer, Integer> {
	List<Employer> findAllByEmail(String email);
}
