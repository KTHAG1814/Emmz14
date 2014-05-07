package KTH.stations.school;

import javax.swing.*;
import java.awt.*;

/**
 * This class represents different characters in the game.
 *
 * @author EMMZ
 * @version 1.0
 */
public class Character{

    protected int x, y;
    protected Image image;
    protected int SPEED = 5;
    protected int JUMP = 4;
    private boolean speakedWithCharacter;
    private boolean metCharacter;

    /**
     * Creates a new character with a given position and image.
     *
     * @param x the x position of the character
     * @param y the y postition of the character
     * @param imgPath the path to the image
     */
    public Character(int x, int y, String imgPath) {
        ImageIcon ii = new ImageIcon(this.getClass().getResource(imgPath));
        image = ii.getImage();
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the image of the character.
     * @return the image of the character
     */
    public Image getImage() {
        return image;
    }

    /**
     * Changes the image of the character.
     *
     * @param imgPath
     */
    public void changeImg(String imgPath){
        ImageIcon ii =
                new ImageIcon(this.getClass().getResource(imgPath));
        image = ii.getImage();
    }

    /**
     * Gives the x position of the character.
     *
     * @return the x position of the character.
     */
    public int getX() {
        return x;
    }

    /**
     * Gives the y position of the character.
     *
     * @return the y position of the character
     */
    public int getY() {
        return y;
    }

    /**
     * Moves the image of the character.
     *
     * @param yLimit to the specific y position
     */
    public void moveVertical(int yLimit) {
        if (y == yLimit)
            y = yLimit;

        if (y < yLimit){
            y += SPEED;
        }

        if (y > yLimit){
            y -= SPEED;
        }
    }

    /**
     * Makes the character jump.
     */
    public void jump() {
        y += JUMP;
        JUMP *= -1;
    }

    /**
     * Moves the character horizontal.
     *
     * @param xLimit to the specific x position
     */
    public void moveHorizontal(int xLimit) {
        if (x == xLimit)
            x = xLimit;

        if (x < xLimit){
            x += SPEED;
        }

        if (x > xLimit){
            x -= SPEED;
        }
    }

    /**
     * Checks if someone has spoken to this character.
     *
     * @return true if the conversation is finished, otherwise false
     */
    public boolean isSpeakedWithCharacter(){
        return speakedWithCharacter;
    }

    /**
     * This method is called when this character has been spoken to.
     */
    public void setSpeakedWithCharacter(){
        speakedWithCharacter = true;
    }

    /**
     * Checks if someone has met this character.
     *
     * @return true if someone has met the character, otherwise false
     *
     */
    public boolean isMetCharacter(){
        return metCharacter;
    }

    /**
     * This method is called when someone has met this character.
     */
    public void setMetCharacter(){
        metCharacter = true;
    }

    /**
     * This method checks is someone is close to this position. With fixed
     * (+/-) margin.
     *
     * @param directionX the x position
     * @param directionY the y position
     * @return true if it someone is close to this position, otherwise false
     */
    public boolean findDirection(int directionX, int directionY){
        return ((x <= directionX && x >= directionX - 40) | (x >= directionX && x <= directionX + 40))
                && ((y >= directionY && y <= directionY + 40));
    }

}
