import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClientNIST {
    public static byte[] getDateTime(String ip, int port) throws Exception {
        DatagramSocket socket = new DatagramSocket();

        // Create an empty byte array for the request (empty packet)
        byte[] request = new byte[0];

        // Prepare a DatagramPacket with the request data, IP address, and port
        DatagramPacket requestPacket = new DatagramPacket(request, request.length, InetAddress.getByName(ip), port);

        // Send the request to the server
        socket.send(requestPacket);

        // Prepare a DatagramPacket to receive the response from the server
        byte[] response = new byte[4]; // Since the response is 4 bytes
        DatagramPacket responsePacket = new DatagramPacket(response, response.length);

        // Receive the response from the server
        socket.receive(responsePacket);

        // Close the socket
        socket.close();

        // Return the received response (byte array containing seconds since 1900)
        return response;
    }

    public static ZonedDateTime convertDateTime(byte[] content) {
        // Use this method to convert the response into a readable format
        int value = 0;
        for (byte b : content) {
            value = (value << 8) + (b & 0xFF);
        }

        System.out.println(Integer.toUnsignedString(value));

        ZonedDateTime epoch = ZonedDateTime.of(1900, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC"));
        ZonedDateTime now = epoch.plusSeconds(Integer.toUnsignedLong(value))
                .withZoneSameInstant(ZoneId.of("America/New_York"));

        return now;
    }

    public static void main(String[] args) throws Exception {
        // Test your code here
        String ip = "127.0.0.1";
        int port = 1037;
        System.out.println(convertDateTime(getDateTime(ip, port)));
    }
}
