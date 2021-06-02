package kodlamaio.HRMS.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.HRMS.business.abstracts.JobAdvertisementService;
import kodlamaio.HRMS.core.utilities.results.DataResult;
import kodlamaio.HRMS.core.utilities.results.Result;
import kodlamaio.HRMS.entities.concretes.JobAdvertisement;

@RestController
@RequestMapping("/api/job-advertisements")
public class JobAdvertisementsController {
	
	private JobAdvertisementService jobAdvertisementService;

	@Autowired
	public JobAdvertisementsController(JobAdvertisementService jobAdvertisementService) {
		this.jobAdvertisementService = jobAdvertisementService;
	}
	
	@GetMapping("/getAllByIsActive")
	public DataResult<List<JobAdvertisement>> getAllByIsActive(@RequestParam boolean isActive) {
		return this.jobAdvertisementService.getAllByIsActive(isActive);
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody JobAdvertisement jobadvertisement) {
		return this.jobAdvertisementService.add(jobadvertisement);
	}
	
	@GetMapping("/getAll")
	public DataResult<List<JobAdvertisement>> getAll() {	
		return this.jobAdvertisementService.getAll();
	}
	@GetMapping("/getAllByIsActiveOrderByCreatedDateDesc")
	public DataResult<List<JobAdvertisement>> getAllByIsActiveOrderByCreatedDateDesc() {	
		return this.jobAdvertisementService.getAllByIsActiveOrderByCreatedDateDesc();
	}
	
	@GetMapping("/getEmployersActiveJobAdvertisement")
	public DataResult<List<JobAdvertisement>> getEmployersActiveJobAdvertisement(int id){
		return this.jobAdvertisementService.getEmployersActiveJobAdvertisement(id);
	}
	
	@PostMapping("/setJobAdvertisementDisabled")
	public DataResult<JobAdvertisement> setJobAdvertisementDisabled(int id){
		return this.jobAdvertisementService.setJobAdvertisementDisabled(id);
	}

}
