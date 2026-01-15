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

        CRobotPose commuInitPose = CCommUMotion.getInitPose();
        commuInitPose.setLED_CommU(Color.GREEN, 255, 255, Color.GREEN);
        CRobotPoseDebugger.cRobotPosePrint(commuInitPose);

        CRobotUtil.Log(TAG, "play:" + motion.play(commuInitPose, 1000));
        motion.waitEndinterpAll();
        CRobotUtil.Log(TAG, "interpolation end");
    }
}
