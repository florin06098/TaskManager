package fs.todo_project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Entity(name = "Files")
@Data
@NoArgsConstructor
@Getter
@Setter
public class AttachedFile implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "file_name")
    private String fileName;
    @Lob
    @Column(name = "file_data")
    @JsonIgnore
    private byte[] fileData;

    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "task_id")
    @JsonIgnore
    private Task task;
}
