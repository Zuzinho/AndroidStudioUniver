package com.example.sigma.model.daos.projectDAO;

import com.example.sigma.model.models.Project;
import com.example.sigma.model.models.User;

public interface ProjectDAO {
    public Project get(int projectId);
    public void create(Project project);
    public void update(Project project);
    public void delete(Project project);
}
