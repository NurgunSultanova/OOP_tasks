package oopTask2;

public class Main {
    public static void main(String[] args){
        QuizPlayer p1=new QuizPlayer("Nurgun","88NN",6654);
        QuizPlayer p2=new QuizPlayer("Ali","88uN",7325);
        QuizPlayer p3=new QuizPlayer("Aysel","85gn",7865);

        p1.submitAnswer(700);
        p2.submitAnswer(780);
        p3.submitAnswer(900);

        p2.shareBonusPoints(p1,300,7325);
        p3.checkScore(9956);
        p2.useHint(960,7325);

        p2.printLog();
        p3.printLog();
    }
}
