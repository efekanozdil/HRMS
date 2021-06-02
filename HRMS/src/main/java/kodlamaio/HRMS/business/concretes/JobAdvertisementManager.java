package kodlamaio.HRMS.business.concretes;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.HRMS.business.abstracts.JobAdvertisementService;
import kodlamaio.HRMS.core.utilities.results.DataResult;
import kodlamaio.HRMS.core.utilities.results.ErrorDataResult;
import kodlamaio.HRMS.core.utilities.results.Result;
import kodlamaio.HRMS.core.utilities.results.SuccessDataResult;
import kodlamaio.HRMS.core.utilities.results.SuccessResult;
import kodlamaio.HRMS.dataAccess.abstracts.EmployerDao;
import kodlamaio.HRMS.dataAccess.abstracts.JobAdvertisementDao;
import kodlamaio.HRMS.entities.concretes.JobAdvertisement;

@Service
public class JobAdvertisementManager implements JobAdvertisementService{
	
	private JobAdvertisementDao jobAdvertisementDao;
	private EmployerDao employerDao;
	
	@Autowired
	public JobAdvertisementManager(JobAdvertisementDao jobAdvertisementDao,EmployerDao employerDao) {
		super();
		this.jobAdvertisementDao = jobAdvertisementDao;
		this.employerDao=employerDao;
	}


	@Override
	public DataResult<List<JobAdvertisement>> getAllByIsActive(boolean isActive) {
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.getAllByIsActive(isActive),"Listelendi");
	}


	@Override
	public Result add(JobAdvertisement jobadvertisement) {
		
		this.jobAdvertisementDao.save(jobadvertisement);
		return new SuccessResult("İlan başarıyla eklendi.");
	}


	@Override
	public DataResult<List<JobAdvertisement>> getAll() {
		
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.findAll());
	}


	@Override
	public DataResult<List<JobAdvertisement>> getAllByIsActiveOrderByCreatedDateDesc() {
		
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.getAllByIsActiveOrderByCreatedDateDesc(true),"Başarılı.");
	}


	@Override
	public DataResult<List<JobAdvertisement>> getEmployersActiveJobAdvertisement(int id) {
		
		if(!employerDao.existsById(id)) {
			return new ErrorDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.getEmployersActiveJobAdvertisement(id),"Başarısız");
		}
		else {
			return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.getEmployersActiveJobAdvertisement(id),"Başarılı");
		}
		
	}


	@Override
	public DataResult<JobAdvertisement> setJobAdvertisementDisabled(int id) {
		// TODO Auto-generated method stub
		if(!this.jobAdvertisementDao.existsById(id)) {
			return new ErrorDataResult<JobAdvertisement>("Hata: İş İlanı bulunamadı");
		}
		JobAdvertisement ref =  this.jobAdvertisementDao.getOne(id);
		ref.setActive(false);
		return new SuccessDataResult <JobAdvertisement>(this.jobAdvertisementDao.save(ref),"İş İlanı Pasif olarak ayarlandı");
	}

}
