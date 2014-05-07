package KTH.stations.packing;

import javax.swing.*;
import java.util.Arrays;

public class PackingBoard extends JPanel {

    private boolean validPostion[][];
    int posX = 0;
    int posY = 0;
    int speed = 40;

    public PackingBoard() {
        setBounds(100, 140, 400, 400);

        validPostion = new boolean[11][10];
        for (int i = 0; i < validPostion.length - 1; i++) {
            Arrays.fill(validPostion[i], Boolean.TRUE);
        }
    }


}