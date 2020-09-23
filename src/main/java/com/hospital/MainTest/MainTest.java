package com.hospital.MainTest;

import com.hospital.conexion.Conexion;
import com.hospital.dao.ExamDao;
import com.hospital.dao.LabWorkerDao;
import com.hospital.model.Exam;
import com.hospital.model.LabWorker;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author cesar31
 */
public class MainTest {

    public static void main(String[] args) {
        //Write your code here
        Connection transaction = Conexion.getConnection();
        LabWorkerDao dao = new LabWorkerDao(transaction);
        List<LabWorker> d = dao.getLabWorkers();
        System.out.println("Laboratoristas");
        for (LabWorker d1 : d) {
            System.out.println(d1.toString());
        }
        System.out.println("Examenes");
        ExamDao daoE = new ExamDao(transaction);
        List<Exam> exams = daoE.getExams();
        for(Exam e : exams) {
            System.out.println(e.toString());
        }
    }
}