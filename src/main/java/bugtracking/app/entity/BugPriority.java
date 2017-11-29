package bugtracking.app.entity;

/**
 * @author Kiruba
 */
public enum BugPriority {
    BLOCKER(1), CRITICAL(2), MAJOR(3), MINOR(4), TRIVIAL(5);

    private final int id;

    private BugPriority(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
