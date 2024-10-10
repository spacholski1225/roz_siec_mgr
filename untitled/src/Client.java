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

            BufferedReader inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter outputStream = new PrintWriter(socket.getOutputStream(), true);
            Scanner userInput = new Scanner(System.in);
            while (true){
                _line = userInput.nextLine();

                if(_line.equals(".")){
                    break;
                }
                outputStream.println(_line);
                outputStream.flush();
                System.out.println(inputStream.readLine());

            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
