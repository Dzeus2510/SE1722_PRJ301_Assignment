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

/**
 *
 * @author Doan Ngoc Vu
 */
public class InstructorDBContext extends DBContext<Student> {
    public Instructor getTimeTable(int tid, Date from, Date to) {
        Instructor instructor = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT distinct i.tid,i.tname\n" +
"       ,ses.sessionID,ses.date,ses.status\n" +
"       ,c.cid, c.cname\n" +
"       ,g.groupID,g.groupName\n" +
"       ,r.roomID,r.roomName\n" +
"       ,t.slotID,t.slotTime\n" +
"       FROM [Student] s INNER JOIN [Group] g ON g.groupID = s.groupID\n" +
"                                       INNER JOIN [Course] c ON g.cid = c.cid\n" +
"                                       INNER JOIN [Session] ses ON g.groupID = ses.groupID\n" +
"                                       INNER JOIN [TimeSlot] t ON t.slotID = ses.slotID\n" +
"                                       INNER JOIN [Room] r ON r.roomID = ses.roomID\n" +
"                                       INNER JOIN [Instructor] i ON i.tid = ses.tid\n" +
"                   WHERE i.tid = ? AND ses.date >= ? AND ses.date <= ? ORDER BY g.groupID";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, tid);
            stm.setDate(2, from);
            stm.setDate(3, to);
            rs = stm.executeQuery();
           while (rs.next()) {
                if (instructor == null) {
                    instructor = new Instructor();
                    instructor.setId(rs.getInt("tid"));
                    instructor.setName(rs.getString("tname"));
                }

                Session ses = new Session();
                ses.setId(rs.getInt("sessionID"));
                ses.setDate(rs.getDate("date"));

                Group group = new Group();
                group.setId(rs.getInt("groupID"));
                group.setName("groupName");
                ses.setGroup(group);

                Room r = new Room();
                r.setId(rs.getInt("roomID"));
                r.setName(rs.getString("roomName"));
                ses.setRoom(r);

                Course c = new Course();
                c.setId(rs.getInt("cid"));
                c.setName(rs.getString("cname"));
                group.setCourse(c);

                TimeSlot t = new TimeSlot();
                t.setId(rs.getInt("slotID"));
                t.setTime(rs.getString("slotTime"));
                ses.setSlot(t);

                group.getSessions().add(ses);
                instructor.getGroups().add(group);

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
        return instructor;
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

    @Override
    public Student get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Student> all() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
