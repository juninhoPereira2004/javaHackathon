package backend;

import models.Visit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VisitaDAO {
    public void scheduleVisit(int userId, String visitDate) {
        String sql = "INSERT INTO visits (user_id, visit_date) VALUES (?, ?)";

        try (Connection conn = Banco.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            pstmt.setString(2, visitDate);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Visit> getVisits(int userId) {
        List<Visit> visits = new ArrayList<>();
        String sql = "SELECT * FROM visits WHERE user_id = ?";

        try (Connection conn = Banco.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String visitDate = rs.getString("visit_date");
                visits.add(new Visit(id, userId, visitDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return visits;
    }
}
