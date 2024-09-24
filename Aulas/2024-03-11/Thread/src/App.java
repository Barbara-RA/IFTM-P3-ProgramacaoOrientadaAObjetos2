public class App {
    public static void main(String[] args) throws Exception {
        // Contador c=new Contador();
        // c.start();

        Contador contador=new Contador();
        Thread thread = new Thread(contador);
        thread.start();
        System.out.println("Terminei o programa principal");
    }
}
