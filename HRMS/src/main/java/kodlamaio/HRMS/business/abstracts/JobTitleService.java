package kodlamaio.HRMS.business.abstracts;

import java.util.List;

import kodlamaio.HRMS.core.utilities.results.DataResult;
import kodlamaio.HRMS.core.utilities.results.Result;
import kodlamaio.HRMS.entities.concretes.Candidate;
import kodlamaio.HRMS.entities.concretes.JobTitle;

public interface JobTitleService {
			
	DataResult<List<JobTitle>> getAll();
	Result add(JobTitle jobtitle);
		
}
