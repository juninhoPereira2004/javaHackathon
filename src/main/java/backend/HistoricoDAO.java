package backend;

import models.HealthRecord;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoricoDAO {
    public void addHealthRecord(int userId, String record) {
        String sql = "INSERT INTO health_records (user_id, record) VALUES (?, ?)";

        try (Connection conn = Banco.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            pstmt.setString(2, record);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<HealthRecord> getHealthRecords(int userId) {
        List<HealthRecord> records = new ArrayList<>();
        String sql = "SELECT * FROM health_records WHERE user_id = ?";

        try (Connection conn = Banco.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String record = rs.getString("record");
                records.add(new HealthRecord(id, userId, record));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return records;
    }
}
