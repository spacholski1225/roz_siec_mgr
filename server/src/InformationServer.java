import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class InformationServer {
    private HashMap<String, String> KeysDatabase = new HashMap<String, String>();

    public static void main(String[] args) {
        try {

            ServerSocket serverSocket = new ServerSocket(1234);

            System.out.println("Ten serwer pracuje " + serverSocket.getInetAddress() +":"+ serverSocket.getLocalPort());
            Socket clientSocket = serverSocket.accept();
            System.out.println("Kto się podłączył: " + clientSocket.getInetAddress() + ":" + clientSocket.getPort());

            PrintStream output = new PrintStream(clientSocket.getOutputStream());
            BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String clientMessage;
            while ((clientMessage = input.readLine()) != null) {
                System.out.println("Otrzymano od klienta: " + clientMessage);

                output.println("Serwer otrzymał: " + clientMessage);

                if (clientMessage.equalsIgnoreCase("exit")) {
                    System.out.println("Zakończono połączenie z klientem.");
                    break;
                }
            }

            clientSocket.close();
            serverSocket.close();
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