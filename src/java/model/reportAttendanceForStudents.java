/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;


public class reportAttendanceForStudents {

    private TimeSlot slot;
    private Boolean status;
    private Room room;
    private String groupName;
    private String instructor;
    private String comment;
    private Date date;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public TimeSlot getSlot() {
        return slot;
    }

    public reportAttendanceForStudents(TimeSlot slot, Boolean status, Room room, String groupName, String instructor, Date date) {
        this.slot = slot;
        this.status = status;
        this.room = room;
        this.groupName = groupName;
        this.instructor = instructor;

        this.date = date;
    }

    public void setSlot(TimeSlot slot) {
        this.slot = slot;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public reportAttendanceForStudents() {
    }

}
