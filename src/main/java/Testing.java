
public class Testing {

    public static void main(String[] args) throws InterruptedException{

        long start = System.currentTimeMillis();

        for (long i = 0; i < 1_000_000; i++) {
            System.out.printf("\r %s", i);
            Thread.sleep(100);

        }
        System.out.println((System.currentTimeMillis() - start) + " MiliDetik");
    }
}
