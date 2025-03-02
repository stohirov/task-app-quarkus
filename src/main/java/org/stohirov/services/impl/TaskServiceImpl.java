package org.stohirov.services.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.stohirov.models.Task;
import org.stohirov.models.enums.Priority;
import org.stohirov.models.enums.Status;
import org.stohirov.payload.TaskReqDTO;
import org.stohirov.payload.TaskResDTO;
import org.stohirov.repository.TaskRepository;
import org.stohirov.services.TaskService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@ApplicationScoped
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public List<TaskResDTO> getAll() {
        return taskRepository.findAll().stream().map(this::entityToDTO).toList();
    }

    @Override
    public Optional<TaskResDTO> get(Long id) {
        Optional<Task> optional = taskRepository.findByIdOptional(id);
        if (optional.isEmpty()) return Optional.empty();

        Task task = optional.get();
        return Optional.of(entityToDTO(task));
    }

    @Override
    @Transactional
    public Optional<TaskResDTO> add(TaskReqDTO taskReqDTO) {
        boolean existByName = taskRepository.existsByTitle(taskReqDTO.getTitle());
        if (existByName) throw new EntityExistsException("Entity already exists!");

        Task task = dtoToEntity(taskReqDTO);
        taskRepository.persist(task);

        return Optional.of(entityToDTO(task));
    }

    @Override
    @Transactional
    public Optional<TaskResDTO> update(Long id, TaskReqDTO taskReqDTO) {
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<?> delete(Long id) {
        return Optional.empty();
    }

    private TaskResDTO entityToDTO(Task task) {
        return TaskResDTO.builder()
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus().toString())
                .priority(task.getPriority().toString())
                .build();
    }

    private Task dtoToEntity(TaskReqDTO taskReqDTO) {
        return new Task(
                taskReqDTO.getTitle(),
                taskReqDTO.getDescription(),
                Priority.valueOf(taskReqDTO.getPriority()),
                Status.valueOf(taskReqDTO.getStatus())
        );
    }

}
