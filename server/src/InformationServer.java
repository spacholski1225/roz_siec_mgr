import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class InformationServer {
    private HashMap<String, String> KeysDatabase = new HashMap<String, String>();

    public static void main(String[] args) {
        try {

            ServerSocket s = new ServerSocket(1234);

            System.out.println("Ten serwer pracuje " + s.getInetAddress() +":"+ s.getLocalPort());
            Socket conn = s.accept();

            PrintStream p = new PrintStream(conn.getOutputStream());
            System.out.println("Kto się podłączył: " + conn.getInetAddress() + ":" + conn.getPort());
            System.out.println("A ja rozmawiam: " + conn.getLocalAddress() +":"+ conn.getLocalPort());
            p.println("Połączyłeś się z serwerem. Bye..");

            conn.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, String> getKeysDatabase() {
        return KeysDatabase;
    }

    public void setKeysDatabase(HashMap<String, String> keysDatabase) {
        KeysDatabase.put("test", "0000000001");
        KeysDatabase.put("admin", "6666666666");
    }
}