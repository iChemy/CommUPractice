package main;

import jp.vstone.RobotLib.CCommUMotion;
import jp.vstone.RobotLib.CRobotPose;
import utils.CRobotPoseDebugger;

public class CCommUPosePractice {
    public static void main(String[] args) {
        CRobotPose commuInitPose = CCommUMotion.getInitPose();
        CRobotPoseDebugger.cRobotPosePrint(commuInitPose);
    }
}
