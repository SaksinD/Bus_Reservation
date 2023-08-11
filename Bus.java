package CH_busP;

import java.util.ArrayList;
import java.util.List;

class Bus {
    private String busNumber;
    private int capacity;
    private boolean[] seats; // true if seat is available, false if occupied
    private List<Reservation> reservations;

    public Bus(String busNumber, int capacity) {
        this.busNumber = busNumber;
        this.capacity = capacity;
        this.seats = new boolean[capacity];
        this.reservations = new ArrayList<>();
        initializeSeats();
    }

    private void initializeSeats() {
        for (int i = 0; i < capacity; i++) {
            seats[i] = true; // All seats initially available
        }
    }

    public String getBusNumber() {
        return busNumber;
    }

    public int getCapacity() {
        return capacity;
    }
    public boolean isSeatAvailable(int seatNumber) {
        return seatNumber >= 0 && seatNumber < capacity && seats[seatNumber];
    }

    public void reserveSeat(int seatNumber) {
        if (isSeatAvailable(seatNumber)) {
            seats[seatNumber] = false; // Mark seat as occupied
        }
    }

    public void cancelReservation(int seatNumber) {
        if (!isSeatAvailable(seatNumber)) {
            seats[seatNumber] = true; // Mark seat as available
        }
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
        reserveSeat(reservation.getSeatNumber());
    }

    public boolean[] getSeatsAvailability() {
        return seats;
    }
}
