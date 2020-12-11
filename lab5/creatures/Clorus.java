package creatures;

import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import static huglife.HugLifeUtils.randomEntry;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

/** the clorus class
 *  @author jingjing
 */

public class Clorus extends Creature {

    private int r;
    private int g;
    private int b;

    public Clorus(double e) {
        super("clorus");
        r = 0;
        g = 0;
        b = 0;
        energy = e;
    }

    @Override
    public Color color() {
        r = 34;
        g = 0;
        b = 231;
        return color(r, g, b);
    }

    @Override
    public String name() {
        return super.name();
    }

    public void stay() {
        energy -= 0.01;
    }

    public void move() {
        energy -= 0.03;
    }

    public void attack (Creature target) {
        this.energy += target.energy();
    }

    public Clorus replicate() {
        this.energy /= 2.0;
        Clorus babyClorus = new Clorus(this.energy);
        return babyClorus;
    }

    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> plipNeighbors = new ArrayDeque<>();
        for (Direction key : neighbors.keySet()) {
            if (neighbors.get(key).name().equals("empty")) {
                emptyNeighbors.add(key);
            } else if (neighbors.get(key).name().equals("plip")) {
                plipNeighbors.add(key);
            }
        }
        // Rule 1
        if (emptyNeighbors.size() == 0) {
            return new Action(Action.ActionType.STAY);
            // Rule 2
        } else if (plipNeighbors.size() > 0) {
            return new Action(Action.ActionType.ATTACK, randomEntry(plipNeighbors));
            // Rule 3
        } else if (energy >= 1) {
            return new Action(Action.ActionType.REPLICATE, randomEntry(emptyNeighbors));
            // Rule 4
        } else {
            return new Action(Action.ActionType.MOVE, randomEntry(emptyNeighbors));
        }
    }

}
