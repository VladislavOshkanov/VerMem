public class Revision {
    static ThreadLocal<Revision> currentRevision;
    Segment root;
    Segment current;
    
    Revision (Segment root, Segment current) {
        this.root = root;
        this.current = current;
    }
    public Revision Fork () {
        Revision r;
        r = new Revision(current, new Segment(current));
        current.Release();
        current = new Segment(current);


    }
}
