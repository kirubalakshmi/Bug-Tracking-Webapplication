package bugtracking.app.entity;

/**
 * @author Kiruba
 */
public enum BugStatus {
    OPEN(1, false),
    HOLD(2, true),
    CLOSE(3, true);
   
    private final int id;
    private final boolean closed;


    private BugStatus(int id, boolean closed) {
        this.closed = closed;
        this.id = id;
    }

    public boolean isClosed() {
        return closed;
    }

    public int getId() {
        return id;
    }
}