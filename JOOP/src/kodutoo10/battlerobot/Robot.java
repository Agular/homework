package kodutoo10.battlerobot;

import kodutoo10.battlestrategies.BattleStrategy;
import kodutoo10.robothardware.RobotHardware;

import java.math.BigInteger;

public class Robot {

    private String name;
    private BigInteger height;
    private RobotHardware hardware;
    private BattleStrategy battleStrategy;

    public Robot(String name, BigInteger height, BattleStrategy battleStrategy, RobotHardware hardware) {
        this.name = name;
        this.height = height;
        this.battleStrategy = battleStrategy;
        this.hardware = hardware;
    }

    public void attack(Robot enemy){
        System.out.println("Attacking enemy!");
    }

    public void getInstructionsOnEnemyAttack(Robot enemy) {
        battleStrategy.performActionsOnEnemyAttack(hardware, enemy);
    }

    public void getInstructionsOnEnemyRetreat(Robot enemy) {
        battleStrategy.performActionsOnEnemyRetreat(hardware, enemy);
    }

    public void getInstructionsOnStandstill(Robot enemy) throws InterruptedException {
        battleStrategy.performActionsOnStandstill(hardware, enemy);
    }

    public String getName() {
        return name;
    }

    public BigInteger getHeight() {
        return height;
    }

    public RobotHardware getHardware() {
        return hardware;
    }

    public BattleStrategy getBattleStrategy() {
        return battleStrategy;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\n" +
                hardware.toString() + "\n" +
                battleStrategy.toString();
    }
}
