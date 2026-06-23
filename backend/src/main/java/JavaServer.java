import bootstrap.AppSetup;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executors;

// this can become compact??

public final class JavaServer {
    static void main() {
        try {
            new AppSetup();
            // make it check if db exists, if not run setup

            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

            server.createContext("/", new baseHandler());
            server.setExecutor(Executors.newVirtualThreadPerTaskExecutor());
            server.start();

            System.out.println("Server is running on http://localhost:8080");
        } catch (AppSetup.AppSetupException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Error starting the server: " + e.getMessage());
        }

    }

    private static class baseHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            byte[] bytes = "testing".getBytes(StandardCharsets.UTF_8);
            exchange.sendResponseHeaders(200, bytes.length);

            try (OutputStream os = exchange.getResponseBody()) {
                os.write(bytes);
            }

        }

    }

}