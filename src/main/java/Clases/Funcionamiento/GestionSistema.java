package Clases.Funcionamiento;

import java.util.concurrent.Semaphore;

public class GestionSistema extends Thread{
    private Semaphore mutex = new Semaphore(1);
    private Thread hiloTiempo;
    private int n = 0;

    public GestionSistema(Thread hiloTiempo){
        this.hiloTiempo = hiloTiempo;
    }

    public void run(){
        try{
            if(Thread.currentThread() == hiloTiempo){
                mutex.acquire();
                tiempo();
                mutex.release();
            }
            //resto del codigo
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void tiempo() throws InterruptedException {
        while (true){
            n = n + 1;
            Thread.sleep(50);
        }
    }

}
