package fs.todo_project.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity(name="Users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private String roles;

    public User(int id, String name, String email, String password, String roles) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    @ManyToMany(cascade ={CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    @JoinTable(name = "users_tasks",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id"))
    private List<Task> tasks = new ArrayList<>();
}