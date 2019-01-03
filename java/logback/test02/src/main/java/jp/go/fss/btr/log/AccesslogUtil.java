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

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * アクセスログユーティリティ.
 *
 * <p>
 * アクセスログ関連処理を行う。<br>
 */
public class AccesslogUtil {

    /**
     * アクセスログ用ロガーのキー
     */
    private static final String LOGGER_KEY = "AccessLog";

    /**
     * アクセスログ用ロガー.
     */
    private static Logger accessLogger = LoggerFactory.getLogger(LOGGER_KEY);

    /**
     * コンストラクタ（new抑止）.
     */
    private AccesslogUtil() {

    }

    /**
     * アクセスログ出力.
     * 
     * <p>
     * ログフォーマットに合わせて出力メッセージを編集し、ログの出力を行う。
     * 出力フォーマットは以下
     * <ul>
     * <li>アクセス情報：アクセスされたメソッド、URL、プロトコルを出力  例) POST /getDocument HTTP/1.1</li>
     * <li>文献番号：文献番号を出力</li>
     * <li>担当官コード： 担当官コードを出力</li>
     * </ul>
     * </p>
     * 
     * @param request HTTPサーブレットリクエスト
     * @param documentNumber 文献番号
     * @param userId 担当官コード(ユーザID)
     */
    public static void putAccessLog(HttpServletRequest request, String documentNumber, String userId) {
        StringBuilder outputString = new StringBuilder();
        // 「アクセス情報各種(半角スペース区切り) + "," + 文献番号 + "," + 担当官コード」の形式でメッセージ出力文字列を作成
        outputString
            .append(request.getMethod()).append(" ")
            .append(request.getRequestURL()).append(" ")
            .append(request.getProtocol())
            .append(",").append(documentNumber)
            .append(",").append(userId);
        // ログ出力
        accessLogger.info(outputString.toString());
    }
}
