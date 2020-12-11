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

/** the plip class
 *  @author jingjing
 */

/* An implementation of a motile pacifist photosynthesizer. */
public class Plip extends Creature {

    private int r;
    private int g;
    private int b;

    /* creates plip with energy equal to E. */
    public Plip(double e) {
        super("plip");
        r = 0;
        g = 0;
        b = 0;
        energy = e;
    }

    /* creates a plip with energy equal to 1. */
    public Plip() {
        this(1);
    }

    @Override
    public Color color() {
        r = 99;
        g = 63 + (int)((255 - 63) / 2 * energy);
        b = 76;
        return color(r, g, b);
    }

    @Override
    public String name() {
        return super.name();
    }

    /* Do nothing with C, Plips are pacifists.*/
    @Override
    public void attack(Creature c) {
        return; // do nothing.
    }

    /**
     * Plips should lose 0.15 units of energy when moving. If you want to
     * to avoid the magic number warning, you'll need to make a
     * private static final variable. This is not required for this lab.
     */
    @Override
    public void move() {
        // TODO
        energy -= 0.15;
        if (energy < 0) { energy = 0;}
    }
    /**
     * Plips gain 0.2 energy when staying due to photosynthesis.
     */
    @Override
    public void stay() {
        // TODO
        energy += 0.2;
        if (energy > 2) { energy = 2;}

    }
    /**
     * Plips and their offspring each get 50% of the energy, with none
     * lost to the process. Now that's efficiency! Returns a baby Plip.*/
    @Override
    public Plip replicate() {
        // TODO
        this.energy /= 2.0;
        Plip newPlip = new Plip(this.energy);
        return newPlip;
    }

    /**
     * Plips take exactly the following actions based on NEIGHBORS:
     * 1. If no empty adjacent spaces, STAY.
     * 2. Otherwise, if energy >= 1, REPLICATE towards an empty direction chosen at random.
     * 3. Otherwise, if any Cloruses, MOVE with 50% probability, towards an empty direction chosen at random.
     * 4. Otherwise, if nothing else, STAY
     *
     * Returns an object of type Action. See Action.java for the scoop on how Actions work.
     */
    @Override
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        boolean anyClorus = false;
        // TODO
        for (Direction key : neighbors.keySet()) {
            if (neighbors.get(key).name().equals("empty")) {
                emptyNeighbors.add(key);
            }
            else if (neighbors.get(key).name().equals("clorus")){
                anyClorus = true;
            }
        }
        // Rule 1
        if (emptyNeighbors.size() == 0) {
            return new Action(Action.ActionType.STAY);
        // Rule 2
        } else if (this.energy >= 1.0) {
            return new Action(Action.ActionType.REPLICATE, randomEntry(emptyNeighbors));
        // Rule 3
        } else if (anyClorus == true && Math.random() < 0.5) {
            return new Action(Action.ActionType.MOVE, randomEntry(emptyNeighbors));
        // Rule 4
        } else {
            return new Action(Action.ActionType.STAY);
        }

    }
}
