package com.practicereactor;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
@NoArgsConstructor
public class Todos {
    @Id
    private String id;

    private String task;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date schedule;

    public Todos(String task, Date schedule) {
        this.task = task;
        this.schedule = schedule;
    }
}
