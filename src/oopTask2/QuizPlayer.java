package oopTask2;

import java.util.ArrayList;

public class QuizPlayer {
    private String playerName;
    private String quizId;
    private int score;
    private int accessCode;
    private boolean isDisqualified;
    private ArrayList<String> quizLog;
    private int wrongAttemps;
    private int correctStreak=0;

    public QuizPlayer(String playerName,String quizId,int accessCode) {
        this.playerName=playerName;
        this.quizId=quizId;
        this.score=0;
        this.isDisqualified=false;
        this.quizLog=new ArrayList<>();
        if(accessCode<1000 || accessCode>9999) {
            System.out.println("Warning! The accescode must be 4 digits long.");
        } else {
            this.accessCode=accessCode;
        }
    }

    public void submitAnswer(int points){
        if(points<0){
            System.out.println("Negative points are not accepted.");
            quizLog.add(playerName +"entered negative points.");
            correctStreak=0;
        } else {
            score += points;
            System.out.println("Score: "+score + "| Streak: " +correctStreak);
            quizLog.add("Score: "+score +", streak: "+correctStreak);

            if(correctStreak>=5){
                applyStreakBonus();
            }
        }
    }

    public boolean useHint(int cost,int enteredCode){
        if(enteredCode!=this.accessCode){
            wrongAttemps++;
            if(wrongAttemps<3) {
                System.out.println("Incorrect accescode!");
                quizLog.add("Incorrect accesscode.");
            } else {
                disqualifyPlayer();
                quizLog.add(playerName +" was disqualified.");
            }
        } else{
            if (cost>score){
                System.out.println("Insufficient score.");
                quizLog.add("Insufficient score.");
            } else {
                score-=cost;
                System.out.println(playerName+ "used a hint.New score: "+score);
                quizLog.add(playerName+ "used a hint.New score: "+score);
            }
        }
        return true;
    }

    public void checkScore(int enteredCode){
        if(enteredCode!=this.accessCode) {
            System.out.println("Incorrect accescode.");
            quizLog.add("Incorrect accesscode.");
        } else {
            System.out.println("Score: "+score);
            quizLog.add("Score: "+score);
        }
    }
    public void shareBonusPoints(QuizPlayer receiver,int amount,int enteredCode){

        if(enteredCode!=this.accessCode){
            wrongAttemps++;
            if(wrongAttemps>=3){
                disqualifyPlayer();
                quizLog.add(playerName +" was disqualified.");
            } else {
                System.out.println("Incorrect accescode.");
                quizLog.add("Incorrect accesscode.");
            }
        } else {
            if(this.score<amount){
                System.out.println("Insufficient score.");
                quizLog.add("Insufficient score.");
            } else {
                receiver.score+=amount;
                System.out.println(amount + " points were transfered between "+ playerName + "->"+ receiver.playerName);
                quizLog.add(amount + " points were transfered between "+ playerName + "->"+ receiver.playerName);
            }
        }
    }
    public void disqualifyPlayer(){
        this.isDisqualified=true;
        System.out.println(playerName+ " was disqualified.");
        quizLog.add(playerName +" was disqualified.");
    }
    public void printLog(){

            System.out.println("--- " + playerName + " History ---");
            for (String item:quizLog) {
                System.out.println("- "+item);
        }
    }
    public void applyStreakBonus(){
        score +=10;
        System.out.println(playerName + "earned 10 bonus points. New score: "+ score);
        quizLog.add(playerName +": +10 bonus points.New score: "+ score);
        correctStreak=0;
    }
}
