package kodlamaio.HRMS.business.abstracts;

import kodlamaio.HRMS.core.utilities.results.Result;
import kodlamaio.HRMS.entities.concretes.EmailVerification;

public interface EmailVerificationService {
		
	void generateCode(EmailVerification activationCode, Integer id);
	Result verify(String activationCode, Integer id);
	
}
