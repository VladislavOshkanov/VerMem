import java.util.Map;
import java.util.Set;

public class Versioned<T> {
    Map<Integer, T> versions;

    public T get() {
        return get(Revision.currentRevision.get());
    }
    public void set(T v) {
        set(Revision.currentRevision.get(), v);
    }

    private void set (Revision r, T value){
        if (versions.get(r.current.version) == null){
            r.current.written.add(this);
        }
        versions.put(r.current.version, value);
    }
    private T get(Revision r){
        Segment s = r.current;
        while (versions.get(s.version) == null){
            s = s.parent;
        }
        return versions.get(s.version);
    }

    public void release( Segment release ) {
        versions.put(release.version, null);
    }

    public void collapse( Revision main, Segment parent){
        if (versions.get(main.current.version) == null){
            set(main, versions.get(parent.version));
        }
        versions.put(parent.version, null);
    }
    public void merge(Revision main, Revision joinRev, Segment join){
        Segment s = joinRev.current;
        while (versions.get(s.version) == null) {
            s = s.parent;
        }
        if (s == join) {
            set(main, versions.get(join.version));
        }
    }

}
