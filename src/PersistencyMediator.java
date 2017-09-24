import java.util.Properties;

public interface PersistencyMediator {
    Drawing load(String drawingName);
    boolean save(Drawing drawing);
    boolean init(Properties props);
}
