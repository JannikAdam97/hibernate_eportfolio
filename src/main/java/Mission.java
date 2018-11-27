import java.util.Set;

public class Mission {
    private int id;
    private String owner;
    private String missionname;
    private String description;
    private Set<Task> tasks;

    public Mission() {}

    public Mission(String owner, String missionname, String description, Set tasks) {
        this.owner = owner;
        this.missionname = missionname;
        this.description = description;
        this.tasks = tasks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getMissionname() {
        return missionname;
    }

    public void setMissionname(String missionname) {
        this.missionname = missionname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }
}
