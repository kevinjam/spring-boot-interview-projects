package com.kevinjanvier.ToDo.service;

import com.kevinjanvier.ToDo.dto.TaskDto;
import com.kevinjanvier.ToDo.entity.Task;
import com.kevinjanvier.ToDo.entity.User;
import com.kevinjanvier.ToDo.exceptions.ResourceNotFoundException;
import com.kevinjanvier.ToDo.repository.TaskRepository;
import com.kevinjanvier.ToDo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public List<TaskDto> getTasksForUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return taskRepository.findByUserId(user.getId())
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public TaskDto createTask(TaskDto taskDto, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Task task = new Task();
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setCompleted(taskDto.isCompleted());
        task.setUser(user);
        taskRepository.save(task);
        return convertToDto(task);
    }

    public TaskDto updateTask(Long id, TaskDto taskDto, String username) {
        Task task = getTaskByIdAndUser(id, username);
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setCompleted(taskDto.isCompleted());
        taskRepository.save(task);
        return convertToDto(task);
    }

    public void deleteTask(Long id, String username) {
        Task task = getTaskByIdAndUser(id, username);
        taskRepository.delete(task);
    }

    private Task getTaskByIdAndUser(Long id, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return taskRepository.findById(id)
                .filter(t -> t.getUser().equals(user))
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));
    }

    private TaskDto convertToDto(Task task) {
        return new TaskDto(task.getId(), task.getTitle(), task.getDescription(), task.isCompleted());
    }

    public Task getTask(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

    }
}