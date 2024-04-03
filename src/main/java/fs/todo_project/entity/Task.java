package fs.todo_project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity(name = "Tasks")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="started_on")
    private LocalDate startedOn;
    @Column(name="deadline")
    private LocalDate deadline;
    @Column(name="is_finished")
    private boolean isFinished;
}
