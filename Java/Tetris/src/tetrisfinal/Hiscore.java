package tetrisfinal;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by raluga on 24.04.2016.
 * This is the hiscore class.
 * I created this in order to better sort the hiscores.
 */
public class Hiscore implements Comparable {
    /***/
    String name;
    /***/
    Integer level;
    /***/
    Integer score;
    /***/
    private final StringProperty nameProperty;
    private final IntegerProperty levelProperty;
    private final IntegerProperty scoreProperty;

    /**
     * Create a new Hiscore.
     *
     * @param name  The name of the player.
     * @param level The level reached by the player.
     * @param score The final score of the player.
     */
    public Hiscore(String name, int level, int score) {
        this.name = name;
        this.level = level;
        this.score = score;
        this.nameProperty = new SimpleStringProperty(name);
        this.levelProperty = new SimpleIntegerProperty(level);
        this.scoreProperty = new SimpleIntegerProperty(score);
    }

    /***/
    public StringProperty getNameProperty() {
        return nameProperty;
    }

    /***/
    public IntegerProperty getLevelProperty() {
        return levelProperty;
    }

    /***/
    public IntegerProperty getScoreProperty() {
        return scoreProperty;
    }
    /***/
    public void setNameProperty(String fName) {
        nameProperty.set(fName);
    }
    /***/
    public void setLevelProperty(Integer flevel) {
        levelProperty.set(flevel);
    }
    /***/
    public void setScoreProperty(Integer fscore) {
        scoreProperty.set(fscore);
    }

    /**
     * Compare the hiscores. First by score, then by level.
     *
     * @param o The other hiscore.
     * @return Which hiscore is greater.
     */
    @Override
    public int compareTo(Object o) {
        Hiscore otherHiscore = (Hiscore) o;
        if (!this.score.equals(otherHiscore.score)) {
            return otherHiscore.score.compareTo(this.score);
        } else {
            return otherHiscore.level.compareTo(this.level);
        }
    }
}
