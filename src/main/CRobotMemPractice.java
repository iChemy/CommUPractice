package main;

import jp.vstone.RobotLib.CRobotMem;
import utils.CRobotMemDebugger;

public class CRobotMemPractice {
    public static void main(String[] args) {
        // VSMDと通信ソケット・メモリアクセス用クラス
        CRobotMem mem = new CRobotMem();

        if (mem.Connect()) {
            CRobotMemDebugger.cRobotMemPrint(mem);
        } else {
            System.err.println("ロボットに接続できません");
        }
    }
}
