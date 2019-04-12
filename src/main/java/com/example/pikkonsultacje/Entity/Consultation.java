package com.example.pikkonsultacje.Entity;


import org.springframework.data.annotation.Id;

import java.util.Calendar;

public class Consultation {

    @Id
    private String id;
    private String tutorId;
    private String studentId;
    private Calendar date;
    private Calendar consultationStartTime;
    private Calendar consultationEndTime;
    private String room;

    public Consultation(){

    }

    public Consultation(String id, String tutorId, String studentId, Calendar date, Calendar consultationStartTime, Calendar consultationEndTime, String room) {
        this.id = id;
        this.tutorId = tutorId;
        this.studentId = studentId;
        this.date = date;
        this.consultationStartTime = consultationStartTime;
        this.consultationEndTime = consultationEndTime;
        this.room = room;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTutorId() {
        return tutorId;
    }

    public void setTutorId(String tutorId) {
        this.tutorId = tutorId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public Calendar getConsultationStartTime() {
        return consultationStartTime;
    }

    public void setConsultationStartTime(Calendar consultationStartTime) {
        this.consultationStartTime = consultationStartTime;
    }

    public Calendar getConsultationEndTime() {
        return consultationEndTime;
    }

    public void setConsultationEndTime(Calendar consultationEndTime) {
        this.consultationEndTime = consultationEndTime;
    }


    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }


    @Override
    public String toString() {
        return "Consultation{" +
                "id='" + id + '\'' +
                ", tutorId='" + tutorId + '\'' +
                ", studentId='" + studentId + '\'' +
                ", date=" + date +
                ", consultationStartTime=" + consultationStartTime +
                ", consultationEndTime=" + consultationEndTime +
                ", room=" + room +
                '}';
    }


}
