package br.edu.ifg.luziania.model.util;

import javax.enterprise.context.SessionScoped;
import java.util.ArrayList;
import java.util.List;

@SessionScoped
public class Session {
    private String name;
    private List<String> permissions;

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

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }
}
