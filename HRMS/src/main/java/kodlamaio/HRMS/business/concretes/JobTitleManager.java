package kodlamaio.HRMS.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import kodlamaio.HRMS.business.abstracts.JobTitleService;
import kodlamaio.HRMS.core.utilities.results.DataResult;
import kodlamaio.HRMS.core.utilities.results.ErrorResult;
import kodlamaio.HRMS.core.utilities.results.Result;
import kodlamaio.HRMS.core.utilities.results.SuccessDataResult;
import kodlamaio.HRMS.core.utilities.results.SuccessResult;
import kodlamaio.HRMS.dataAccess.abstracts.JobTitleDao;
import kodlamaio.HRMS.entities.concretes.JobTitle;

@Service
public class JobTitleManager implements JobTitleService {
	
	private JobTitleDao jobTitleDao;
	
	@Autowired
	public JobTitleManager(JobTitleDao jobTitleDao) {
		
		this.jobTitleDao = jobTitleDao;
	}

	@Override
	public DataResult<List<JobTitle>> getAll() {

		return new SuccessDataResult<List<JobTitle>>(jobTitleDao.findAll(), "İş pozisyonları listelendi.");
	}

	@Override
	public Result add(JobTitle jobtitle) {
		if(jobTitleDao.findAllByName(jobtitle.getName()).stream().count() != 0) {
			return new ErrorResult("Bu iş pozisyonu sistemde zaten mevcut!");
		}
		
		this.jobTitleDao.save(jobtitle);
		
		return new SuccessResult("İş pozisyonu başarıyla eklendi.");
	}




}
