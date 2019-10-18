import java.io.*;
import java.net.*;

class UDPClient {
    public static void main(String args[]) throws Exception
    {
      // System.out.println("Type Server's IP");
      BufferedReader inFromUser =
        new BufferedReader(new InputStreamReader(System.in));

      DatagramSocket clientSocket = new DatagramSocket();
//InetAddress IPAddress = InetAddress.getByName("192.168.1.100");
      System.out.println("Digite o IP do servidor:");
	InetAddress IPAddress = InetAddress.getByName(inFromUser.readLine());

      byte[ ] sendData = new byte[1024];
      byte[ ] receiveData = new byte[1024];

      System.out.println("Digite o texto a ser enviado para o servidor:");
      String sentence = inFromUser.readLine();
      sendData = sentence.getBytes(); //Transforma para bytes
      DatagramPacket sendPacket =
      new DatagramPacket(sendData, sendData.length, IPAddress, 9876);

      clientSocket.send(sendPacket);

      DatagramPacket receivePacket =
         new DatagramPacket(receiveData, receiveData.length);

      clientSocket.receive(receivePacket); // Fica travado aguardando 

      String modifiedSentence =
          new String(receivePacket.getData());

      System.out.println("FROM SERVER:" + modifiedSentence);
      clientSocket.close();
      }
}
