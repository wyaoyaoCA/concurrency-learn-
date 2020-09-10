package study.wyy.concurrency.guared;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        final RequestQueue requestQueue = new RequestQueue();
        ServerThread serverThread = new ServerThread(requestQueue);
        serverThread.start();
        new ClientThread(requestQueue).start();
        Thread.sleep(10000);
        serverThread.closed();
    }
}
