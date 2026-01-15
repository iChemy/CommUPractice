package main;

import jp.vstone.RobotLib.CCommUMotion;
import jp.vstone.RobotLib.CRobotMem;
import jp.vstone.RobotLib.CRobotUtil;
import utils.CRobotMemDebugger;

public class CCommUMotionPractice {
    static final String TAG = "CCommUMotionPractice";

    public static void main(String[] args) {
        // VSMDと通信ソケット・メモリアクセス用クラス
        CRobotMem mem = new CRobotMem();
        // Sota用モーション制御クラス
        CCommUMotion motion = new CCommUMotion(mem);

        if (mem.Connect()) {
            if (!motion.InitRobot_CommU()) {
                System.err.println("CommU の初期化中にエラーが検出されました");
                return;
            }
            CRobotMemDebugger.cRobotMemPrint(mem);

            CRobotUtil.Log(TAG, "enable collision detection");
            motion.EnableCollidionDetect();
            CRobotMemDebugger.cRobotMemPrint(mem);
            CRobotUtil.Log(TAG, "disable collision detection");
            motion.DisableCollidionDetect();
            CRobotMemDebugger.cRobotMemPrint(mem);
        } else {
            System.err.println("ロボットに接続できません");
        }
    }
}
