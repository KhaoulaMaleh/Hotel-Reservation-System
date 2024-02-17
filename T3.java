package TASK3;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class T3 {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();

        // Create a Scanner object
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the Hotel Reservation System!");

        // Ask for room category
        System.out.print("Enter desired room category (Standard, Deluxe, Suite): ");
        String category = sc.nextLine();

        // Search for available rooms
        List<Room> availableRooms = hotel.searchAvailableRooms(category);

        // Check if any rooms are available
        if (!availableRooms.isEmpty()) {
            // Display available rooms
            System.out.println("\nAvailable " + category + " rooms:");
            for (int i = 0; i < availableRooms.size(); i++) {
                Room room = availableRooms.get(i);
                System.out.println("Room number: " + room.getRoomNumber());
            }

            // Ask user to choose a room
            System.out.print("\nEnter room number to book: ");
            int roomNumber = sc.nextInt();
            sc.nextLine(); // Consume the newline character

            // Find the chosen room object
            Room roomToBook = null;
            for (Room room : availableRooms) {
                if (room.getRoomNumber() == roomNumber) {
                    roomToBook = room;
                    break;
                }
            }

            // Check if chosen room is valid
            if (roomToBook != null) {
                // Ask for guest information
                System.out.print("Enter guest name: ");
                String guestName = sc.nextLine();

                // Ask for check-in and check-out dates
                LocalDate checkInDate = null;
                LocalDate checkOutDate = null;
                boolean validDates = false;

                while (!validDates) {
                    try {
                        System.out.print("Enter check-in date (YYYY-MM-DD): ");
                        String checkInDateString = sc.nextLine();
                        checkInDate = LocalDate.parse(checkInDateString);

                        System.out.print("Enter check-out date (YYYY-MM-DD): ");
                        String checkOutDateString = sc.nextLine();
                        checkOutDate = LocalDate.parse(checkOutDateString);

                        if (checkOutDate.isAfter(checkInDate)) {
                            validDates = true;
                        } else {
                            System.out.println("Check-out date must be after check-in date.");
                        }
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid date format. Please use YYYY-MM-DD.");
                    }
                }

                // Make reservation
                hotel.makeReservation(roomToBook, guestName, checkInDate, checkOutDate);
                System.out.println("Reservation made successfully!");
            } else {
                System.out.println("Invalid room number.");
            }
        } else {
            System.out.println("No available rooms in the specified category.");
        }

        // View bookings
        List<Booking> bookings = hotel.getBookings();
        for (Booking booking : bookings) {
            System.out.println("Booking ID: " + booking.getBookingId());
            System.out.println("Guest Name: " + booking.getGuestName());
            System.out.println("Room Number: " + booking.getRoom().getRoomNumber());
            System.out.println("Check-in Date: " + booking.getCheckInDate());
            System.out.println("Check-out Date: " + booking.getCheckOutDate());
        }
     sc.close();
    }
}
