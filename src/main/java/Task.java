public class Task {
    private int id;
    private String name;
    private String description;
    private int effort;

    public Task() {}

    public Task(String name, String description, int effort) {
        this.name = name;
        this.description = description;
        this.effort = effort;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEffort() {
        return effort;
    }

    public void setEffort(int effort) {
        this.effort = effort;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!this.getClass().equals(obj.getClass())) return false;

        Task obj2 = (Task)obj;
        if((this.id == obj2.getId()) && (this.name.equals(obj2.getName()))) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return ( id + name ).hashCode();
    }
}
