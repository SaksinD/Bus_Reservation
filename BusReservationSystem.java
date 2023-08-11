package CH_busP;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Reservation {
    private Bus bus;
    private int seatNumber;
    private String passengerName;

    public Reservation(Bus bus, int seatNumber, String passengerName) {
        this.bus = bus;
        this.seatNumber = seatNumber;
        this.passengerName = passengerName;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public Bus getBus() {
        return bus;
    }
}

public class BusReservationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Bus> buses = new ArrayList<>();

        // Initialize buses and passengers
        buses.add(new Bus("BUS001", 30));
        buses.add(new Bus("BUS002", 40));

        while (true) {
            displayMenu();
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    displayAvailableBuses(buses);
                    break;

                case 2:
                    makeReservation(buses, scanner);
                    break;

                case 3:
                    displayReservations(buses);
                    break;

                case 4:
                    System.out.println("Thank you for using the Bus Reservation System.");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }
    private static void displayMenu() {
        System.out.println("Bus Reservation System Menu");
        System.out.println("1. Display available buses");
        System.out.println("2. Make a reservation");
        System.out.println("3. Display reservations");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void displayAvailableBuses(List<Bus> buses) {
        System.out.println("Available Buses:");
        for (int i = 0; i < buses.size(); i++) {
            Bus bus = buses.get(i);
            System.out.println((i + 1) + ". Bus " + bus.getBusNumber() + " (Capacity: " + bus.getCapacity() +
                    ", Available Seats: " + countAvailableSeats(bus) + ")");
        }
    }

    private static int countAvailableSeats(Bus bus) {
        int count = 0;
        for (boolean seatAvailable : bus.getSeatsAvailability()) {
            if (seatAvailable) {
                count++;
            }
        }
        return count;
    }

    private static void makeReservation(List<Bus> buses, Scanner scanner) {
        System.out.println("Select a bus for reservation:");
        displayAvailableBuses(buses);

        int busChoice = scanner.nextInt();
        if (busChoice >= 1 && busChoice <= buses.size()) {
            Bus selectedBus = buses.get(busChoice - 1);
            System.out.println("Selected bus: " + selectedBus.getBusNumber());
            System.out.println("Available seats: ");
            displayAvailableSeats(selectedBus);
            
            int seatChoice = scanner.nextInt();
            if (seatChoice >= 1 && seatChoice <= selectedBus.getCapacity()) {
                if (selectedBus.isSeatAvailable(seatChoice - 1)) {
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter passenger name: ");
                    String passengerName = scanner.nextLine();
                    Reservation reservation = new Reservation(selectedBus, seatChoice - 1, passengerName);
                    selectedBus.addReservation(reservation);
                    System.out.println("Reservation successfully made for " + passengerName +
                            " on seat " + seatChoice + " in bus " + selectedBus.getBusNumber());
                } else {
                    System.out.println("Selected seat is not available.");
                }
            } else {
                System.out.println("Invalid seat choice.");
            }
        } else {
            System.out.println("Invalid bus choice.");
        }
    }

    private static void displayAvailableSeats(Bus bus) {
        boolean[] seatsAvailable = bus.getSeatsAvailability();
        for (int i = 0; i < seatsAvailable.length; i++) {
            if (seatsAvailable[i]) {
                System.out.print((i + 1) + " ");
            } else {
                System.out.print("X ");
            }
        }
        System.out.println();
    }

    private static void displayReservations(List<Bus> buses) {
        System.out.println("Reservations:");
        for (Bus bus : buses) {
            List<Reservation> reservations = bus.getReservations();
            for (Reservation reservation : reservations) {
                System.out.println("Bus " + bus.getBusNumber() + " - Seat " +
                        (reservation.getSeatNumber() + 1) + ": " + reservation.getPassengerName());
            }
        }
    }
}