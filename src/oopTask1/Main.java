package oopTask1;

public class Main {
    public static void main(String[] args) {
        bankAccount user1=new bankAccount("Nurgun","1",1218);
        bankAccount user2=new bankAccount("Aysel","2",2349);
        bankAccount user3=new bankAccount("Adil","3",9823);

        user1.deposit(2400.0);
        user2.deposit(2800.0);
        user3.deposit(3000.0);
        user1.deposit(-400.0);

        user1.checkBalance(1223);
        user3.withDraw(4500,9823);

        user1.applyInterest();
        user2.printHistory();
        user3.printHistory();

        user2.transferTo(user1,200,2345);
        user2.transferTo(user1,200,2347);
        user2.transferTo(user1,200,2343);

    }
}
