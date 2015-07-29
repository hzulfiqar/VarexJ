package cmu.jetty;

import gov.nasa.jpf.util.test.TestJPF;
import org.junit.Test;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author: chupanw
 */
public class JettyHelloWorldTest extends TestJPF{
    private final String[] config = {"+nhandler.delegateUnhandledNative", "+search.class=.search.RandomSearch", "+classpath+=${jpf-core}/lib/junit-4.11.jar,lib/jetty-all-7.6.18-SNAPSHOT.jar,lib/servlet-api-2.5.jar"};
    @Test(timeout = 6000000)
    public void testHelloWorldServer() throws Exception {
        if (verifyNoPropertyViolation(config)) {
            JettyHelloWorld.main(new String[]{});
        }
    }

    @Test
    public void testServer() throws Exception {
        if (verifyNoPropertyViolation(config)) {
            final int PORT = 8080;

            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Waiting for a client");
            Socket socket = serverSocket.accept();
            System.out.println("Client arrived");
            OutputStream socketOutput = socket.getOutputStream();
            InputStream socketInput = socket.getInputStream();

            int number = socketInput.read();
            System.out.println(number);
            socket.close();
        }
    }
}
