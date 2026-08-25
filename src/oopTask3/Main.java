package oopTask3;

public class Main {
    public static void main(String[] args) {
        TeamMember M1 = new TeamMember("Aysel", "1", 1991);
        TeamMember M2 = new TeamMember("Qalib", "3", 2007);
        TeamMember M3 = new TeamMember("Zehra", "2", 4587);

        M1.assignTask("Design UI");
        M2.assignTask("Fix Bug");
        M3.assignTask("Review Code");

        M2.checkWorkload(2008);

        M1.completeTask("Design UI",1991);

        M3.reassignTask(M2,"Review Code",4587);

        M1.printActivityLog();
        M2.printActivityLog();
        M3.printActivityLog();
    }
}
