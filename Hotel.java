package TASK3;
import java.time.LocalDate;

import java.util.*;

class Hotel {
    private List<Room> rooms;
    private List<Booking> bookings;
    private int bookingIdCounter;

    public Hotel() {
        rooms = new ArrayList<>();
        bookings = new ArrayList<>();
        bookingIdCounter = 1;
        initializeRooms();
    }

    private void initializeRooms() {
        for (int i = 1; i <= 10; i++) {
            rooms.add(new Room(i, "Standard"));
        }
        for (int i = 11; i <= 20; i++) {
            rooms.add(new Room(i, "Deluxe"));
        }
        for (int i = 21; i <= 30; i++) {
            rooms.add(new Room(i, "Suite"));
        }
    }

    public List<Room> searchAvailableRooms(String category) {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.isAvailable() && room.getCategory().equalsIgnoreCase(category)) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public void makeReservation(Room room, String guestName, LocalDate checkInDate, LocalDate checkOutDate) {
        Booking booking = new Booking(bookingIdCounter++, room, guestName, checkInDate, checkOutDate);
        bookings.add(booking);
        room.bookRoom();
    }

    public List<Booking> getBookings() {
        return bookings;
    }
}

class Room {
    private int roomNumber;
    private String category;
    private boolean isAvailable;

    public Room(int roomNumber, String category) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.isAvailable = true;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getCategory() {
        return category;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void bookRoom() {
        isAvailable = false;
    }

    public void releaseRoom() {
        isAvailable = true;
    }
}

class Booking {
    private int bookingId;
    private Room room;
    private String guestName;
    public LocalDate checkInDate;
    public LocalDate checkOutDate;

    public Booking(int bookingId, Room room, String guestName, LocalDate checkInDate, LocalDate checkOutDate) {
        this.bookingId = bookingId;
        this.room = room;
        this.guestName = guestName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public int getBookingId() {
        return bookingId;
    }

    public Room getRoom() {
        return room;
    }

    public String getGuestName() {
        return guestName;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }
}