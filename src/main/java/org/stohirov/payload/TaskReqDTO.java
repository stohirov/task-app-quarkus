package org.stohirov.payload;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class TaskReqDTO {

    private String title;
    private String description;
    private String priority;
    private String status;

}
