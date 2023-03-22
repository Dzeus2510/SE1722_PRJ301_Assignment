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
import model.TimeSlot;

/**
 *
 * @author duong
 */
public class ListAttendanceGroupsDBContext extends DBContext<Session> {

    @Override
    public void insert(Session model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Session model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Session model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Session get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Session> all() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ArrayList<Session> allGroup(Date date, int tid) {
        ArrayList<Session> list = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "select * from Session l inner join Instructor i \n" +
"                                        on l.tid = i.tid inner join [Group] g \n" +
"                                        on l.GroupID = g.GroupID join TimeSlot t  \n" +
"                                        on l.slotID = t.slotID inner join Course c  \n" +
"                                        on g.cid = c.cid join Room r  \n" +
"                                        on l.RoomID = r.RoomID \n" +
"                                        where l.date = ? and l.tid = ?";
            stm = connection.prepareStatement(sql);
            stm.setDate(1, date);
            stm.setInt(2, tid);
            rs = stm.executeQuery();
            while (rs.next()) {
                Session s = new Session();
                s.setDate(rs.getDate("date"));
                s.setId(rs.getInt("sessionID"));
                s.setStatus(rs.getBoolean("status"));
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
                i.setGmail(rs.getString("tgmail"));
                i.setDob(rs.getDate("tdob"));
                i.setGender(rs.getBoolean("tgender"));
                i.setPhone(rs.getString("tphone"));

                s.setInstructor(i);
                Course c = new Course();
                c.setId(rs.getInt("cid"));
                c.setName(rs.getString("cname"));
                g.setCourse(c);
                s.setGroup(g);

                Room r = new Room();
                r.setId(rs.getInt("roomID"));
                r.setName(rs.getString("roomName"));
                s.setRoom(r);

                list.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListAttendanceGroupsDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(ListAttendanceGroupsDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(ListAttendanceGroupsDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ListAttendanceGroupsDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        ListAttendanceGroupsDBContext l = new ListAttendanceGroupsDBContext();
        ArrayList<Session> s = l.allGroup(Date.valueOf("2023-03-20"), 6);
        System.out.println(s.get(0).getId());
    }
}
