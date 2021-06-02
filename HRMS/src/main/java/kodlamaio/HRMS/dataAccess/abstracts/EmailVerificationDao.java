package kodlamaio.HRMS.dataAccess.abstracts;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.HRMS.entities.concretes.EmailVerification;

public interface EmailVerificationDao extends JpaRepository<EmailVerification, Integer>{

		Optional<EmailVerification> findByUserId(Integer userId);
	
}
