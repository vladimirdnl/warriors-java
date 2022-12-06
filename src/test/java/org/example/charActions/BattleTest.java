package org.example.charActions;

import org.example.characters.Warrior;
import org.example.characters.Knight;
import org.example.characters.Defender;
import org.example.characters.Rookie;
import org.example.characters.Vampire;

import org.example.Army;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BattleTest {
    @Test
    @DisplayName("ClassDef 1 (Warrior class definition)")
    void warClasDef(){
        boolean defined = true;
        Class<?> war = null;
        try {
            war = Class.forName("org.example.characters.Warrior");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        if (war == null) defined = false;
        assertTrue(defined);
    }

    @Test
    @DisplayName("ClassDef 2 (Knight class definition)")
    void knightClasDef(){
        boolean defined = true;
        Class<?> knight = null;
        try {
            knight = Class.forName("org.example.characters.Knight");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        if (knight == null) defined = false;
        assertTrue(defined);
    }

    @Test
    @DisplayName("ClassDef 3 (Army class definition)")
    void armyClasDef(){
        boolean defined = true;
        Class<?> army = null;
        try {
            army = Class.forName("org.example.Army");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        if (army == null) defined = false;
        assertTrue(defined);
    }

    @Test
    @DisplayName("ClassDef 4 (Defender class definition)")
    void defClasDef(){
        boolean defined = true;
        Class<?> defender = null;
        try {
            defender = Class.forName("org.example.characters.Defender");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        if (defender == null) defined = false;
        assertTrue(defined);
    }

    @Test
    @DisplayName("ClassDef 5 (Vampire class definition)")
    void vampClasDef(){
        boolean defined = true;
        Class<?> vamp = null;
        try {
            vamp = Class.forName("org.example.characters.Defender");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        if (vamp == null) defined = false;
        assertTrue(defined);
    }

    @Test
    @DisplayName("Smoke test 1 (for Warrior and Knight task)")
    void smokeTest1() {
        var chuck = new Warrior();
        var bruce = new Warrior();
        var carl = new Knight();
        var dave = new Warrior();

        assertTrue(Battle.fight(chuck, bruce));
        assertFalse(Battle.fight(dave, carl));
        assertTrue(chuck.isAlive());
        assertFalse(bruce.isAlive());
        assertTrue(carl.isAlive());
        assertFalse(dave.isAlive());
    }

    @Test
    @DisplayName("Smoke test 2 (for Army task)")
    void smokeTest2() {
        var chuck = new Warrior();
        var bruce = new Warrior();
        var carl = new Knight();
        var dave = new Warrior();
        var mark = new Warrior();

        assert Battle.fight(chuck, bruce);
        assert !Battle.fight(dave, carl);
        assert chuck.isAlive();
        assert !bruce.isAlive();
        assert carl.isAlive();
        assert !dave.isAlive();
        assert !Battle.fight(carl, mark);
        assert !carl.isAlive();

        var myArmy = new Army();
        myArmy.addUnits(Knight::new, 3);

        var enemyArmy = new Army();
        enemyArmy.addUnits(Warrior::new, 3);

        var army3 = new Army();
        army3.addUnits(Warrior::new, 20);
        army3.addUnits(Knight::new, 5);

        var army4 = new Army();
        army4.addUnits(Warrior::new, 30);

        assert Battle.battle(myArmy, enemyArmy);
        assert !Battle.battle(army3, army4);
    }

    @Test
    @DisplayName("Smoke test 3 (for Defender task")
    void smokeTest3() {
        var chuck = new Warrior();
        var bruce = new Warrior();
        var carl = new Knight();
        var dave = new Warrior();
        var mark = new Warrior();
        var bob = new Defender();
        var mike = new Knight();
        var rog = new Warrior();
        var lancelot = new Defender();

        assertTrue(Battle.fight(chuck, bruce));
        assertFalse(Battle.fight(dave, carl));
        assertTrue(chuck.isAlive());
        assertFalse(bruce.isAlive());
        assertTrue(carl.isAlive());
        assertFalse(dave.isAlive());
        assertFalse(Battle.fight(carl, mark));
        assertFalse(carl.isAlive());
        assertFalse(Battle.fight(bob, mike));
        assertTrue(Battle.fight(lancelot, rog));

        var myArmy = new Army();
        myArmy.addUnits(Defender::new, 1);

        var enemyArmy = new Army();
        enemyArmy.addUnits(Warrior::new, 2);

        var army3 = new Army();
        army3.addUnits(Warrior::new, 1);
        army3.addUnits(Defender::new, 1);

        var army4 = new Army();
        army4.addUnits(Warrior::new, 2);

        assertFalse(Battle.battle(myArmy, enemyArmy));
        assertTrue(Battle.battle(army3, army4));
    }

    @Test
    @DisplayName("Smoke test 4 (for Vampire task)")
    void smokeTest4() {
        var chuck = new Warrior();
        var bruce = new Warrior();
        var carl = new Knight();
        var dave = new Warrior();
        var mark = new Warrior();
        var bob = new Defender();
        var mike = new Knight();
        var rog = new Warrior();
        var lancelot = new Defender();
        var eric = new Vampire();
        var adam = new Vampire();
        var richard = new Defender();
        var ogre = new Warrior();

        assertTrue(Battle.fight(chuck, bruce));
        assertFalse(Battle.fight(dave, carl));

        assertTrue(chuck.isAlive());
        assertFalse(bruce.isAlive());
        assertTrue(carl.isAlive());
        assertFalse(dave.isAlive());

        assertFalse(Battle.fight(carl, mark));
        assertFalse(carl.isAlive());

        assertFalse(Battle.fight(bob, mike));
        assertTrue(Battle.fight(lancelot, rog));
        assertFalse(Battle.fight(eric, richard));
        assertTrue(Battle.fight(ogre, adam));

        var myArmy = new Army()
                .addUnits(Defender::new, 2)
                .addUnits(Vampire::new, 2)
                .addUnits(Warrior::new, 1);

        var enemyArmy = new Army()
                .addUnits(Warrior::new, 2)
                .addUnits(Defender::new, 2)
                .addUnits(Vampire::new, 3);

        var army3 = new Army()
                .addUnits(Warrior::new, 1)
                .addUnits(Defender::new, 4);

        var army4 = new Army()
                .addUnits(Vampire::new, 3)
                .addUnits(Warrior::new, 2);

        assertFalse(Battle.battle(myArmy, enemyArmy));
        assertTrue(Battle.battle(army3, army4));
    }

    @Test
    @DisplayName("1. Fight")
    void fight1(){
        var carl = new Warrior();
        var jim = new Knight();
        assertFalse(Battle.fight(carl, jim));
    }

    @Test
    @DisplayName("2. Fight")
    void fight2(){
        var slevin = new Warrior();
        var ramon = new Knight();
        assertTrue(Battle.fight(ramon, slevin));
    }

    @Test
    @DisplayName("3. Fight")
    void fight3(){
        var bob = new Warrior();
        var mars = new Warrior();
        Battle.fight(bob, mars);
        assertTrue(bob.isAlive());
    }

    @Test
    @DisplayName("4. Fight")
    void fight4(){
        var godkiller = new Warrior();
        var zeus = new Knight();
        Battle.fight(zeus, godkiller);
        assertTrue(zeus.isAlive());
    }

    @Test
    @DisplayName("5. Fight")
    void fight5(){
        var husband = new Warrior();
        var wife = new Warrior();
        Battle.fight(husband, wife);
        assertFalse(wife.isAlive());
    }

    @Test
    @DisplayName("6. Fight")
    void fight6(){
        var dragon = new Warrior();
        var knight = new Knight();
        Battle.fight(dragon, knight);
        assertTrue(knight.isAlive());
    }
    @Test
    @DisplayName("7. Fight")
    void fight7(){
        var unit_1 = new Warrior();
        var unit_2 = new Knight();
        var unit_3 = new Warrior();
        Battle.fight(unit_1, unit_2);
        assertFalse(Battle.fight(unit_2, unit_3));
    }

    @Test
    @DisplayName("8. Fight")
    void fight8(){
        var unit_1 = new Rookie();
        var unit_2 = new Defender();
        Battle.fight(unit_1, unit_2);
        assert unit_2.getHealth() == 60;
    }

    @Test
    @DisplayName("9. Fight")
    void fight9(){
        var unit_1 = new Rookie();
        var unit_2 = new Defender();
        var unit_3 = new Warrior();
        Battle.fight(unit_1, unit_2);
        assertTrue(Battle.fight(unit_2, unit_3));
    }

    @Test
    @DisplayName("10. Fight")
    void fight10(){
        var unit_1 = new Defender();
        var unit_2 = new Defender();
        assertTrue(Battle.fight(unit_1, unit_2));
    }

    @Test
    @DisplayName("1. Battle")
    void battle1 (){
        var army_1 = new Army();
        var army_2 = new Army();
        army_1.addUnits(Warrior::new, 1);
        army_2.addUnits(Warrior::new, 2);
        boolean result = Battle.battle(army_1, army_2);
        assertFalse(result);
    }

    @Test
    @DisplayName("2. Battle")
    void battle2 (){
        var army_1 = new Army();
        var army_2 = new Army();
        army_1.addUnits(Warrior::new, 2);
        army_2.addUnits(Warrior::new, 3);
        boolean result = Battle.battle(army_1, army_2);
        assertFalse(result);
    }

    @Test
    @DisplayName("3. Battle")
    void battle3 (){
        var army_1 = new Army();
        var army_2 = new Army();
        army_1.addUnits(Warrior::new, 5);
        army_2.addUnits(Warrior::new, 7);
        boolean result = Battle.battle(army_1, army_2);
        assertFalse(result);
    }

    @Test
    @DisplayName("4. Battle")
    void battle4 (){
        var army_1 = new Army();
        var army_2 = new Army();
        army_1.addUnits(Warrior::new, 20);
        army_2.addUnits(Warrior::new, 21);
        boolean result = Battle.battle(army_1, army_2);
        assertTrue(result);
    }

    @Test
    @DisplayName("5. Battle")
    void battle5 (){
        var army_1 = new Army();
        var army_2 = new Army();
        army_1.addUnits(Warrior::new, 10);
        army_2.addUnits(Warrior::new, 11);
        boolean result = Battle.battle(army_1, army_2);
        assertTrue(result);
    }

    @Test
    @DisplayName("6. Battle")
    void battle6 (){
        var army_1 = new Army();
        var army_2 = new Army();
        army_1.addUnits(Warrior::new, 11);
        army_2.addUnits(Warrior::new, 7);
        boolean result = Battle.battle(army_1, army_2);
        assertTrue(result);
    }

    @Test
    @DisplayName("7. Battle")
    void battle7 (){
        var army_1 = new Army();
        var army_2 = new Army();
        army_1.addUnits(Warrior::new, 5);
        army_1.addUnits(Defender::new, 4);
        army_2.addUnits(Defender::new, 5);
        army_2.addUnits(Warrior::new, 4);
        boolean result = Battle.battle(army_1, army_2);
        assertTrue(result);
    }

    @Test
    @DisplayName("8. Battle")
    void battle8 (){
        var army_1 = new Army();
        var army_2 = new Army();
        army_1.addUnits(Defender::new, 5);
        army_1.addUnits(Warrior::new, 20);
        army_2.addUnits(Warrior::new, 21);
        army_2.addUnits(Defender::new, 4);
        boolean result = Battle.battle(army_1, army_2);
        assertTrue(result);
    }

    @Test
    @DisplayName("9. Battle")
    void battle9 (){
        var army_1 = new Army();
        var army_2 = new Army();
        army_1.addUnits(Warrior::new, 10);
        army_1.addUnits(Defender::new, 5);
        army_2.addUnits(Defender::new, 5);
        army_1.addUnits(Warrior::new, 10);
        boolean result = Battle.battle(army_1, army_2);
        assertTrue(result);
    }

    @Test
    @DisplayName("10. Battle")
    void battle10 (){
        var army_1 = new Army();
        var army_2 = new Army();
        army_1.addUnits(Defender::new, 2);
        army_1.addUnits(Warrior::new, 1);
        army_1.addUnits(Defender::new, 1);
        army_2.addUnits(Warrior::new, 5);
        boolean result = Battle.battle(army_1, army_2);
        assertFalse(result);
    }

    @Test
    @DisplayName("11. Battle")
    void battle11(){
        var army_1 = new Army();
        var army_2 = new Army();
        army_1.addUnits(Defender::new, 5);
        army_1.addUnits(Vampire::new, 6);
        army_1.addUnits(Warrior::new, 7);
        army_2.addUnits(Warrior::new, 6);
        army_2.addUnits(Defender::new, 6);
        army_2.addUnits(Vampire::new, 6);
        boolean result = Battle.battle(army_1, army_2);
        assertFalse(result);
    }

    @Test
    @DisplayName("12. Battle")
    void battle12(){
        var army_1 = new Army();
        var army_2 = new Army();
        army_1.addUnits(Defender::new, 2);
        army_1.addUnits(Vampire::new, 3);
        army_1.addUnits(Warrior::new, 4);
        army_2.addUnits(Warrior::new, 4);
        army_2.addUnits(Defender::new, 4);
        army_2.addUnits(Vampire::new, 3);
        boolean result = Battle.battle(army_1, army_2);
        assertFalse(result);
    }

    @Test
    @DisplayName("13. Battle")
    void battle13(){
        var army_1 = new Army();
        var army_2 = new Army();
        army_1.addUnits(Defender::new, 11);
        army_1.addUnits(Vampire::new, 3);
        army_1.addUnits(Warrior::new, 4);
        army_2.addUnits(Warrior::new, 4);
        army_2.addUnits(Defender::new, 4);
        army_2.addUnits(Vampire::new, 13);
        boolean result = Battle.battle(army_1, army_2);
        assertTrue(result);
    }

    @Test
    @DisplayName("14. Battle")
    void battle14(){
        var army_1 = new Army();
        var army_2 = new Army();
        army_1.addUnits(Defender::new, 9);
        army_1.addUnits(Vampire::new, 3);
        army_1.addUnits(Warrior::new, 8);
        army_2.addUnits(Warrior::new, 4);
        army_2.addUnits(Defender::new, 4);
        army_2.addUnits(Vampire::new, 13);
        boolean result = Battle.battle(army_1, army_2);
        assertTrue(result);
    }
}