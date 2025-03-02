package org.stohirov.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.stohirov.models.Task;

@ApplicationScoped
public class TaskRepository implements PanacheRepository<Task> {

    public boolean existsByTitle(String title) {
        return count("title", title) > 0;
    }
}
