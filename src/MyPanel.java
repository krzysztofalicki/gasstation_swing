import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class MyPanel extends JPanel{

    LinkedList<Auto> listaAut = new LinkedList<>();
    LinkedList<Janusz> listaJanuszow = new LinkedList<>();
    LinkedList<ReadyChecker> listaTankowania = new LinkedList<>();
    LinkedList<Object> listaDystrybutorow = new LinkedList<>();
    LinkedList<Integer> stanowiska = new LinkedList<>();

    public MyPanel() {

        Object lock = new Object();
        Object lockJ = new Object();
        Object wjazd = new Object();
        Object gardlo = new Object();

        for (int i = 0; i < 4; i++) {
            listaDystrybutorow.add(new Object());
        }

        for (int i = 0; i < 4; i++) {
            listaTankowania.add(new ReadyChecker());
        }

        Janusz janusz1 = new Janusz(460, 470);
        Janusz janusz2 = new Janusz(480, 470);
        listaJanuszow.add(janusz1);
        listaJanuszow.add(janusz2);
        MySwingWorkerJ mySwingWorkerJ1 = new MySwingWorkerJ(janusz1, lockJ, listaDystrybutorow, stanowiska);
        MySwingWorkerJ mySwingWorkerJ2 = new MySwingWorkerJ(janusz2, lockJ, listaDystrybutorow, stanowiska);

        int speed = 10;

        for (int i = 0; i < 8; i++) {
            speed++;
            listaAut.add(new Auto(50,400,speed));
        }

        Timer timer = new Timer(200, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyPanel.this.repaint();
            }
        });
        timer.start();

        for (Auto auto : listaAut) {
            new MySwingWorker(auto, lock, wjazd, gardlo, listaTankowania, listaDystrybutorow, stanowiska).execute();
        }

        mySwingWorkerJ1.execute();
        mySwingWorkerJ2.execute();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.GRAY);
        g.fillRect(50,100,50,350);
        g.drawRect(90,250,70,50);
        g.fillRect(150,100,50,350);
        g.fillRect(150,100,300,50);
        g.fillRect(150,200,300,50);
        g.fillRect(150,300,300,50);
        g.fillRect(150,400,300,50);
        g.setColor(Color.GREEN);
        g.fillRect(50,400,50,50);
        g.setColor(Color.RED);
        g.fillRect(50,100,50,50);
        g.setColor(Color.BLACK);
        g.fillRect(400,70,100,30);
        g.fillRect(400,170,100,30);
        g.fillRect(400,270,100,30);
        g.fillRect(400,370,100,30);
        g.drawRect(400,460,100,30);

        for (Auto auto : listaAut) {
            g.setColor(auto.getColor());
            int width = auto.getWidth();
            g.fillRect(auto.getX(), auto.getY(), width, width);
        }

        for (Janusz janusz : listaJanuszow) {
            g.setColor(janusz.getColor());
            int width = janusz.getWidth();
            g.fillRect(janusz.getX(), janusz.getY(), width, width);
        }

    }
}
