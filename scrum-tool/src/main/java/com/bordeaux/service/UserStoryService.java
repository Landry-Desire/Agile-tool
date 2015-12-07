package com.bordeaux.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bordeaux.entity.Task;
import com.bordeaux.entity.UserStory;
import com.bordeaux.entity.user.ScrumTeam;
import com.bordeaux.form.task.TaskForm;
import com.bordeaux.repository.UserStoryRepository;

@Service
public class UserStoryService {

	@Autowired
	private UserStoryRepository userStoryRepository;
	
	public void saveTaskInUserStory(int userStoryID,TaskForm taskForm) {
		UserStory userStory=userStoryRepository.findOne(userStoryID);
		Task task = new Task();
		task.setColor(taskForm.getColor());
		task.setDescription(taskForm.getDescription());
		task.setTag(taskForm.getTag());
		task.setAssociatedUs(new UserStory(userStoryID));
		task.setScrumTeam(new ScrumTeam(taskForm.getScrumTeamId()));
		userStory.getTasks().add(task);
		userStoryRepository.saveAndFlush(userStory); 
	}
	
	public UserStory getUserStory(int userStoryID){
		return userStoryRepository.findOne(userStoryID);
	}


	public void save(UserStory userStory) {
		userStoryRepository.save(userStory);
	}
}
