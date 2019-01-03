/****************************************************************************
 * システム名: 特実検索システム
 * サブシステム名:対訳表示
 * 機能名: 対訳表示データ取得機能
 * 変更履歴:
 * Ver 日付       担当          コメント（問題点番号／修正内容）
 * 1.0 2018/06/01 沼澤 亜希子   新規作成
 *
 ****************************************************************************/

package jp.go.fss.btr.log;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ベンチマーク用のログ出力ユーティリティー.
 *
 * <p>
 * ベンチマーク採取開始時にbeginメソッドを呼び出し、終了時にendを呼び出す。<br>
 * 呼び出し時のキーにて、開始、終了を特定する。<br>
 */
public class BenchMarkLogUtil {

    /**
     * ロガー.
     */
    private static Logger logger = LoggerFactory.getLogger(BenchMarkLogUtil.class);

    /**
     * ベンチマーク用ロガーのキー.
     */
    private static final String LOGGER_KEY = "BenchMarkLog";

    /**
     * ベンチマーク用ロガー.
     */
    private static Logger benchLogger = LoggerFactory.getLogger(LOGGER_KEY);

    /**
     * 開始ログ情報保存エリア.
     */
    private static ThreadLocal<Map<String, Long>> benchMarkMap = new ThreadLocal<>();

    /**
     * コンストラクタ（new抑止）.
     */
    private BenchMarkLogUtil() {

    }

    /*
     * 性能測定対象
     * 以下の処理は、システム設計書業務機能編「20対訳表示機能.docx」表示性能の表「目標性能値」の処理
     * ○：対象、△：対訳表示データ取得機能以外、×：他システム
     *
     * [対象]  [処理No.]  [処理内容]
     * △      001        対訳表示連携ファイル読込み
     * ○      002        頁情報取得要求（原文）
     * ×      003        頁情報取得（原文）
     * ○      004        文献データ取得要求（原文）
     * ×      005        文献データ取得（原文）
     * ○      006        文献データSGML→XML変換（原文）
     * ○      007        表示用データ抽出（原文）
     * ○      008        頁情報取得要求（翻訳文）
     * ×      009        頁情報取得（翻訳文）
     * ○      010        文献データ取得要求（翻訳文）
     * ×      011        文献データ取得（翻訳文）
     * ○      012        文献データSGML→XML変換（翻訳文）
     * ○      013        表示用データ抽出（翻訳文）
     * ○      014        原文・翻訳文表示用データマージ
     * △      015        表示処理
     */
    /**
     * ベンチマークキー（頁情報取得要求（原文・翻訳文））.
     */
    public static final String BENCH_KEY_GET_PAGE = "BENCH_KEY_GET_PAGE"; // 頁情報取得要求（原文・翻訳文）
    /**
     * ベンチマークキー（文献データ取得要求（原文・翻訳文））.
     */
    public static final String BENCH_KEY_GET_TEXT = "BENCH_KEY_GET_TEXT"; // 文献データ取得要求（原文・翻訳文）
    /**
     * ベンチマークキー（文献データSGML→XML変換（原文・翻訳文））.
     */
    public static final String BENCH_KEY_CONV_SGML_XML = "BENCH_KEY_CONV_SGML_XML"; // 文献データSGML→XML変換（原文・翻訳文）
    /**
     * ベンチマークキー（表示用データ抽出（原文・翻訳文））.
     */
    public static final String BENCH_KEY_GET_DOCUMENT = "BENCH_KEY_GET_DOCUMENT"; // 表示用データ抽出（原文・翻訳文）
    /**
     * ベンチマークキー（原文・翻訳文表示用データマージ）.
     */
    public static final String BENCH_KEY_MERGE = "BENCH_KEY_MERGE"; // 原文・翻訳文表示用データマージ

