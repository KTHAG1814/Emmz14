package KTH.stations.school;

/**
 * This class inherits from the Character class. It is a ball
 * with a x, y position. That is movable.
 *
 * @author EMMZ
 * @version 1.0
 */
public class Ball extends Character {
    private int SPEEDy;

    /**
     * Creates a new ball with given positions.
     *
     * @param x the x position of the ball
     * @param y the y position of the ball
     * @param imgPath the path to the image
     */
    public Ball(int x, int y, String imgPath) {
        super(x, y, imgPath);
        SPEEDy = 1;
    }

    /**
     * Moves the ball in a parabolic way. The height is fixed.
     *
     * @param fromX starting x position
     * @param toX ending x position
     */
    public void moveParabolic(int fromX, int toX){

        x += SPEED;
        y += SPEEDy;

        if (x == fromX)
            SPEED *= -1;

        if (x == toX){
            SPEED *= -1;
            SPEEDy *=-1;
        }

        if (x == (toX - fromX)/2 + fromX){
            SPEEDy *= -1;
        }

    }


}
