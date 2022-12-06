package org.example.characters;

public class Vampire extends Warrior{
    public Vampire(){
        super(40);
    }
    static final int ATTACK = 4;
    static final int VAMPIRISM = 50;

    @Override
    public int getAttack(){
        return ATTACK;
    }
    public int getVampirism(){
        return VAMPIRISM;
    }
    public void heal(int dealt_dmg){
        setHealth(getHealth() + Math.min(dealt_dmg * getVampirism() / 100, 40 - getHealth()));
    }
    @Override
    public int attack(Warrior war){
        int dealt_dmg = war.receiveDamage(getAttack());
        heal(dealt_dmg);
        return dealt_dmg;
    }

}
