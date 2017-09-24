import java.io.*;
import java.util.Properties;

public class SerialisationMediator implements PersistencyMediator {

    @Override
    public Drawing load(String drawingName) {
        try {
            FileInputStream inputStream = new FileInputStream(drawingName);
            ObjectInputStream stream = new ObjectInputStream(inputStream);
            return (Drawing) stream.readObject();
        }
        catch (Exception ex) {
            return null;
        }
    }

    @Override
    public boolean save(Drawing drawing) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(drawing.getName());
            ObjectOutputStream stream = new ObjectOutputStream(fileOutputStream);
            stream.writeObject(drawing);
        }
        catch(Exception ex) {
            return false;
        }
        return true;
    }

    @Override
    public boolean init(Properties props) {
        return false;
    }
}
