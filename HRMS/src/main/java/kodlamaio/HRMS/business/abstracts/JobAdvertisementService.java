package kodlamaio.HRMS.business.abstracts;


import java.util.List;

import kodlamaio.HRMS.core.utilities.results.DataResult;
import kodlamaio.HRMS.core.utilities.results.Result;
import kodlamaio.HRMS.entities.concretes.JobAdvertisement;

public interface JobAdvertisementService {
	DataResult<List<JobAdvertisement>> getAllByIsActive(boolean isActive);
	Result add(JobAdvertisement jobadvertisement);
	DataResult<List<JobAdvertisement>> getAll();
	DataResult<List<JobAdvertisement>> getAllByIsActiveOrderByCreatedDateDesc();
	DataResult<List<JobAdvertisement>> getEmployersActiveJobAdvertisement(int id);
	DataResult<JobAdvertisement> setJobAdvertisementDisabled(int id);
	
	
}
