import java.io.*;
import java.net.*;

class UDPServer {
  public static void main(String args[]) throws Exception
    {
      int porta = 9876;
      DatagramSocket serverSocket = new DatagramSocket(porta); //Cria uma porta no 9876

      byte[ ] receiveData = new byte[1024]; // Formatação em Bytes, multiplataforma, para se padronizar os dados de envio/recebimento
      byte[ ] sendData  = new byte[1024];
      serverSocket.connect(InetAddress.getByName("8.8.8.8"), 10002);
      String ip = serverSocket.getLocalAddress().getHostAddress();

      System.out.printf("Listening on %s:%d\n",ip,porta);
      while(true) // Servidores não desligam, ficam escutando eternamente
        {
          DatagramPacket receivePacket = new DatagramPacket (receiveData, receiveData.length); 
          serverSocket.receive(receivePacket); // Quando chega algum pacote no socket, o código fica travado nessa linha (orientação a eventos)
          String sentence = new String(receivePacket.getData());
          System.out.println("Recieved package:");
          System.out.println(sentence); 
          InetAddress IPAddress = receivePacket.getAddress();
          int port = receivePacket.getPort();
          String capitalizedSentence = sentence.toUpperCase(); //Trata o dado
          System.out.println("Retrive package:");
          System.out.println(capitalizedSentence); 

          sendData = capitalizedSentence.getBytes();

          DatagramPacket sendPacket =
             new DatagramPacket(sendData, sendData.length, IPAddress, port);

          serverSocket.send(sendPacket);
        }
    }
}