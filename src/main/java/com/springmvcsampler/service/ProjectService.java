package com.springmvcsampler.service;

import com.springmvcsampler.model.Project;
import com.springmvcsampler.repository.ProjectRepository;
import com.springmvcsampler.web.form.ProjectCreateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;

@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
@Service("Service that manages projects")
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	@PostConstruct
	public void initialize() {
            this.save(new Project("Craggy Island Parochial House Extension", "Father Ted", Calendar.getInstance().getTime(), Calendar.getInstance().getTime(), "An extension to Father Hackets bedroom so he can store all his booze.", 10.00));
            this.save(new Project("MI5 secret tunnel", "M", Calendar.getInstance().getTime(), Calendar.getInstance().getTime(), "A secret tunnel under the thames to allow the spies to escape when Dr No comes calling.", 15.00));
	}

    public Project save(Project project) {
		projectRepository.save(project);
        return project;
    }

	public Project create(ProjectCreateForm form) {
		return projectRepository.save(new Project(form.getName(), form.getClientName(), form.getStartDate(), form.getEndDate(), form.getDescription(), form.getGlobalOHP()));
	}

	public Optional<Project> getProjectById(Long id) {
		return projectRepository.findById(id);
	}

	public Optional<Project> getProjectByName(String name) { return  projectRepository.findByName(name);}

	public Page<Project> getProjectByClientName(String clientName, Pageable pageable) { return  projectRepository.findByClientName(clientName, pageable);}

	public Page<Project> getAllProjects(Pageable pageable) {
		return projectRepository.findAll(pageable);
	}

}
