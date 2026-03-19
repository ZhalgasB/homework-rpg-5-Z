package com.narxoz.rpg.facade;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.hero.HeroProfile;

import java.util.Random;

public class BattleService {
    private Random random = new Random(1L);

    public BattleService setRandomSeed(long seed) {
        this.random = new Random(seed);
        return this;
    }

    public AdventureResult battle(HeroProfile hero, BossEnemy boss, AttackAction action) {

        AdventureResult result = new AdventureResult();
        int rounds = 0;

        while (hero.isAlive() &&boss.isAlive() && rounds < 20){
            rounds++;

            int dmg = action.getDamage();
            dmg += random.nextInt(3)-1;
            boss.takeDamage(dmg);

            result.addLine(hero.getName() + " uses " + action.getActionName() + " dmg " + dmg +  " to " + boss.getName());

            if (!boss.isAlive())break;

            int bosDmg = boss.getAttackPower();
            hero.takeDamage(bosDmg);
            result.addLine(boss.getName() + "attack for " + bosDmg);

        }
        String winner;

        if (hero.isAlive()){
            winner = hero.getName();
        }
        else winner = boss.getName();

        result.setWinner(winner);
        result.setRounds(rounds);
        result.addLine("Battle ended after: " + rounds + "rounds");



        return result;
    }
}
