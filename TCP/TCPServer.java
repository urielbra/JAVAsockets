import java.io.*; 
import java.net.*; 

class TCPServer { 

  public static void main(String argv[]) throws Exception 
    { 
      String clientSentence; 
      String capitalizedSentence; 

      ServerSocket welcomeSocket = new ServerSocket(6789); //Esse espera um PEDIDO DE CONEXÃO
  
      while(true) { 
  
        Socket connectionSocket = welcomeSocket.accept(); // Quando o pedido é aceito, cria-se outro Socker (aquele que realmente irá comunicar)
                                                          // Já se sabe automaticamente, a porta e o IP de quem pediu
        BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream())); 
	
	      DataOutputStream  outToClient = new DataOutputStream(connectionSocket.getOutputStream()); 

        clientSentence = inFromClient.readLine();  // Pega a string
        System.out.println("Recieved package:");
        System.out.println(clientSentence); 

        capitalizedSentence = clientSentence.toUpperCase() + '\n'; 

	      outToClient.writeBytes(capitalizedSentence); //Devolve
        System.out.println("Retrive package:");
        System.out.println(capitalizedSentence); 

      } 
    } 
} 