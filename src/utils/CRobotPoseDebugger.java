package utils;

import java.util.Map;

import jp.vstone.RobotLib.CCommUMotion;
import jp.vstone.RobotLib.CRobotPose;
import jp.vstone.RobotLib.CRobotUtil;

public class CRobotPoseDebugger {
    static final String TAG = "CRobotPoseDebugger";

    /**
     * CRobotPoseに設定されている角度情報をコンソールに出力する
     * 
     * @param pose 確認対象のポーズオブジェクト
     */
    public static void cRobotPosePrint(CRobotPose pose) {
        CRobotUtil.Log(TAG, "====== Pose Debug Log ======");

        // 1. ユーザー指定の変数名リスト
        Byte[] targetIds = {
                CCommUMotion.SV_BODY_P, // Body Pitch
                CCommUMotion.SV_BODY_Y, // Body Yaw
                CCommUMotion.SV_L_SHOULDER_P, // Left Shoulder Pitch
                CCommUMotion.SV_L_SHOULDER_R, // Left Shoulder Roll
                CCommUMotion.SV_R_SHOULDER_P, // Right Shoulder Pitch
                CCommUMotion.SV_R_SHOULDER_R, // Right Shoulder Roll
                CCommUMotion.SV_HEAD_P, // Head Pitch
                CCommUMotion.SV_HEAD_R, // Head Roll
                CCommUMotion.SV_HEAD_Y, // Head Yaw
        };

        // 2. ログ表示用のラベル（targetIdsと同じ順番にする）
        String[] targetNames = {
                "BODY_P (体・前後)",
                "BODY_Y (体・回転)",
                "L_SHOL_P(左肩・縦)",
                "L_SHOL_R(左肩・横)",
                "R_SHOL_P(右肩・縦)",
                "R_SHOL_R(右肩・横)",
                "HEAD_P  (頭・上下)",
                "HEAD_R  (頭・傾き)",
                "HEAD_Y  (頭・左右)",
        };

        // 3. ループして値を出力
        for (int i = 0; i < targetIds.length; i++) {
            Byte id = targetIds[i];
            String name = targetNames[i];

            // CRobotPoseから値を取得 (設定されていない場合は null が返る)
            Short angle = pose.getServoAngle(id);

            if (angle != null) {
                // 値がある場合: 見やすく整形して表示
                CRobotUtil.Log(TAG, String.format("  %-15s : %4d", name, angle));
            } else {
                // 値がない場合
                CRobotUtil.Log(TAG, String.format("  %-15s : null (設定なし)", name));
            }
        }

        // LEDの情報があれば出力
        if (pose.getLed() != null) {
            CRobotUtil.Log(TAG, "  LED Color       : " + pose.getLed());
        }

        CRobotUtil.Log(TAG, "============================");
    }

    /**
     * CRobotPoseに設定されているトルク情報をコンソールに出力する
     * * @param pose 確認対象のポーズオブジェクト
     */
    public static void cRobotPoseTorquePrint(CRobotPose pose) {
        CRobotUtil.Log(TAG, "====== Torque Debug Log ======");

        // 1. ユーザー指定の変数名リスト (CommU用)
        Byte[] targetIds = {
                CCommUMotion.SV_BODY_P, // Body Pitch
                CCommUMotion.SV_BODY_Y, // Body Yaw
                CCommUMotion.SV_L_SHOULDER_P, // Left Shoulder Pitch
                CCommUMotion.SV_L_SHOULDER_R, // Left Shoulder Roll
                CCommUMotion.SV_R_SHOULDER_P, // Right Shoulder Pitch
                CCommUMotion.SV_R_SHOULDER_R, // Right Shoulder Roll
                CCommUMotion.SV_HEAD_P, // Head Pitch
                CCommUMotion.SV_HEAD_R, // Head Roll
                CCommUMotion.SV_HEAD_Y, // Head Yaw
        };

        // 2. ログ表示用のラベル
        String[] targetNames = {
                "BODY_P (体・前後)",
                "BODY_Y (体・回転)",
                "L_SHOL_P(左肩・縦)",
                "L_SHOL_R(左肩・横)",
                "R_SHOL_P(右肩・縦)",
                "R_SHOL_R(右肩・横)",
                "HEAD_P  (頭・上下)",
                "HEAD_R  (頭・傾き)",
                "HEAD_Y  (頭・左右)",
        };

        // ★重要: Javadocによると個別取得メソッドがないため、Map全体を取得する
        Map<Byte, Short> torqueMap = pose.getTorque();

        // 3. ループして値を出力
        for (int i = 0; i < targetIds.length; i++) {
            Byte id = targetIds[i];
            String name = targetNames[i];
            Short torque = null;

            // Mapが存在し、かつそのIDのトルクが設定されている場合のみ値を取得
            if (torqueMap != null && torqueMap.containsKey(id)) {
                torque = torqueMap.get(id);
            }

            if (torque != null) {
                // 値がある場合: 見やすく整形して表示 (0=OFF, 100=Max)
                CRobotUtil.Log(TAG, String.format("  %-15s : %4d", name, torque));
            } else {
                // 値がない場合
                CRobotUtil.Log(TAG, String.format("  %-15s : null (設定なし)", name));
            }
        }

        CRobotUtil.Log(TAG, "============================");
    }
}