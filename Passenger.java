public class Passenger {
    private String FirstName;
    private String LastName;
    private double Expenses;

    public Passenger(String FirstName, String LastName, double Expenses){
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Expenses = Expenses;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public double getExpenses() {
        return Expenses;
    }

    @Override
    public String toString(){
        return "Name : " + FirstName + " " + LastName +  " | Expenses : " + Expenses;
    }
}