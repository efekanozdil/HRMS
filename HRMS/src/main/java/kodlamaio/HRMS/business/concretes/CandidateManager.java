package kodlamaio.HRMS.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.HRMS.business.abstracts.CandidateService;
import kodlamaio.HRMS.business.abstracts.EmailVerificationService;
import kodlamaio.HRMS.business.abstracts.UserService;
import kodlamaio.HRMS.core.utilities.IdentityValidation;
import kodlamaio.HRMS.core.utilities.results.DataResult;
import kodlamaio.HRMS.core.utilities.results.ErrorResult;
import kodlamaio.HRMS.core.utilities.results.Result;
import kodlamaio.HRMS.core.utilities.results.SuccessDataResult;
import kodlamaio.HRMS.core.utilities.results.SuccessResult;
import kodlamaio.HRMS.dataAccess.abstracts.CandidateDao;
import kodlamaio.HRMS.dataAccess.abstracts.EmailVerificationDao;

import kodlamaio.HRMS.entities.concretes.Candidate;
import kodlamaio.HRMS.entities.concretes.EmailVerification;
import kodlamaio.HRMS.entities.concretes.User;

@Service
public class CandidateManager implements CandidateService {

	private CandidateDao candidateDao;
	private UserService userService;
	private EmailVerificationService emailVerificationService;
	

	@Autowired
	public CandidateManager(CandidateDao candidateDao,UserService userService,EmailVerificationService emailVerificationService) {
		super();
		this.candidateDao = candidateDao;
		this.userService=userService;
		this.emailVerificationService=emailVerificationService;
	}

	@Override
	public DataResult<List<Candidate>> getAll() {
		return new SuccessDataResult<List<Candidate>>(this.candidateDao.findAll(),"İş arayanlar listelendi.");
	}

	@Override
	public Result add(Candidate candidate) {
		
		if(!firstNameChecker(candidate)) {
			return new ErrorResult("İsim alanı boş bırakılamaz!");
		}
		else if(!lastNameChecker(candidate)) {
			return new ErrorResult("Soyisim alanı boş bırakalamaz!");
		}
		else if(!IdentityValidation.isRealPerson(candidate.getIdentityNumber())) {
			return new ErrorResult("TC Kimlik Numarası doğrulanmadı, tekrar deneyin.");
		}
		else if(candidate.getIdentityNumber().isBlank()) {
			return new ErrorResult("TC Kimlik Numarası alanı boş bırakalamaz!;");
		}
		else if(!birthDateChecker(candidate)) {
			return new ErrorResult("Doğum Tarihi boş bırakalamaz.");
		}
		else if(!emailChecker(candidate)) {
			return new ErrorResult("E-mail alanı boş bırakalamaz.");
		}
		
		else if(!passwordChecker(candidate)) {
			return new ErrorResult("Password alanı boş bırakalamaz.");
		}
		else if(candidateDao.findAllByEmail(candidate.getEmail()).stream().count() != 0) {
			return new ErrorResult("Bu E-Mail adresi sistemde mevcut!!");
			
		}
		else if(candidateDao.findAllByIdentityNumber(candidate.getIdentityNumber()).stream().count() != 0) {
			return new ErrorResult("Bu TC Kimlik Numarası sistemde mevcut!!");
		}
		

		this.candidateDao.save(candidate);
		
	
		return new SuccessResult("İş arayan sisteme eklenmiştir.");
	}
	
	private  boolean firstNameChecker(Candidate candidate) {
		
		if(candidate.getFirstName().equals(null) || candidate.getFirstName().isBlank()) {
			return false;
		}
		return true;
		
	}
	
	private  boolean lastNameChecker(Candidate candidate) {
		
		if(candidate.getLastName().equals(null) || candidate.getLastName().isBlank()) {
			return false;
		}
		return true;
		
	}
	private  boolean IdentityNumberChecker(Candidate candidate) {
		
		if(candidate.getIdentityNumber().equals(null) || candidate.getIdentityNumber().isBlank()) {
			return false;
		}
		return true;
		
	}
	
	private boolean birthDateChecker(Candidate candidate) {
		if(candidate.getBirthDate().equals(null)) {
			return false;
		}
		return true;
		
	}
	private boolean emailChecker(Candidate candidate) {
		if(candidate.getEmail().isBlank() || candidate.getEmail().equals(null)) {
			return false;
		}
		return true;
		
	}
	
	private boolean passwordChecker(Candidate candidate) {
		if(candidate.getPassword().isBlank() || candidate.getPassword().equals(null)) {
			return false;
		}
		return true;
	}
	
	
	
	
	
	
	
	

}
