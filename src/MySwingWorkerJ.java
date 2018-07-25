import javax.swing.*;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.concurrent.ArrayBlockingQueue;

import static java.lang.Thread.sleep;



public class MySwingWorkerJ extends SwingWorker<Object, Object> {

    private Janusz janusz;
    private Object lockJ;
    private LinkedList<Object> listaDystrybutorow;
    private LinkedList<Integer> stanowiska;

    public MySwingWorkerJ(Janusz janusz, Object lockJ, LinkedList<Object> listaDystrybutorow, LinkedList<Integer> stanowiska) {

        this.janusz = janusz;
        this.lockJ = lockJ;
        this.listaDystrybutorow = listaDystrybutorow;
        this.stanowiska = stanowiska;
    }

    @Override
    protected Object doInBackground() throws Exception {

        while(true) {

            int y = 0;
            boolean check = true;
            while (check) {
                while (stanowiska.isEmpty()) { sleep(1); }
                synchronized (lockJ) {
                    if (stanowiska.isEmpty()) check = true;
                    else {
                        y = stanowiska.getFirst();
                        stanowiska.remove(0);
                        check = false;
                    }
                }
            }


            /*Integer taken = listaTankowania.take();
            int y = taken;*/

            Object temp = null;
            if (y==100) temp = listaDystrybutorow.get(0);
            else if (y==200) temp = listaDystrybutorow.get(1);
            else if (y==300) temp = listaDystrybutorow.get(2);
            else if (y==400) temp = listaDystrybutorow.get(3);

            synchronized (temp) {
                for (int i = 0; i <= (490 - y); i++) {
                    sleep(janusz.getSpeed());
                    janusz.decrementPosition();
                }
                sleep(500);
                temp.notify();
            }

            for (int i = 0; i <= (490 - y); i++) {
                sleep(janusz.getSpeed());
                janusz.incrementPosition();
            }

            /*for (ListIterator<Auto> it = listaAut.listIterator(); it.hasNext();) {
                Auto auto1 = it.next();
                if (auto1 == auto) it.remove();
            }*/
        }

        //return null;
    }
}