    /**
     * ベンチマーク開始処理.
     *
     * <p>
     * ベンチマーク採取開始時に呼び出すメソッド。<br>
     * 呼び出し時のキーにて、ベンチマーク開始を特定する。
     *
     * @param key ベンチマークキー
     */
    public static void beginBenchMark(String key) {
        logger.trace("[Start]BenchMarkLogUtil.beginBenchMark key:[{}]", key);

        // 現在日時を設定する。
        long now = new Date().getTime();
        // 開始ログ情報保存エリアの現行スレッド値を取得する。
        Map<String, Long> bmMap = benchMarkMap.get();

        // 現行スレッド値がnullの場合
        if (bmMap == null) {
            logger.debug("BenchMarkLogUtil.beginBenchMark  if (bmMap == null)");
            bmMap = new HashMap<>();
            // 現行スレッド値にキー：引数.ベンチマークキー、値：現在日時を設定する。
            bmMap.put(key, Long.valueOf(now));
            // 開始ログ情報保存エリアのスレッドへ設定する。
            benchMarkMap.set(bmMap);
            // 現行スレッド値がnull以外の場合
        } else {
            logger.debug("BenchMarkLogUtil.beginBenchMark  else／bmMap.value:[{}]", bmMap.get(key));
            bmMap.put(key, Long.valueOf(now));
        }
        logger.trace("[End]BenchMarkLogUtil.beginBenchMark now:[{}]", Long.valueOf(now));
    }

    /**
     * ベンチマーク終了処理①.
     *
     * <p>
     * ベンチマーク採取終了時に呼び出すメソッド。<br>
     * 呼び出し時のキーにて、ベンチマーク終了を特定する。
     *
     * @param key ベンチマークキー
     */
    public static void endBenchMark(String key) {
        logger.trace("[Start]BenchMarkLogUtil.endBenchMark key:[{}]", key);
        // 引数にベンチマークキー、ベンチマーク用ロガーを設定し、ベンチマーク終了処理②を呼び出す。
        endBenchMark(key, benchLogger);
        logger.trace("[End]BenchMarkLogUtil.endBenchMark");
    }

    /**
     * ベンチマーク終了処理②.
     *
     * <p>
     * ベンチマーク採取終了時に実行するメソッド。<br>
     *
     * @param key ベンチマークキー
     * @param benchLogger ベンチマーク用ロガー
     */
    public static void endBenchMark(String key, Logger benchLogger) {
        logger.debug("BenchMarkLogUtil.endBenchMark key:[{}], benchLogger.getName():[{}]", key, benchLogger);

        // 終了日時に現在日時を設定する。
        long end = new Date().getTime();
        logger.debug("BenchMarkLogUtil.endBenchMark end:[{}]", end);
        // 開始ログ情報保存エリアの現行スレッド値を取得する。
        Map<String, Long> bmMap = benchMarkMap.get();

        // 現行スレッド値がnullの場合または引数.ベンチマークキーのマッピングが現行スレッド値に含まれていない場合
        if (bmMap == null || !bmMap.containsKey(key)) {
            logger.debug("BenchMarkLogUtil.endBenchMark  if (bmMap == null || !bmMap.containsKey(key))");
            // 異常(ERROR)メッセージ「"ベンチマークログ出力時に対応する開始がない。key:{キー}"」を出力する。
            benchLogger.error("ベンチマークログ出力時に対応する開始がない。key:[{}]", key);
            logger.debug("BenchMarkLogUtil.endBenchMark → return");
            // 何もせずにリターン(return)する。
            return;
        }

        // 開始ログ情報保存エリアの現行スレッド値からキー：引数.ベンチマークキーに紐づく値
        // （ベンチマーク開始処理で設定した現在日時）を取得し、開始日時に設定する。
        long begin = (bmMap.get(key)).longValue();
        logger.debug("BenchMarkLogUtil.endBenchMark begin:[{}]", begin);
        // 情報(INFO)メッセージ「"【ベンチマーク】key:{キー} time:{処理時間}(millisec)"」を出力する。
        benchLogger.info("【ベンチマーク】KEY:[{}] time:[{}(millisec)]", key, end - begin);
        logger.debug("BenchMarkLogUtil.endBenchMark end:[{}(millisec)], begin:[{}(millisec)], end - begin:[{}(millisec)]", end, begin, end - begin);

        // 開始ログ情報保存エリアの現行スレッド値からキー：引数.ベンチマークキーに紐づくマッピングデータを削除する。
        bmMap.remove(key);
    }
}
