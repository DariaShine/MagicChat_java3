package lesson4;

public class MainApp {
    static Object mon = new Object();
    static volatile int currentNum = 1;
    final static int num = 5;

    public static void main(String[] args) throws InterruptedException {

        Thread th1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < num; i++) {
                    synchronized (mon){
                        while(currentNum != 1){
                            try {
                                mon.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println("A");
                        currentNum = 2;
                        mon.notifyAll();
                }
        }}});

        th1.start();

        Thread th2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < num; i++) {
                    synchronized (mon){
                        while(currentNum != 2){
                            try {
                                mon.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println("B");
                        currentNum = 3;
                        mon.notifyAll();
                    }
                }}});

        th2.start();

        Thread th3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < num; i++) {
                    synchronized (mon){
                        while(currentNum != 3){
                            try {
                                mon.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println("C");
                        currentNum = 1;
                        mon.notifyAll();
                    }
                }}});

        th3.start();
    }
}
