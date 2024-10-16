import Context.DbContext;
import Logic.Logic;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class InformationServer {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(2011);

            System.out.println("Ten serwer pracuje " + serverSocket.getInetAddress() +":"+ serverSocket.getLocalPort());
            Socket clientSocket = serverSocket.accept();
            System.out.println("Kto się podłączył: " + clientSocket.getInetAddress() + ":" + clientSocket.getPort());

            PrintStream output = new PrintStream(clientSocket.getOutputStream());
            BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String clientRequest;
            DbContext singletonContext = new DbContext();

            while ((clientRequest = input.readLine()) != null) {
                System.out.println("Otrzymano od klienta: " + clientRequest);
                output.println(requestHandler(clientRequest, singletonContext));
                output.flush();

                if (clientRequest.equalsIgnoreCase("exit")) {
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

    private static String requestHandler(String clientRequest, DbContext context) {

        Logic logic = new Logic(clientRequest, context);

        if(clientRequest.startsWith("key_in-get")){
            return logic.CheckIfKeyExists();
        }
        else if(clientRequest.startsWith("key_in-set")) {
            return logic.SetNewKey();
        }

        return "Bad Request cannot proceed.";
    }
}