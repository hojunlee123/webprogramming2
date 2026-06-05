package com.example.survey.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC 연결 생성을 담당하는 유틸리티 클래스.
 * 과제 환경에 맞게 URL, 사용자명, 비밀번호를 수정해서 사용한다.
 * MySQL이 실행 중이면 우선 사용하고, 연결이 되지 않으면 로컬 H2 DB로 자동 전환한다.
 */
public final class DBUtil {

    // 예시 기준은 MySQL 8.x 이다.
    private static final String URL = "jdbc:mysql://localhost:3306/fruit_survey?serverTimezone=Asia/Seoul";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1234";

    // MySQL이 없을 때도 사이트를 바로 확인할 수 있도록 H2 파일 DB를 함께 준비한다.
    private static final String FALLBACK_URL = "jdbc:h2:./data/fruit_survey;MODE=MySQL;DB_CLOSE_DELAY=-1";
    private static final String FALLBACK_USERNAME = "sa";
    private static final String FALLBACK_PASSWORD = "";

    private DBUtil() {
    }

    /**
     * 매 요청마다 새로운 DB 연결을 반환한다.
     * 1. MySQL 연결 시도
     * 2. 실패하면 H2 DB를 초기화한 뒤 연결 반환
     */
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException mysqlException) {
            Class.forName("org.h2.Driver");
            Connection fallbackConnection = DriverManager.getConnection(
                    FALLBACK_URL,
                    FALLBACK_USERNAME,
                    FALLBACK_PASSWORD
            );
            initializeFallbackSchema(fallbackConnection);
            return fallbackConnection;
        }
    }

    /**
     * H2 대체 DB에 필요한 테이블과 초기 데이터를 보장한다.
     * 이미 데이터가 있으면 중복 입력하지 않는다.
     */
    private static void initializeFallbackSchema(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(
                    "CREATE TABLE IF NOT EXISTS fruit_option ("
                            + "fruit_id INT AUTO_INCREMENT PRIMARY KEY, "
                            + "fruit_name VARCHAR(30) NOT NULL, "
                            + "vote_count INT NOT NULL DEFAULT 0)"
            );
        }

        try (PreparedStatement countStmt = conn.prepareStatement("SELECT COUNT(*) FROM fruit_option");
             java.sql.ResultSet rs = countStmt.executeQuery()) {
            if (rs.next() && rs.getInt(1) == 0) {
                try (PreparedStatement insertStmt = conn.prepareStatement(
                        "INSERT INTO fruit_option (fruit_name, vote_count) VALUES (?, ?)")) {
                    insertInitialFruit(insertStmt, "Apple", 25);
                    insertInitialFruit(insertStmt, "Grape", 25);
                    insertInitialFruit(insertStmt, "Strawberry", 25);
                    insertInitialFruit(insertStmt, "Melon", 25);
                }
            }
        }
    }

    /**
     * 과제 요구사항인 초기 25% 비율을 맞추기 위해 동일한 표 수로 저장한다.
     */
    private static void insertInitialFruit(PreparedStatement insertStmt, String fruitName, int voteCount)
            throws SQLException {
        insertStmt.setString(1, fruitName);
        insertStmt.setInt(2, voteCount);
        insertStmt.executeUpdate();
    }
}
