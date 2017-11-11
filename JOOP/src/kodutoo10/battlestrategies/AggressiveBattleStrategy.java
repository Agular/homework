package kodutoo10.battlestrategies;

import kodutoo10.battlerobot.Robot;
import kodutoo10.robothardware.Motor;
import kodutoo10.robothardware.RobotHardware;

import java.math.BigInteger;

public class AggressiveBattleStrategy implements BattleStrategy {

    private final BigInteger ENEMY_HEIGHT_THRESHOLD = new BigInteger("10");
    private final BigInteger ENEMY_RETREAT_RPM_THRESHOLD = new BigInteger("500");

    @Override
    public void performActionsOnEnemyAttack(RobotHardware hardware, Robot enemy) {
        if (enemy.getHeight().compareTo(ENEMY_HEIGHT_THRESHOLD) <= 0) {
            hardware.getMotor().setRPM(new BigInteger("2000"));
        } else {
            hardware.getMotor().setRPM(new BigInteger("1500"));
        }
    }

    @Override
    public void performActionsOnEnemyRetreat(RobotHardware hardware, Robot enemy) {
        if (enemyRPMExceedsThreshold(enemy)) {
            hardware.getMotor().setRPMToMax();
        } else {
            hardware.getMotor().setRPM(new BigInteger("1500"));
        }
    }

    private boolean enemyRPMExceedsThreshold(Robot enemy) {
        return enemy.getHardware().getMotor().getRPM().compareTo(ENEMY_RETREAT_RPM_THRESHOLD) == 1;
    }

    @SuppressWarnings("Duplicates")
    @Override
    public void performActionsOnStandstill(RobotHardware hardware, Robot enemy) throws InterruptedException {
        Motor motor = hardware.getMotor();
        motor.setRPM(new BigInteger("1500"));
        Thread.sleep(1000);
        motor.setRPM(new BigInteger("300"));
        Thread.sleep(100);
        motor.setRPM(new BigInteger("2000"));
    }

    @Override
    public String toString(){
        return "Aggressive strategy";
    }
}
