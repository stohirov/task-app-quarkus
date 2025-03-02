package org.stohirov.payload;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TaskResDTO {

    private Long id;

    private String title;

    private String description;

    private String priority;

    private String status;

}
