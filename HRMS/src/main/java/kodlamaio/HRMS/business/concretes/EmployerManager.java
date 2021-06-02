package kodlamaio.HRMS.business.concretes;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.HRMS.business.abstracts.EmployerService;
import kodlamaio.HRMS.core.utilities.results.DataResult;
import kodlamaio.HRMS.core.utilities.results.ErrorResult;
import kodlamaio.HRMS.core.utilities.results.Result;
import kodlamaio.HRMS.core.utilities.results.SuccessDataResult;
import kodlamaio.HRMS.core.utilities.results.SuccessResult;
import kodlamaio.HRMS.dataAccess.abstracts.EmployerDao;
import kodlamaio.HRMS.entities.concretes.Employer;

@Service
public class EmployerManager implements EmployerService{

	private EmployerDao employerDao;
	
	@Autowired
	public EmployerManager(EmployerDao employerDao) {
		super();
		this.employerDao = employerDao;
	}

	
	@Override
	public DataResult<List<Employer>> getAll() {
		
		return new SuccessDataResult<List<Employer>>(this.employerDao.findAll(),"İş verenler listelendi.");
	}

	@Override
	public Result add(Employer employer) {
		if(!companyNameChecker(employer)) {
			new ErrorResult("Şirket adı boş bırakılamaz!");
		}
		else if (!webAddressChecker(employer)) {
			new ErrorResult("Web addres alanı boş bırakılamaz!");
		}
		else if (!emailChecker(employer)) {
			new ErrorResult("E-Mail alanı boş bırakılamaz!");
		}
		else if(!isEmailAlreadyRegistered(employer)) {
			new ErrorResult("Bu E-mail adresi sistemde mevcut!");
		}
		else if(!isRealPhoneNumber(employer)) {
			new ErrorResult("Girilen numara geçersizdir!");
		}
		else if(!phoneNumberChecker(employer)) {
			new ErrorResult("Telefon numarası alanı boş bırakılamaz!");
		}
		else if(!EmployerDomainCheck(employer)) {
			new ErrorResult("E-Mail domain doğrulaması yapılamadı!");
		}
		
		this.employerDao.save(employer);
	
		return new SuccessResult("İş veren başarıyla eklendi.");
	}
	
	private boolean companyNameChecker(Employer employer) {
		if(employer.getCompanyName().isBlank() || employer.equals(null)) {
			return false;
		}
		return true;
	}
	private boolean webAddressChecker(Employer employer) {
		if(employer.getWebAddress().isBlank() || employer.getWebAddress().equals(null)) {
			return false;
			
		}
		return true;
	}
	
	private boolean emailChecker(Employer employer) {
		if(employer.getEmail().isBlank() || employer.getEmail().equals(null)) {
			return false;
		}
		return true;
	}
	
	private boolean phoneNumberChecker(Employer employer) {
		if(employer.getPhoneNumber().isBlank() || employer.getPhoneNumber().equals(null)) {
			return false;
		}
		return true;
	}
	private boolean isRealPhoneNumber(Employer employer) {

		String patterns = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
				+ "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
				+ "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$";

		Pattern pattern = Pattern.compile(patterns);
		Matcher matcher = pattern.matcher(employer.getPhoneNumber());
		if (!matcher.matches()) {
			return false;
		}
		return true;

	}
	private boolean isEmailAlreadyRegistered(Employer employer) {

		if (employerDao.findAllByEmail(employer.getEmail()).stream().count() != 0) {
			return false;
		}
		return true;

	}
	public static boolean EmployerDomainCheck(Employer employer) {
		
		String[] dizi = employer.getEmail().split("@");
		String email=dizi[1];
				
		String[] dizi2 = employer.getWebAddress().split("www.");
		String domain =dizi2[1];

		if(email.equals(domain)) {
			System.out.println(email + domain);
			return true;
			
		}
		else {
		System.out.println(email + domain + " false ");
		return false;}
	}
		
}
