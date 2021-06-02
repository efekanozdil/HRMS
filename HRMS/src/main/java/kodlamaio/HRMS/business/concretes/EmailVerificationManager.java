package kodlamaio.HRMS.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.HRMS.business.abstracts.EmailVerificationService;
import kodlamaio.HRMS.core.utilities.GenerateRandomCode;
import kodlamaio.HRMS.core.utilities.results.ErrorDataResult;
import kodlamaio.HRMS.core.utilities.results.Result;
import kodlamaio.HRMS.core.utilities.results.SuccessDataResult;
import kodlamaio.HRMS.dataAccess.abstracts.EmailVerificationDao;
import kodlamaio.HRMS.entities.concretes.EmailVerification;

@Service
public class EmailVerificationManager implements EmailVerificationService {

	EmailVerificationDao emailVerificationDao;
	
	
	@Autowired
	public EmailVerificationManager(EmailVerificationDao emailVerificationDao) {
		this.emailVerificationDao = emailVerificationDao;
	}
	

	@Override
	public void generateCode(EmailVerification activationCode, Integer id) {
		activationCode.setActivationCode(null);
		activationCode.setConfirmed(false);
		if (activationCode.isConfirmed() == false) {
			GenerateRandomCode generator = new GenerateRandomCode();
			String code_create = generator.Create(id);
			activationCode.setActivationCode(code_create);
			activationCode.setUserId(id);

			emailVerificationDao.save(activationCode);

		}

		return;

		
	}

	@Override
	public Result verify(String activationCode, Integer id) {
		EmailVerification eVer=emailVerificationDao.findByUserId(id).get();
		if(eVer.getActivationCode().equals(activationCode)) {
			eVer.setConfirmed(true);
			return new SuccessDataResult<EmailVerification>(this.emailVerificationDao.save(eVer),"Doğrulama başarılı!");
			
	
		}
		return new ErrorDataResult<EmailVerification>(null,"Doğrulama başarısız, lütfen tekrar deneyin.");
	}

}
