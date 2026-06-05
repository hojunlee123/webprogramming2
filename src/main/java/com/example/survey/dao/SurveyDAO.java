package com.example.survey.dao;

import com.example.survey.model.FruitOption;
import com.example.survey.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 설문 관련 데이터 접근을 담당하는 DAO 클래스.
 */
public class SurveyDAO {

    /**
     * 과일 전체 목록을 현재 투표율이 높은 순서대로 조회한다.
     */
    public List<FruitOption> findAllByVoteRateDesc() throws SQLException, ClassNotFoundException {
        String sql =
                "SELECT fruit_id, "
                        + "fruit_name, "
                        + "vote_count, "
                        + "ROUND(vote_count * 100.0 / (SELECT SUM(vote_count) FROM fruit_option), 1) AS vote_rate "
                        + "FROM fruit_option "
                        + "ORDER BY vote_rate DESC, fruit_name ASC";

        List<FruitOption> options = new ArrayList<>();

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                FruitOption option = new FruitOption();
                option.setId(rs.getInt("fruit_id"));
                option.setName(rs.getString("fruit_name"));
                option.setVoteCount(rs.getInt("vote_count"));
                option.setVoteRate(rs.getDouble("vote_rate"));
                options.add(option);
            }
        }

        return options;
    }

    /**
     * 사용자가 선택한 과일의 투표 수를 1 증가시킨다.
     * 동일 사용자의 중복 투표를 막지 않으므로 여러 번 참여 가능하다.
     */
    public void increaseVote(int fruitId) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE fruit_option SET vote_count = vote_count + 1 WHERE fruit_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, fruitId);
            pstmt.executeUpdate();
        }
    }

    /**
     * 현재 전체 참가자 수를 반환한다.
     * 과제에서는 각 투표 1건을 참가 1회로 본다.
     */
    public int getTotalVotes() throws SQLException, ClassNotFoundException {
        String sql = "SELECT SUM(vote_count) AS total_votes FROM fruit_option";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("total_votes");
            }
        }

        return 0;
    }

    /**
     * 과일 번호 하나를 기준으로 상세 정보를 조회한다.
     */
    public FruitOption findById(int fruitId) throws SQLException, ClassNotFoundException {
        String sql =
                "SELECT fruit_id, "
                        + "fruit_name, "
                        + "vote_count, "
                        + "ROUND(vote_count * 100.0 / (SELECT SUM(vote_count) FROM fruit_option), 1) AS vote_rate "
                        + "FROM fruit_option "
                        + "WHERE fruit_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, fruitId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new FruitOption(
                            rs.getInt("fruit_id"),
                            rs.getString("fruit_name"),
                            rs.getInt("vote_count"),
                            rs.getDouble("vote_rate")
                    );
                }
            }
        }

        return null;
    }
}
