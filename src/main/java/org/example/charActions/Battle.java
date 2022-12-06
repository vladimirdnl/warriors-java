package org.example.charActions;

import org.example.Army;
import org.example.characters.Warrior;

public class Battle {
    public static boolean fight(Warrior war1, Warrior war2){
        while (true){
            if (!war1.isAlive()) return false;
            war1.attack(war2);
            if (!war2.isAlive()) return true;
            war2.attack(war1);
        }
    }

    public static boolean battle(Army army1, Army army2){
        var it1 = army1.firstAlive();
        var it2 = army2.firstAlive();
        while (it1.hasNext() && it2.hasNext()){
            fight(it1.next(), it2.next());
        }
        return it1.hasNext();
    }
}
