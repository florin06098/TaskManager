package fs.todo_project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "Tasks")
@NoArgsConstructor
@Getter
@Setter
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
    @ManyToMany(mappedBy = "tasks")
    @JsonIgnore
    private Set<User> users = new HashSet<>();
    @Column(name="task_status")
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;
    @Column(name="priority")
    @Enumerated(EnumType.STRING)
    private TaskPriority taskPriority;

    @OneToMany(cascade = {CascadeType.REMOVE}, fetch = FetchType.EAGER, mappedBy = "task")
    private List<UserComment> userComments = new ArrayList<>();
    @OneToMany(cascade = {CascadeType.REMOVE}, fetch = FetchType.EAGER, mappedBy = "task")
    private List<AttachedFile> files = new ArrayList<>();

}
