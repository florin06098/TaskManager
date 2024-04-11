package fs.todo_project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Comments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "commented_by_user")
    private String username;
    @Column(name = "comment")
    private String comment;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "task_id")
    @JsonIgnore
    private Task task;
}
