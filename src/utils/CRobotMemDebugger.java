package utils;

import jp.vstone.RobotLib.CRobotMem;

public class CRobotMemDebugger {
    public static void cRobotMemPrint(CRobotMem mem) {
        System.out.println("バッテリ測定電圧:" + mem.BatteryVoltage.get());
        System.out.println("衝突回避モード:" + ((mem.CollidionDetectDisable.get() == 0) ? "on" : "off"));
        System.out.println("モデル名:" + mem.ModelName.get());
    }
}
