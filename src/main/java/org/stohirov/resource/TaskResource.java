package org.stohirov.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import lombok.NonNull;
import org.stohirov.payload.TaskReqDTO;
import org.stohirov.payload.TaskResDTO;
import org.stohirov.services.TaskService;

import java.util.List;
import java.util.Optional;

@Path("/tasks")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TaskResource {

    @Inject
    TaskService taskService;

    @POST
    public Optional<TaskResDTO> create(TaskReqDTO taskReqDTO) {
        return taskService.add(taskReqDTO);
    }

    @GET
    @Path("/{id}")
    public Optional<TaskResDTO> get(@PathParam("id") @NonNull Long id) {
        return taskService.get(id);
    }

    @GET
    @Path("/all")
    public List<TaskResDTO> get() {
        return taskService.getAll();
    }

}
