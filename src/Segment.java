import java.util.List;

public class Segment {
    Segment parent;
    int version;
    int refcount;
    List<Versioned> written;
    static int versionCount = 0;

    Segment( Segment parent ) {
        this.parent = parent;
        if (parent != null) parent.refcount++;
        version = versionCount++;
        refcount = 1;
    }

    void Release(){
        if (--refcount == 0){
            for(Versioned v : written){
                
            }
        }
    }
}
