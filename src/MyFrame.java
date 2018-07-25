import javax.swing.*;

public class MyFrame extends JFrame{

    public MyFrame(String title){
        super(title);
        setSize(600, 600);
        MyPanel myPanel = new MyPanel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(myPanel);
        setVisible(true);
    }
}
