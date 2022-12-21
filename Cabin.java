public class Cabin {
    String initialize;

    private Passenger[] passengers = new Passenger[3];

    public Passenger[] getPassengers() {
        return passengers;
    }

    public Cabin(String initialize) {
        this.initialize = initialize;
    }

    public void addPassengers(Passenger passenger) {
        for (int i = 0; i < passengers.length; i++) {
            if (passengers[i] == null) {
                passengers[i] = passenger;
                break;
            }
        }
    }


    //For task 3
    public boolean isFull() {
        return passengers[0] != null && passengers[1] != null && passengers[2] != null;
    }

    public boolean isPassengerFull() {
        return passengers[0] != null && passengers[1] == null && passengers[2] == null;
    }
}

