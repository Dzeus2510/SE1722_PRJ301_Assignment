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
import model.Group;
import model.Student;
import model.User;

/**
 *
 * @author sonnt
 */
public class StudentDBContext extends DBContext<Student> {

    @Override
    public void insert(Student model) {
        PreparedStatement stm = null;
        try {
            String sql = "INSERT INTO Student(sname,gender,dob,sgmail,sphone,username,groupID) VALUES(?,?,?,?,?,?,?)";
            stm = connection.prepareStatement(sql);
            stm.setString(1, model.getName());
            stm.setBoolean(2, model.isGender());
            stm.setDate(3, model.getDob());
            stm.setString(4, model.getGmail());
            stm.setString(5, model.getPhone());
            stm.setString(6, model.getUser().getUsername());
            stm.setInt(7, model.getGroup().getId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
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
    }

    @Override
    public void update(Student model) {
        PreparedStatement stm = null;
        try {
            String sql = "UPDATE [Student]\n"
                    + "   SET [sname] = ?\n"
                    + "      ,[gender] = ?\n"
                    + "      ,[dob] = ?\n"
                    + "      ,[sgmail] = ?\n"
                    + "      ,[sphone] = ?\n"
                    + "      ,[username] = ?\n"
                    + "      ,[groupID] = ?\n"
                    + " WHERE [sid] = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, model.getName());
            stm.setBoolean(2, model.isGender());
            stm.setDate(3, model.getDob());
            stm.setString(4, model.getGmail());
            stm.setString(5, model.getPhone());
            stm.setString(6, model.getUser().getUsername());
            stm.setInt(7, model.getGroup().getId());
            stm.setInt(8, model.getId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
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
    }

    @Override
    public void delete(Student model) {
        PreparedStatement stm = null;
        try {
            String sql = "DELETE Student WHERE [sid] = ?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, model.getId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
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
    }

    @Override
    public Student get(int id) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT s.[sid]\n" +
"      ,s.[sname]\n" +
"      ,s.[gender]\n" +
"      ,s.[dob]\n" +
"      ,s.[sgmail]\n" +
"      ,s.[sphone]\n" +
"      ,s.[username]\n" +
"      ,g.[groupID]\n" +
"	  ,g.[groupName]\n" +
"  FROM [PRJ_diemdanh].[dbo].[Student] s INNER JOIN [PRJ_diemdanh].[dbo].[Group] g\n" +
"  ON s.[groupID] = g.[groupID]\n" +
"  WHERE s.[sid] = ?";
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
                User u = new User();
                u.setUsername(rs.getString("username"));
                s.setUser(u);
                Group g = new Group();
                g.setId(rs.getInt("groupID"));
                g.setName(rs.getString("groupName"));
                s.setGroup(g);

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
            String sql = "SELECT s.[sid]\n" +
"      ,s.[sname]\n" +
"      ,s.[gender]\n" +
"      ,s.[dob]\n" +
"      ,s.[sgmail]\n" +
"      ,s.[sphone]\n" +
"      ,s.[username]\n" +
"      ,g.[groupID]\n" +
"	  ,g.[groupName]\n" +
"  FROM [PRJ_diemdanh].[dbo].[Student] s INNER JOIN [PRJ_diemdanh].[dbo].[Group] g\n" +
"  ON s.[groupID] = g.[groupID]";
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
                User u = new User();
                u.setUsername(rs.getString("username"));
                s.setUser(u);
                Group g = new Group();
                g.setId(rs.getInt("groupID"));
                g.setName(rs.getString("groupName"));
                s.setGroup(g);

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
            String sql = "SSELECT s.[sid]\n" +
"      ,s.[sname]\n" +
"      ,s.[gender]\n" +
"      ,s.[dob]\n" +
"      ,s.[sgmail]\n" +
"      ,s.[sphone]\n" +
"      ,s.[username]\n" +
"      ,g.[groupID]\n" +
"	  ,g.[groupName]\n" +
"  FROM [PRJ_diemdanh].[dbo].[Student] s INNER JOIN [PRJ_diemdanh].[dbo].[Group] g\n" +
"  ON s.[groupID] = g.[groupID]\n" +
"  WHERE g.[groupID] IN (";
            String params = "";
            for (Integer groupID : groupIDs) {
                params += "?,";
            }
            params = params.substring(0,params.length()-1);
            sql += params +")";
            stm = connection.prepareStatement(sql);
            for (int i = 0; i < groupIDs.size(); i++) {
                stm.setInt(i+1,groupIDs.get(i));
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
                User u = new User();
                u.setUsername(rs.getString("username"));
                Group g = new Group();
                g.setId(rs.getInt("groupID"));
                g.setName(rs.getString("groupName"));
                s.setGroup(g);

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

}
