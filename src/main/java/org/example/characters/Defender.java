package org.example.characters;

public class Defender extends Warrior{
    static final int ATTACK = 3;
    static final int DEFENCE = 2;
    public Defender(){
       super(60);
    }
    @Override
    public int getAttack(){
        return ATTACK;
    }

    public int getDefence(){
        return DEFENCE;
    }
    @Override
    public int receiveDamage(int damage){
        return super.receiveDamage(damage - getDefence());
    }
}
