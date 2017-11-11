package kodutoo10;

import kodutoo10.battlerobot.Robot;
import kodutoo10.battlestrategies.AggressiveBattleStrategy;
import kodutoo10.battlestrategies.DefensiveBattleStrategy;
import kodutoo10.robothardware.NormalGearbox;
import kodutoo10.robothardware.NormalMotor;
import kodutoo10.robothardware.RobotHardware;

import java.math.BigInteger;

public class MainBattleSystem {

    public static void main(String[] args) throws InterruptedException {
        RobotHardware defensiveHardware = new RobotHardware(new NormalMotor(new BigInteger("6000")),
                new NormalGearbox(new BigInteger("6")));

        RobotHardware aggressiveHardware = new RobotHardware(new NormalMotor(new BigInteger("10000")),
                new NormalGearbox(new BigInteger("8")));

        Robot defensiveRobot = new Robot("Paladin", new BigInteger("25"),
                new DefensiveBattleStrategy(), defensiveHardware);

        Robot aggressiveRobot = new Robot("Berserker", new BigInteger("15"),
                new AggressiveBattleStrategy(), aggressiveHardware);

        System.out.println(defensiveRobot.toString());
        System.out.println(aggressiveRobot.toString());

        defensiveRobot.getInstructionsOnEnemyAttack(aggressiveRobot);
        defensiveRobot.getInstructionsOnEnemyRetreat(aggressiveRobot);
        defensiveRobot.getInstructionsOnStandstill(aggressiveRobot);

        aggressiveRobot.getInstructionsOnEnemyAttack(defensiveRobot);
        aggressiveRobot.getInstructionsOnEnemyRetreat(defensiveRobot);
        aggressiveRobot.getInstructionsOnStandstill(defensiveRobot);
    }
}
