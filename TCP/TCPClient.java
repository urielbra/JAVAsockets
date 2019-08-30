import java.io.*; 
import java.net.*; 
class TCPClient { 

    public static void main(String argv[ ]) throws Exception 
    { 
        String sentence; 
        String modifiedSentence; 

        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in)); 
        System.out.println("Digite o IP do servidor:");
	    InetAddress IPAddress = InetAddress.getByName(inFromUser.readLine());
        Socket clientSocket = new Socket(IPAddress, 6789); 

        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream()); 
	    BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); 

        System.out.println("Digite o texto a ser enviado para o servidor:");
        sentence = inFromUser.readLine(); 

        outToServer.writeBytes(sentence + '\n'); // Mandando a frase para o servidor

        modifiedSentence = inFromServer.readLine(); // O código para, aguardando o servidor

        System.out.println("FROM SERVER: " + modifiedSentence); 

        clientSocket.close(); // Fechar a conexão
                   
    } 
} 
