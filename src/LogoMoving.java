import javax.swing.*;

public class LogoMoving {
    public LogoMoving() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        GUIlogoMoving app = new GUIlogoMoving();
        app.setVisible(true);
    }
    public static void main(String[] arg) {
        new LogoMoving();
    }
}
class GUIlogoMoving extends JFrame{
    JLabel label = new JLabel();
    public GUIlogoMoving() {
        super("MovingLogo");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon ocean = new ImageIcon("ocean.jpg");
        setContentPane(new JLabel(ocean));
        pack();
        ImageIcon shrimp = new ImageIcon(getIconR(getRandomIntInclusive(1, 6)));
        int x = getRandomIntInclusive(10, ocean.getIconWidth()-shrimp.getIconWidth()-10);
        int y = getRandomIntInclusive(10, ocean.getIconHeight()-shrimp.getIconHeight()-10);
        label.setBounds(x, y, shrimp.getIconWidth(), shrimp.getIconHeight());
        label.setIcon(shrimp);
        add(label);
        setVisible(true);
        MovingWay(ocean.getIconWidth(), ocean.getIconHeight(), shrimp.getIconWidth(), shrimp.getIconHeight());
    }
    static int getRandomIntInclusive(int min, int max) {
        min = (int) Math.ceil(min);
        max = (int) Math.floor(max);
        return (int) (Math.floor(Math.random() * (max - min + 1)) + min);
    }
    static String getIconR(int n) {
        switch (n) {
            case (1):
                return "shrimp1r.png";
            case (2):
                return "shrimp2r.png";
            case (3):
                return "shrimp3r.png";
            case (4):
                return "shrimp4r.png";
            case (5):
                return "shrimp5r.png";
            case (6):
                return "shrimp6r.png";
        }
        return "shrimp1r.png";
    }
    static String getIconL(int n) {
        switch (n) {
            case (1):
                return "shrimp1l.png";
            case (2):
                return "shrimp2l.png";
            case (3):
                return "shrimp3l.png";
            case (4):
                return "shrimp4l.png";
            case (5):
                return "shrimp5l.png";
            case (6):
                return "shrimp6l.png";
        }
        return "shrimp1l.png";
    }
    void MovingWay(int w, int h, int w_shr, int h_shr) {
        String[] way = {"up-right"};
        int pause_sleep = 18;
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(true) {
                        if (label.getLocation().x+w_shr == w) {
                            if (way[0].equals("up-right")) {way[0]="up-left";}
                            if (way[0].equals("down-right")) {way[0]="down-left";}
                            label.setIcon(new ImageIcon(getIconL(getRandomIntInclusive(1, 6))));
                        }
                        if (label.getLocation().y+h_shr == h) {
                            if (way[0].equals("down-left")) {
                                way[0]="up-left";
                                label.setIcon(new ImageIcon(getIconL(getRandomIntInclusive(1, 6))));
                            }
                            if (way[0].equals("down-right")) {
                                way[0]="up-right";
                                label.setIcon(new ImageIcon(getIconR(getRandomIntInclusive(1, 6))));
                            }
                        }
                        if (label.getLocation().x == 0) {
                            if (way[0].equals("down-left")) {way[0]="down-right";}
                            if (way[0].equals("up-left")) {way[0]="up-right";}
                            label.setIcon(new ImageIcon(getIconR(getRandomIntInclusive(1, 6))));
                        }
                        if (label.getLocation().y == 0) {
                            if (way[0].equals("up-left")) {
                                way[0]="down-left";
                                label.setIcon(new ImageIcon(getIconL(getRandomIntInclusive(1, 6))));
                            }
                            if (way[0].equals("up-right")) {
                                way[0]="down-right";
                                label.setIcon(new ImageIcon(getIconR(getRandomIntInclusive(1, 6))));
                            }
                        }
                        if (way[0].equals("down-right")) {label.setLocation(label.getLocation().x+1, label.getLocation().y+1);}
                        if (way[0].equals("down-left")) {label.setLocation(label.getLocation().x-1, label.getLocation().y+1);}
                        if (way[0].equals("up-left")) {label.setLocation(label.getLocation().x-1, label.getLocation().y-1);}
                        if (way[0].equals("up-right")) {label.setLocation(label.getLocation().x+1, label.getLocation().y-1);}
                        Thread.sleep(pause_sleep);

                    }
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }
}