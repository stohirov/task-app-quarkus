package org.stohirov.models;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.stohirov.models.enums.Priority;
import org.stohirov.models.enums.Status;

@Entity(name = "tasks")
@SQLRestriction("deleted=false")
@SQLDelete(sql = "update tasks set deleted=true where id = ?")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Task extends PanacheEntity {

    @Column(nullable = false)
    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Enumerated(EnumType.STRING)
    private Status status;

    private boolean deleted = false;

    public Task(String title, String description, Priority priority, Status status) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
    }

}
