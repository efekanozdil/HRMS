package kodlamaio.HRMS.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.HRMS.business.abstracts.JobTitleService;
import kodlamaio.HRMS.core.utilities.results.DataResult;
import kodlamaio.HRMS.core.utilities.results.Result;
import kodlamaio.HRMS.entities.concretes.JobTitle;

@RestController
@RequestMapping("/api/job-titles")
public class JobTitlesController {
	
	private JobTitleService jobTitleService;
	
	@Autowired
	public JobTitlesController(JobTitleService jobTitleService) {
		
		this.jobTitleService = jobTitleService;
	}


	@GetMapping("/getall")
	public DataResult<List<JobTitle>> getAll(){
		return  this.jobTitleService.getAll();
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody JobTitle jobtitle) {
		return this.jobTitleService.add(jobtitle);
		
	}
	

	

}
