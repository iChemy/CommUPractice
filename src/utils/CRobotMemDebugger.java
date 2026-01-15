package utils;

import jp.vstone.RobotLib.CRobotMem;
import jp.vstone.RobotLib.CRobotUtil;

public class CRobotMemDebugger {
    static final String TAG = "CRobotMemDebugger";

    public static void cRobotMemPrint(CRobotMem mem) {
        CRobotUtil.Log(TAG, "バッテリ測定電圧:" + mem.BatteryVoltage.get());
        CRobotUtil.Log(TAG, "衝突回避モード:" + ((mem.CollidionDetectDisable.get() == 0) ? "on" : "off"));
        CRobotUtil.Log(TAG, "モデル名:" + mem.ModelName.get());

        CRobotUtil.Log(TAG, "衝突判定:" + mem.ServoCollidionStatus.get());
    }
}
