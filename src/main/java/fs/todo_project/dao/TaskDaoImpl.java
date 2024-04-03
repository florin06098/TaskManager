package fs.todo_project.dao;

import fs.todo_project.entity.Task;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskDaoImpl implements TaskDao {
    EntityManager em;
    @Autowired
    public TaskDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Task getTask(int id) {
        return em.find(Task.class, id);
    }

    @Override
    public List<Task> getTasks() {
        TypedQuery<Task> allTasks = em.createQuery("FROM Tasks", Task.class);
        return allTasks.getResultList();
    }

    @Override
    @Transactional
    public Task save(Task task) {
        Task mergedTask = em.merge(task);
        return mergedTask;
    }

    @Override
    @Transactional
    public void deleteTask(int id) {
        Task task = getTask(id);
        em.remove(task);
    }
}
