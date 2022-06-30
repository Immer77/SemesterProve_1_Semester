package model;//package model;
//
//import controller.Controller;
//
//import java.time.LocalDateTime;
//import java.time.LocalTime;
//
//public class App {
//    public static void main(String[] args) {
//        Funktion f1 = Controller.createFunktion("Filetering");
//        Funktion f2 = Controller.createFunktion("Grøntsager");
//        Funktion f3 = Controller.createFunktion("Sovs og tilbehør");
//        Funktion f4 = Controller.createFunktion("Buffetopfyldning");
//
//        Medarbejder m1 = Controller.createMedarbejder("Peter", 6,LocalTime.of(8,0));
//        Medarbejder m2 = Controller.createMedarbejder("Anne", 8,LocalTime.of(8,0));
//        Medarbejder m3 = Controller.createMedarbejder("Marie", 6,LocalTime.of(10,0));
//        Medarbejder m4 = Controller.createMedarbejder("Torben", 8,LocalTime.of(7,0));
//
//        Vagt vagt = Controller.createVagt("Røgede ål til medarbejderne", LocalDateTime.of(2022,6,24,8,0), LocalDateTime.of(2022,6,24,12,30));
//        vagt.createAntal(2, f1);
//        vagt.createAntal(1,f2);
//        vagt.createAntal(1,f3);
//        vagt.createAntal(1,f4);
//
//        m1.addVagt(vagt);
//        m2.addVagt(vagt);
//        m3.addVagt(vagt);
//
//        m1.addFunktion(f2);
//        m1.addFunktion(f3);
//        m1.addFunktion(f4);
//
//        m2.addFunktion(f2);
//        m2.addFunktion(f3);
//        m2.addFunktion(f4);
//
//        m3.addFunktion(f1);
//        m3.addFunktion(f2);
//        m3.addFunktion(f4);
//
//        m4.addFunktion(f1);
//        m4.addFunktion(f3);
//        String filnavn = "C:\\Users\\Peter\\IdeaProjects\\PRO1-Project\\L34-SemesterProve\\src\\controller\\vagtplan.txt";
//        Controller.udskrivVagtplan(vagt, filnavn);
//
//    }
//}
