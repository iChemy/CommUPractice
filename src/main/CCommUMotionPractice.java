package main;

import jp.vstone.RobotLib.CCommUMotion;
import jp.vstone.RobotLib.CRobotMem;
import jp.vstone.RobotLib.CSotaMotion;
import utils.CRobotMemDebugger;

public class CCommUMotionPractice {
    public static void main(String[] args) {
        // VSMDと通信ソケット・メモリアクセス用クラス
        CRobotMem mem = new CRobotMem();
        // Sota用モーション制御クラス
        CCommUMotion motion = new CCommUMotion(mem);

        if (mem.Connect()) {
            CRobotMemDebugger.cRobotMemPrint(mem);
            System.out.println("enable collision detection");
            motion.EnableCollidionDetect();
            CRobotMemDebugger.cRobotMemPrint(mem);
            System.out.println("disable collision detection");
            motion.DisableCollidionDetect();
            CRobotMemDebugger.cRobotMemPrint(mem);
        } else {
            System.err.println("ロボットに接続できません");
        }
    }
}
