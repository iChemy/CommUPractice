package main;

import java.awt.Color;
import jp.vstone.RobotLib.CCommUMotion;
import jp.vstone.RobotLib.CRobotMem;
import jp.vstone.RobotLib.CRobotPose;
import jp.vstone.RobotLib.CRobotUtil;
import utils.CRobotMemDebugger;
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
        pose.addServoAngle(Byte.valueOf(CCommUMotion.SV_L_SHOULDER_P), (short) -2400);
        CRobotPoseDebugger.cRobotPosePrint(pose);
        pose.addServoAngle(Byte.valueOf(CCommUMotion.SV_R_SHOULDER_P), (short) 2400);
        CRobotPoseDebugger.cRobotPosePrint(pose);

        motion.EnableCollidionDetect();

        // サーボモータのソフトスタート (これがないと動かない...?)
        CRobotUtil.Log(TAG, "servo on");
        motion.ServoOn();

        CRobotMemDebugger.cRobotMemPrint(mem);

        CRobotPose init_pose = CCommUMotion.getInitPose();
        Byte[] poseIds = new Byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 };
        Short[] poseVals = new Short[] { 0, 0, 600, 0, -600, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        Byte[] torqueIds = new Byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 };
        Short[] torqueVals = new Short[] { 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 50, 50 };
        init_pose.SetPose(poseIds, poseVals);
        init_pose.SetTorque(torqueIds, torqueVals);
        pose.SetPose(poseIds, poseVals);
        pose.SetTorque(torqueIds, torqueVals);

        CRobotUtil.Log(TAG, "play:" + motion.play(init_pose, 2000));
        motion.waitEndinterpAll();
        CRobotUtil.Log(TAG, "interpolation end");
        CRobotPoseDebugger.cRobotPosePrint(init_pose);
        CRobotPoseDebugger.cRobotPoseTorquePrint(init_pose);

        CRobotMemDebugger.cRobotMemPrint(mem);

        CRobotUtil.Log(TAG, "play:" + motion.play(pose, 2000));
        motion.waitEndinterpAll();
        CRobotUtil.Log(TAG, "interpolation end");
        CRobotPoseDebugger.cRobotPosePrint(pose);
        CRobotPoseDebugger.cRobotPoseTorquePrint(pose);

        CRobotMemDebugger.cRobotMemPrint(mem);

        // すぐにサーボをoffにしても違いがわからない
        CRobotUtil.wait(10000);
        CRobotUtil.Log(TAG, "servo off");
        motion.ServoOff();
    }
}
