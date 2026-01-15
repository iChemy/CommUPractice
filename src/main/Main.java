package main;

import jp.vstone.RobotLib.CRobotMem;

public class Main {
    public static void main(String[] args) {
		//VSMDと通信ソケット・メモリアクセス用クラス
		CRobotMem mem = new CRobotMem();

        if (mem.Connect()) {
            cRobotMemDebug(mem);
        } else {
            System.err.println("ロボットに接続できません");
        }
    }

    static void cRobotMemDebug(CRobotMem mem) {
        System.out.println("バッテリ測定電圧:" + mem.BatteryVoltage.get());
        System.out.println("衝突回避モード:" + mem.CollidionDetectDisable.get());
        System.out.println("モデル名:" + mem.ModelName.get());
    }
}
