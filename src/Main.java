import java.util.Scanner;

public class Main {
    private static int quene = 1;
    private static Object lock = new Object();
    private static int totalThreads;

    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);

        System.out.print("Введите число потоков: ");
        totalThreads = sc.nextInt();
        sc.close();

        while (true) {
            for (int i = 1; i <=totalThreads; i++) {

                final int numberOfThread = i;

                Thread thread = new Thread("Поток-" + i) {
                    @Override
                    public void run() {
                        try {
                            while (true){
                                synchronized (lock) {
                                    while (quene != numberOfThread) {
                                        lock.wait();

                                    }

                                    System.out.println(getName());

                                    if (numberOfThread == totalThreads) {
                                        quene = 1;
                                    } else {
                                        quene = numberOfThread + 1;
                                    }

                                    lock.notifyAll();
                                }

                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };

                thread.start();

            }
        }
    }
}
