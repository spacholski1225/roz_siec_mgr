import java.io.BufferedReader;  
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private final String _hostname;
    private final int _portNumber;
    private String _line;

    public Client(String hostname, int portNumber){
        _hostname = hostname.isEmpty() ? "localhost" : hostname;
        _portNumber = portNumber;
    }

    public void OpenConnection(){
        try {
            Socket socket = new Socket(_hostname, _portNumber);
            System.out.println("Połączono z serwerem: " + socket.getInetAddress() + ":" + socket.getPort());

            BufferedReader inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter outputStream = new PrintWriter(socket.getOutputStream(), true);
            Scanner userInput = new Scanner(System.in);

            while (true){
                System.out.print("Klient: ");
                _line = userInput.nextLine();

                outputStream.println(_line);
                outputStream.flush();

                if (_line.equalsIgnoreCase("exit")) {
                    System.out.println("Zakończono połączenie.");
                    break;
                }

                String serverResponse = inputStream.readLine();
                System.out.println("Serwer: " + serverResponse);
            }

            socket.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
