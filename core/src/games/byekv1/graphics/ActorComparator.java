package games.byekv1.graphics;

import java.util.Comparator;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class ActorComparator implements Comparator<Actor> {

    @Override
    public int compare(Actor thisIns, Actor otherIns) {
        
        if (thisIns.getZIndex() == otherIns.getZIndex()) {
            return 0;
        }

        if (thisIns.getZIndex() < otherIns.getZIndex()) {
            return -1;
        }
        else return 1;

    }

}

