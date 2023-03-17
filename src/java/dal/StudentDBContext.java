/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Course;
import model.Group;
import model.Instructor;
import model.Room;
import model.Session;
import model.Student;
import model.TimeSlot;
import model.User;

/**
 *
 * @author sonnt
 */
public class StudentDBContext extends DBContext<Student> {

   

    @Override
    public Student get(int id) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT s.[sid]\n"
                    + "      ,s.[sname]\n"
                    + "      ,s.[gender]\n"
                    + "      ,s.[dob]\n"
                    + "      ,s.[sgmail]\n"
                    + "      ,s.[sphone]\n"
                    + "      ,g.[groupID]\n"
                    + "	  ,g.[groupName]\n"
                    + "  FROM [PRJ_diemdanh].[dbo].[Student] s INNER JOIN [PRJ_diemdanh].[dbo].[Group] g\n"
                    + "  ON s.[groupID] = g.[groupID]\n"
                    + "  WHERE s.[sid] = ?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            if (rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt("sid"));
                s.setName(rs.getString("sname"));
                s.setGender(rs.getBoolean("gender"));
                s.setDob(rs.getDate("dob"));
                s.setGmail(rs.getString("sgmail"));
                s.setPhone(rs.getString("sphone"));
                Group g = new Group();
                g.setId(rs.getInt("groupID"));
                g.setName(rs.getString("groupName"));
                s.getGroups().add(g);

                return s;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    @Override
    public ArrayList<Student> all() {
        ArrayList<Student> students = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT s.[sid]\n"
                    + "      ,s.[sname]\n"
                    + "      ,s.[gender]\n"
                    + "      ,s.[dob]\n"
                    + "      ,s.[sgmail]\n"
                    + "      ,s.[sphone]\n"
                    + "      ,g.[groupID]\n"
                    + "	  ,g.[groupName]\n"
                    + "  FROM [PRJ_diemdanh].[dbo].[Student] s INNER JOIN [PRJ_diemdanh].[dbo].[Group] g\n"
                    + "  ON s.[groupID] = g.[groupID]";
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt("sid"));
                s.setName(rs.getString("sname"));
                s.setGender(rs.getBoolean("gender"));
                s.setDob(rs.getDate("dob"));
                s.setGmail(rs.getString("sgmail"));
                s.setPhone(rs.getString("sphone"));
                Group g = new Group();
                g.setId(rs.getInt("groupID"));
                g.setName(rs.getString("groupName"));
                s.getGroups().add(g);

                students.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return students;
    }

    public ArrayList<Student> searchByIDs(ArrayList<Integer> groupIDs) {
        ArrayList<Student> students = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT s.[sid]\n"
                    + "      ,s.[sname]\n"
                    + "      ,s.[gender]\n"
                    + "      ,s.[dob]\n"
                    + "      ,s.[sgmail]\n"
                    + "      ,s.[sphone]\n"
                    + "      ,g.[groupID]\n"
                    + "      ,g.[groupName]\n"
                    + "  FROM [PRJ_diemdanh].[dbo].[Student] s INNER JOIN [PRJ_diemdanh].[dbo].[Group] g\n"
                    + "  ON s.[groupID] = g.[groupID]\n"
                    + "  WHERE g.[groupID] IN (";
            String params = "";
            for (Integer groupID : groupIDs) {
                params += "?,";
            }
            params = params.substring(0, params.length() - 1);
            sql += params + ")";
            stm = connection.prepareStatement(sql);
            for (int i = 0; i < groupIDs.size(); i++) {
                stm.setInt(i + 1, groupIDs.get(i));
            }
            rs = stm.executeQuery();
            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt("sid"));
                s.setName(rs.getString("sname"));
                s.setGender(rs.getBoolean("gender"));
                s.setDob(rs.getDate("dob"));
                s.setGmail(rs.getString("sgmail"));
                s.setPhone(rs.getString("sphone"));

                Group g = new Group();
                g.setId(rs.getInt("groupID"));
                g.setName(rs.getString("groupName"));
                s.getGroups().add(g);

                students.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return students;
    }

    public Student getTimeTable(int sid, Date from, Date to) {
        Student student = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT s.sid,s.sname\n"
                    + "         ,ses.sessionID,ses.date,ses.status\n"
                    + "         ,c.cid, c.cname\n"
                    + "	  ,g.groupID,g.groupName\n"
                    + "	  ,r.roomID,r.roomName\n"
                    + "	  ,i.tid,i.tname\n"
                    + "	  ,t.slotID,t.slotTime\n"
                    + "\n"
                    + "  FROM [Student] s INNER JOIN [Group] g ON g.groupID = s.groupID\n"
                    + "                   INNER JOIN [Course] c ON g.cid = c.cid\n"
                    + "                   INNER JOIN [Session] ses ON g.groupID = ses.groupID\n"
                    + "                   INNER JOIN [TimeSlot] t ON t.slotID = ses.slotID\n"
                    + "                   INNER JOIN [Room] r ON r.roomID = ses.roomID\n"
                    + "                   INNER JOIN [Instructor] i ON i.tid = ses.tid\n"
                    + "\n"
                    + "WHERE s.sid = ? AND ses.date >= ? AND ses.date <= ? ORDER BY s.sid, g.groupID";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, sid);
            stm.setDate(2, from);
            stm.setDate(3, to);
            rs = stm.executeQuery();
            Group currentGroup = new Group();
            currentGroup.setId(-1);
            while (rs.next()) {
                if (student == null) {
                    student = new Student();
                    student.setId(rs.getInt("sid"));
                    student.setName(rs.getString("sname"));
                }
                int gid = rs.getInt("groupID");
                if (gid != currentGroup.getId()) {
                    currentGroup = new Group();
                    currentGroup.setId(rs.getInt("groupID"));
                    currentGroup.setName(rs.getString("groupName"));
                    Course c = new Course();
                    c.setId(rs.getInt("cid"));
                    c.setName(rs.getString("cname"));
                    currentGroup.setCourse(c);
                    student.getGroups().add(currentGroup);
                }
                Session ses = new Session();
                ses.setId(rs.getInt("sessionID"));
                ses.setDate(rs.getDate("date"));
                ses.setStatus(rs.getBoolean("status"));
                ses.setGroup(currentGroup);

                Instructor i = new Instructor();
                i.setId(rs.getInt("tid"));
                i.setName(rs.getString("tname"));
                ses.setInstructor(i);

                Room r = new Room();
                r.setId(rs.getInt("roomID"));
                r.setName(rs.getString("roomName"));
                ses.setRoom(r);

                TimeSlot t = new TimeSlot();
                t.setId(rs.getInt("slotID"));
                t.setTime(rs.getString("slotTime"));
                ses.setSlot(t);

                currentGroup.getSessions().add(ses);

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
        return student;
    }

    @Override
    public void insert(Student model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Student model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Student model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
