package kodutoo10.battlestrategies;

import kodutoo10.battlerobot.Robot;
import kodutoo10.robothardware.Motor;
import kodutoo10.robothardware.RobotHardware;

import java.math.BigInteger;

public class DefensiveBattleStrategy implements BattleStrategy {
    @Override
    public void performActionsOnEnemyAttack(RobotHardware hardware, Robot enemy) {
        hardware.getMotor().setRPM(new BigInteger("1000"));
        hardware.getGearbox().shiftToLowest();
    }

    @Override
    public void performActionsOnEnemyRetreat(RobotHardware hardware, Robot enemy) {
        hardware.getGearbox().shiftToNeutral();
        hardware.getMotor().setRPM(BigInteger.ZERO);
    }

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
        return "Defensive strategy";
    }
}
