package oopTask3;

import java.util.ArrayList;

public class TeamMember {
    private String memberName;
    private String memberId;
    private ArrayList<String> taskList;
    private int maxCapacity;
    private int accessPin;
    private boolean isOnLeave;
    private ArrayList<String> activityLog;
    private int completedTaskCount;
    private int wrongPinCount;

    public TeamMember(String memberName,String memberId,int accessPin){
        this.memberName=memberName;
        this.memberId=memberId;
        this.taskList=new ArrayList<>();
        this.maxCapacity=5;
        this.isOnLeave=false;
        this.wrongPinCount = 0;
        this.activityLog = new ArrayList<>();
        this.completedTaskCount = 0;
        if(accessPin<1000 || accessPin>9999){
            System.out.println("Warning! The accesspin must be 4 digits long.");
        } else {
            this.accessPin=accessPin;
        }
    }

    public void assignTask(String taskName){
        if (isOnLeave) {
            System.out.println(memberName + " is on leave! Cannot assign task.");
            activityLog.add(memberName + " is on leave! Cannot assign task.");
        } else {
            if(taskList.size()>=maxCapacity){
                System.out.println("Capacity is full.");
            } else{
                taskList.add(taskName);
                System.out.println(taskName + " has been assigned to " + memberName + ".");
                activityLog.add(taskName + " has been assigned to " + memberName + ".");
            }
        }

    }
    public boolean completeTask(String taskName,int enteredPin) {
        if (isOnLeave) {
            System.out.println(memberName + " is on leave! Cannot assign task.");
            activityLog.add(memberName + " is on leave! Cannot assign task.");
            return false;
        }else {
            if(enteredPin!=this.accessPin) {
                wrongPinCount++;
                if (wrongPinCount < 3) {
                    System.out.println("Incorrect accesspin!");
                    return false;
                } else {
                    setOnLeave();
                }
            }
       }
       boolean removed=taskList.remove(taskName);
       if(!removed){
           System.out.println("Task not found!");
           return false;
       } else {
           System.out.println("Completed task " + taskName);
           activityLog.add("Completed task " + taskName);
           completedTaskCount++;
           TaskSuccessCount();
           return true;
       }
    }
    public void checkWorkload(int enteredPin){
        if(this.accessPin==enteredPin){
            System.out.println("Task count: " + taskList.size());
            System.out.println("Tasks:");
            for (String task: taskList){
                System.out.println("- "+ task);
            }
        } else {
            System.out.println("Incorrect accesspin!");
        }
    }
    public void reassignTask(TeamMember receiver,String taskName,int enteredPin){
        if(receiver.taskList.size()>=maxCapacity){
            System.out.println("The receiver's capacity is full.");
        } else {
            if(enteredPin!=this.accessPin){
                System.out.println("Incorrect accesspin.");
            } else {
                receiver.assignTask(taskName);
                taskList.remove(taskName);
                System.out.println(taskName + " has been reassigned from " + memberName + " to " + receiver.memberName + ".");
                activityLog.add(taskName + " has been reassigned from " + memberName + " to " + receiver.memberName + ".");
            }
        }
    }
    public void setOnLeave() {
        isOnLeave = true;
        activityLog.add("On Leave");
    }
    public void printActivityLog() {
        System.out.println("---" + memberName + " activity history ---");
        for (String log : activityLog) {
            System.out.println("- " +log);
        }
    }
    public void earnBadge() {
        maxCapacity += 2;
    }

    public void TaskSuccessCount() {
        completedTaskCount++;
        if (completedTaskCount == 5) {
            earnBadge();
            completedTaskCount = 0;
        }
    }

}
