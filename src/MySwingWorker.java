import javax.swing.*;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.concurrent.ArrayBlockingQueue;

import static java.lang.Thread.sleep;
import static java.lang.Thread.yield;


public class MySwingWorker extends SwingWorker<Object, Object> {

    private Auto auto;
    private Object lock;
    private Object wjazd;
    private Object gardlo;
    private LinkedList<ReadyChecker> listaTankowania;
    private LinkedList<Object> listaDystrybutorow;
    private LinkedList<Integer> stanowiska;

    public MySwingWorker(Auto auto, Object lock, Object wjazd, Object gardlo, LinkedList<ReadyChecker> listaTankowania, LinkedList<Object> listaDystrybutorow, LinkedList<Integer> stanowiska) {

        this.auto = auto;
        this.lock = lock;
        this.wjazd = wjazd;
        this.gardlo = gardlo;
        this.listaDystrybutorow = listaDystrybutorow;
        this.listaTankowania = listaTankowania;
        this.stanowiska = stanowiska;
    }

    @Override
    protected Object doInBackground() throws Exception {

        while(true) {

            for (int i = 0; i < 150; i++) {
                sleep(auto.getSpeed());
                auto.decrementPositionY();
            }

            int ktory = 0;
            synchronized (wjazd) {
                boolean check = true;
                while (check) {
                    ktory = 0;
                    sleep(100);
                    for (ReadyChecker temp : listaTankowania) {
                        if (check == false){
                            sleep(1);
                        }
                        else if (temp.ready == 1) {
                            ktory++;
                        }
                        else {
                            temp.ready = 1;
                            check = false;
                        }
                    }
                }
            }

            synchronized (gardlo) {
                for (int i = 0; i < 100; i++) {
                    sleep(auto.getSpeed());
                    auto.incrementPositionX();
                }
            }

            if (ktory == 0) auto.droga1();
            else if (ktory == 1) auto.droga2();
            else if (ktory == 2) auto.droga3();
            else if (ktory == 3) auto.droga4();

            int y = auto.getY();

            if (auto.getPosition() == 400) {
                /*listaTankowania.put(auto.getY());
                System.out.println(listaTankowania.toString());*/

                synchronized (lock) {
                    stanowiska.add(y);
                }

                Object temp = null;
                if (y==100) temp = listaDystrybutorow.get(0);
                else if (y==200) temp = listaDystrybutorow.get(1);
                else if (y==300) temp = listaDystrybutorow.get(2);
                else if (y==400) temp = listaDystrybutorow.get(3);

                synchronized (temp) {
                    temp.wait();
                    listaTankowania.get(ktory).ready = 0;
                }
            }

            if (ktory == 0) auto.powrot1();
            else if (ktory == 1) auto.powrot2();
            else if (ktory == 2) auto.powrot3();
            else if (ktory == 3) auto.powrot4();

            synchronized (gardlo) {
                for (int i = 0; i < 100; i++) {
                    sleep(auto.getSpeed());
                    auto.decrementPositionX();
                }
            }

            for (int i = 0; i < 150; i++) {
                sleep(auto.getSpeed());
                auto.decrementPositionY();
            }

            /*for (ListIterator<Auto> it = listaAut.listIterator(); it.hasNext();) {
                Auto auto1 = it.next();
                if (auto1 == auto) it.remove();
            }*/

            sleep(250);

            auto.setX(50);
            auto.setY(400);

            sleep(250);
        }

        //return null;
    }
}
