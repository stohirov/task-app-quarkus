package org.stohirov.services;

import org.stohirov.payload.TaskReqDTO;
import org.stohirov.payload.TaskResDTO;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    List<TaskResDTO> getAll();

    Optional<TaskResDTO> get(Long id);

    Optional<TaskResDTO> add(TaskReqDTO taskReqDTO);

    Optional<TaskResDTO> update(Long id, TaskReqDTO taskReqDTO);

    Optional<?> delete(Long id);

}
