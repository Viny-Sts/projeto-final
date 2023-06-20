package br.edu.ifg.luziania.model.util;

import javax.enterprise.context.SessionScoped;
import java.util.ArrayList;
import java.util.List;

@SessionScoped
public class Session {
    private String name;
    private List<Boolean> permissions;

    public Session() {
        this.permissions = new ArrayList<>();
        this.name = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Boolean> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Boolean> permissions) {
        this.permissions = permissions;
    }
}
