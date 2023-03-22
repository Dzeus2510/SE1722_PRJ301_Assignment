/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

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
import model.TimeSlot;
import model.reportAttendanceForStudents;

public class reportAttendanceForStudentsDBContext extends DBContext<reportAttendanceForStudents> {

    @Override
    public void insert(reportAttendanceForStudents model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(reportAttendanceForStudents model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(reportAttendanceForStudents model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public reportAttendanceForStudents get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<reportAttendanceForStudents> all() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ArrayList<Attendance> allAttendanceByStidCoid(int studentId, int courseId) {
        ArrayList<Attendance> attendance = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT ses.Date,ses.sessionId,ses.slotID,t.slotTime,g.groupID,g.groupName,c.cname,\n" +
"                    c.cid,r.roomID,r.roomName,a.Status,a.comment,i.tid, i.tname\n" +
"                    FROM Student s LEFT JOIN [Group] g ON g.groupID = s.groupID left join Course c \n" +
"                    on c.cid = g.cid JOIN [Session] ses ON ses.groupID = g.groupID\n" +
"                    LEFT JOIN [Attendance] a ON ses.sessionid = a.SessionID AND s.sid = a.sid\n" +
"                    left join Room r on r.RoomID = ses.RoomID left join Instructor i on ses.tid = i.tid\n" +
"                    left join TimeSlot t on t.SlotID = ses.slotID\n" +
"                    where s.sid = ? and c.cid = ?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, studentId);
            stm.setInt(2, courseId);
            rs = stm.executeQuery();
            while (rs.next()) {
                Attendance a = new Attendance();
                a.setStatus(rs.getBoolean("Status"));
                a.setComment(rs.getString("comment"));

                Session s = new Session();
                s.setDate(rs.getDate("Date"));
                s.setId(rs.getInt("sessionId"));

                TimeSlot t = new TimeSlot();
                t.setId(rs.getInt("slotID"));
                t.setTime(rs.getString("slotTime"));
                s.setSlot(t);

                Group g = new Group();
                g.setId(rs.getInt("groupID"));
                g.setName(rs.getString("groupName"));

                Instructor i = new Instructor();
                i.setId(rs.getInt("tid"));
                i.setName(rs.getString("tname"));

                s.setInstructor(i);
                Course c = new Course();
                c.setName(rs.getString("cname"));
                c.setId(rs.getInt("cid"));
                g.setCourse(c);
                s.setGroup(g);

                Room r = new Room();
                r.setId(rs.getInt("roomID"));
                r.setName(rs.getString("roomName"));
                s.setRoom(r);

                a.setSession(s);
                attendance.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(reportAttendanceForStudentsDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(reportAttendanceForStudentsDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(reportAttendanceForStudentsDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(reportAttendanceForStudentsDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return attendance;
    }

    public static void main(String[] args) {
        reportAttendanceForStudentsDBContext e = new reportAttendanceForStudentsDBContext();
        ArrayList<Attendance> attendance = e.allAttendanceByStidCoid(1, 19);
        System.out.println(attendance.get(0).getSession().getRoom().getName());
    }
}
