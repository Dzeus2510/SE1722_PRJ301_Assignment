/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;
import dal.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Attendance;
import model.Course;
import model.Group;
import model.Instructor;
import model.Room;
import model.Session;
import model.Student;
import model.TimeSlot;
/**
 *
 * @author Doan Ngoc Vu
 */
public class AttendanceDBContext extends DBContext<Attendance>  {
     public ArrayList<Attendance> getAttendancesBySession(int sessionid) {
        String sql = "USE PRJ_diemdanh\n" +
"SELECT s.sid,s.sname,\n" +
"                 a.aid,\n" +
"                ISNULL(a.status,0) as [status],\n" +
"                ISNULL(a.comment,'') as [comment]\n" +
"                FROM Student s          LEFT JOIN [Group] g ON g.groupID = s.groupID\n" +
"                			 LEFT JOIN [Session] ses ON ses.groupID = g.groupID\n" +
"                			 LEFT JOIN [Attendance] a ON ses.sessionID = a.sessionID AND s.sid = a.sid\n" +
"                	WHERE ses.sessionID = ?";
        ArrayList<Attendance> atts = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {

            stm = connection.prepareStatement(sql);
            stm.setInt(1, sessionid);
            rs = stm.executeQuery();
            while (rs.next()) {
                Attendance a = new Attendance();
                a.setId(rs.getInt("aid"));
                a.setStatus(rs.getBoolean("status"));
                a.setComment(rs.getString("comment"));
                Student s = new Student();
                s.setId(rs.getInt("sid"));
                s.setName(rs.getString("sname"));
                a.setStudent(s);
                atts.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return atts;

    }

    public void update(ArrayList<Attendance> atts, int sessionid) {
        try {
            connection.setAutoCommit(false);
            String sql_update_session = "UPDATE [Session]\n"
                    + "   SET [status] = 1\n"
                    + " WHERE [groupID] = ?";
            PreparedStatement stm_update_session = connection.prepareStatement(sql_update_session);
            stm_update_session.setInt(1, sessionid);
            stm_update_session.executeUpdate();
            for (Attendance a : atts) {
                if (a.getId() == 0) {
                    //INSERT
                    String sql_insert_att = "INSERT INTO [Attendance]\n"
                            + "           ([sid]\n"
                            + "           ,[sessionID]\n"
                            + "           ,[status]\n"
                            + "           ,[comment])\n"
                            + "     VALUES\n"
                            + "           (?\n"
                            + "           ,?\n"
                            + "           ,?\n"
                            + "           ,?)";
                    PreparedStatement stm_insert_att = connection.prepareStatement(sql_insert_att);
                    stm_insert_att.setInt(1, a.getStudent().getId());
                    stm_insert_att.setInt(2, a.getSession().getId());
                    stm_insert_att.setBoolean(3, a.isStatus());
                    stm_insert_att.setString(4, a.getComment());
                    stm_insert_att.executeUpdate();
                } else {
                    //UPDATE
                    String sql_update_att = "UPDATE [Attendance]\n"
                            + "   SET \n"
                            + "	   [status] = ?\n"
                            + "      ,[comment] = ?\n"
                            + " WHERE [aid] = ?";
                    PreparedStatement stm_update_att = connection.prepareStatement(sql_update_att);
                    stm_update_att.setBoolean(1, a.isStatus());
                    stm_update_att.setString(2, a.getComment());
                    stm_update_att.setInt(3, a.getId());
                    stm_update_att.executeUpdate();
                }
            }

            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(dal.StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(dal.StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void insert(Attendance model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Attendance model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Attendance model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Attendance get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Attendance> all() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}


