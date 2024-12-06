import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Logger {
    private BufferedWriter writer;

    public Logger(String file) {
        try {
            writer = new BufferedWriter(new FileWriter (file,false));
        } catch (IOException e) {
            System.out.println("Could not open file " + file);
        }
    }

    public void writeLog(String record) {
        try{
            writer.write (record);
            writer.newLine();
            writer.flush();
        }catch (IOException e) {
            System.out.println("Could not write to file " + record);
        }
    }
    public void close() {
        try{
            if (writer != null) {
                writer.close ();
            }
        }catch (IOException e){
            System.out.println("Could not close file " + writer);

        }
    }
}
