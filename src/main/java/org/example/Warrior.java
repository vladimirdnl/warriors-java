package org.example;

import javax.swing.*;

interface CanReceiveDamage extends HasHealth{
    void receiveDamage(int damage);
}

interface CanAttack{
    int getAttack();
    default void hit(CanReceiveDamage w){
        w.receiveDamage(getAttack());
    }
}
interface HasHealth{
    int getHealth();
    default boolean isAlive(){
        return getHealth()>0;
    }
}
interface Warrior extends CanReceiveDamage, CanAttack{
    static Warrior newWarrior() {return new WarriorImpl(50, 5);}
    static Warrior newKnight() {return new WarriorImpl(50, 7);}
    static Warrior newDefender() {return new WithDefence(new WarriorImpl(60, 3), 2);}
    static Warrior newVampire() {return new WithVampirism(new WarriorImpl(40, 4), 50);}
}


class WarriorImpl implements Warrior {
    private int health;
    private final int attack;

    WarriorImpl(int health, int attack){
        this.health = health;
        this.attack = attack;
    }

    @Override
    public int getHealth(){
        return health;
    }

    @Override
    public void hit (CanReceiveDamage w){

    }

    void setHealth(int health){
        this.health=health;
    }
    @Override
    public void receiveDamage(int damage){
        setHealth(getHealth() - damage);
    }

    @Override
    public int getAttack(){
        return attack;
    }
}

abstract class WarriorDecorator implements Warrior{
    private Warrior warrior;
    public WarriorDecorator(Warrior warrior){
        this.warrior = warrior;
    }

    protected Warrior getKernel(){
        if (warrior instanceof WarriorDecorator decorator){
            return getKernel();
        } else {
            return warrior;
        }
    }

    @Override
    public int getAttack() {
        return warrior.getAttack();
    }

    @Override
    public int getHealth() {
        return warrior.getHealth();
    }

    @Override
    public void receiveDamage(int damage) {
        warrior.receiveDamage(damage);
    }

    @Override
    public void hit(CanReceiveDamage w) {
        Warrior.super.hit(w);
    }
}

interface HasDefence {
    int getDefence();
}
class WithDefence extends WarriorDecorator implements HasDefence{
    private final int defence;

    public WithDefence(Warrior warrior, int defence){
        super(warrior);
        this.defence = defence;
    }

    public int getDefence(){
        return  defence;
    }

    @Override
    public void receiveDamage(int damage) {
        var reducedDamage = Math.max(0, damage-getDefence());
        super.receiveDamage(reducedDamage);
    }


}
interface HasVampirism{
    int getVampirism();
}
class WithVampirism extends WarriorDecorator implements HasVampirism{
    private final int vampirism;

    public WithVampirism(Warrior warrior, int vampirism){
        super(warrior);
        this.vampirism = vampirism;
    }

    public int getVampirism(){
        return  vampirism;
    }



    @Override
    public void hit(CanReceiveDamage w) {
        var healthBefore = w.getHealth();
        super.hit(w);
        var healthAfter = w.getHealth();
        var damageDealt = Math.max(0, healthBefore - healthAfter);
        var selfHealing = damageDealt*getVampirism()/100;
        var warrior = (WarriorImpl) getKernel();
        warrior.setHealth(warrior.getHealth()+selfHealing);
    }
}