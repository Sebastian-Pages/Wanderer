package soldier.gameManagment;

import soldier.core.UnitGroup;

import java.util.Set;
import java.util.TreeSet;

public class Map {
    private int length;
    private int height;
    private Set<Player> otherPlayers;

    public Map(int length,int height){
        this.length=length;
        this.height=height;
        this.otherPlayers= new TreeSet<Player>();
    }

    public void addPlayer(Player player){
        otherPlayers.add(player);
    }

    public int getLength() {
        return length;
    }

    public int getHeight() {
        return height;
    }

    public Set<Player> getOtherPlayers() {
        return otherPlayers;
    }
}
