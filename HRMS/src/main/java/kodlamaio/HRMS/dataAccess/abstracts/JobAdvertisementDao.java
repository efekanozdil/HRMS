package kodlamaio.HRMS.dataAccess.abstracts;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import kodlamaio.HRMS.entities.concretes.JobAdvertisement;

public interface JobAdvertisementDao extends JpaRepository<JobAdvertisement, Integer> {
	
	List<JobAdvertisement> getAllByIsActive(boolean isActive);
	List<JobAdvertisement> getAllByIsActiveOrderByCreatedDateDesc(boolean isActive);
	
	
	@Query("From JobAdvertisement where isActive = true and employer_id =:id")
	List<JobAdvertisement> getEmployersActiveJobAdvertisement(int id);
	
	
	

}
