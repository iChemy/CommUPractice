package main;

import java.awt.Color;
import jp.vstone.RobotLib.CCommUMotion;
import jp.vstone.RobotLib.CRobotMem;
import jp.vstone.RobotLib.CRobotPose;
import jp.vstone.RobotLib.CRobotUtil;
import utils.CRobotPoseDebugger;

public class CCommUPosePractice {
    static final String TAG = "CCommUPosePractice";

    public static void main(String[] args) {
        // VSMDと通信ソケット・メモリアクセス用クラス
        CRobotMem mem = new CRobotMem();
        // Sota用モーション制御クラス
        CCommUMotion motion = new CCommUMotion(mem);

        if (!mem.Connect()) {
            System.err.println("ロボットに接続できません");
            return;
        }

        if (!motion.InitRobot_CommU()) {
            System.err.println("CommU の初期化中にエラーが検出されました");
            return;
        }

        CRobotPose pose = CCommUMotion.getInitPose();
        pose.setLED_CommU(Color.GREEN, 255, 255, Color.GREEN);
        CRobotPoseDebugger.cRobotPosePrint(pose);
        pose.addServoAngle(Byte.valueOf(CCommUMotion.SV_L_SHOULDER_P), (short) -1000);
        CRobotPoseDebugger.cRobotPosePrint(pose);
        pose.addServoAngle(Byte.valueOf(CCommUMotion.SV_R_SHOULDER_P), (short) 1000);
        CRobotPoseDebugger.cRobotPosePrint(pose);

        // サーボモータのソフトスタート (これがないと動かない...?)
        motion.ServoOn();

        CRobotUtil.Log(TAG, "play:" + motion.play(pose, 2000));
        motion.waitEndinterpAll();
        CRobotUtil.Log(TAG, "interpolation end");
    }
}
