package web.dal;

import web.model.Researchers;
import web.model.Sites;
import web.model.Users;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResearchersDao extends UsersDao {
    private static ResearchersDao instance = null;
    protected ResearchersDao() {
        super();
    }
    public static ResearchersDao getInstance() {
        if(instance == null) {
            instance = new ResearchersDao();
        }
        return instance;
    }

    /**
     * Create researchers
     * @param researcher
     * @return researcher
     * @throws SQLException
     */
    public Researchers create(Researchers researcher) throws SQLException{
        Users users = create(new Users(researcher.getUserId(),researcher.getUserName(),
                researcher.getPassword(),researcher.getStatus()));
        researcher.setUserId(users.getUserId());

        String sql = "INSERT INTO Researchers(UserId, FirstName, LastName, " +
                "Gender, AcademicPaper, Institute) " +
                "VALUES(?, ?, ?, ?, ?, ?);";
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = connectionManager.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, researcher.getUserId());
            ps.setString(2,researcher.getFirstName());
            ps.setString(3,researcher.getLastName());
            ps.setBoolean(4,researcher.isGender());
            ps.setString(5,researcher.getAcademicPaper());
            ps.setString(6,researcher.getInstitute());
            ps.executeUpdate();

            return researcher;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(ps != null) {
                ps.close();
            }
        }
    }

    /**
     * Administrator can delete researcher
     * @param researcher
     * @return
     * @throws SQLException
     */
    public Researchers delete(Researchers researcher) throws SQLException{
        String sql = "DELETE FROM Researchers WHERE UserId = ?;";
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = connectionManager.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, researcher.getUserId());
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("No records available to delete for RestaurantKey=" + researcher.getUserId());
            }
            super.delete(researcher);
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(ps != null) {
                ps.close();
            }
        }
    }


    public Researchers getResearchersByUserId(int userId) throws SQLException{
        String sql = "SELECT UserId, FirstName, LastName, Gender, AcademicPaper, Institute" +
                " FROM Sites WHERE UserId = ?;";
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = connectionManager.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            while (rs.next()){
                int localuserId = rs.getInt("UserId");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                boolean isGender = rs.getBoolean("IsGender");
                String academicPaper = rs.getString("AcademicPaper");
                String institute = rs.getString("Institute");
                

                Researchers researcher = new Researchers(localuserId, firstName, lastName, isGender, academicPaper, institute);
                return researcher;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(ps != null) {
                ps.close();
            }
            if(rs != null) {
                rs.close();
            }
        }
        return null;
    }
    
    
}
