package kodutoo10.battlestrategies;

import kodutoo10.battlerobot.Robot;
import kodutoo10.robothardware.RobotHardware;

public interface BattleStrategy {

    void performActionsOnEnemyAttack(RobotHardware hardware, Robot enemy);

    void performActionsOnEnemyRetreat(RobotHardware hardware, Robot enemy);

    void performActionsOnStandstill(RobotHardware hardware, Robot enemy) throws InterruptedException;
}
