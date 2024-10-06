package com.tech.hotel.utils;

import java.security.SecureRandom;
import java.util.List;
import java.util.stream.Collectors;

import com.tech.hotel.dto.BookingDTO;
import com.tech.hotel.dto.RoomDTO;
import com.tech.hotel.dto.UserDTO;
import com.tech.hotel.entity.Booking;
import com.tech.hotel.entity.Room;
import com.tech.hotel.entity.User;

public class Utils {

    private static final String ALPHANUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom secureRandom = new SecureRandom();

    public static String generateRandomConfirmationCode(int length) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(ALPHANUMERIC_STRING.length());
            char randomChar = ALPHANUMERIC_STRING.charAt(randomIndex);
            stringBuilder.append(randomChar);
        }
        return stringBuilder.toString();
    }

    public static UserDTO mapUserEntityToUserDTO(User user) {
        if (user == null) {
            return null;
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setRole(user.getRole());
        return userDTO;
    }

    public static RoomDTO mapRoomEntityToRoomDTO(Room room) {
        if (room == null) {
            return null;
        }

        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setId(room.getId());
        roomDTO.setRoomType(room.getRoomType());
        roomDTO.setRoomPrice(room.getRoomPrice());
        roomDTO.setRoomPhotoUrl(room.getRoomPhotoUrl());
        roomDTO.setRoomDescription(room.getRoomDescription());
        return roomDTO;
    }

    public static BookingDTO mapBookingEntityToBookingDTO(Booking booking) {
        if (booking == null) {
            return null;
        }

        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setId(booking.getId());
        bookingDTO.setCheckInDate(booking.getCheckInDate());
        bookingDTO.setCheckOutDate(booking.getCheckOutDate());
        bookingDTO.setNumOfAdults(booking.getNumOfAdults());
        bookingDTO.setNumOfChildren(booking.getNumOfChildren());
        bookingDTO.setTotalNumOfGuest(booking.getTotalNumOfGuest());
        bookingDTO.setBookingConfirmationCode(booking.getBookingConfirmationCode());
        return bookingDTO;
    }

    public static RoomDTO mapRoomEntityToRoomDTOPlusBookings(Room room) {
        RoomDTO roomDTO = mapRoomEntityToRoomDTO(room);

        if (room != null && room.getBookings() != null) {
            roomDTO.setBookings(
                room.getBookings()
                    .stream()
                    .map(Utils::mapBookingEntityToBookingDTO)
                    .collect(Collectors.toList())
            );
        }

        return roomDTO;
    }

    public static BookingDTO mapBookingEntityToBookingDTOPlusBookedRooms(Booking booking, boolean mapUser) {
        BookingDTO bookingDTO = mapBookingEntityToBookingDTO(booking);

        if (booking != null) {
            if (mapUser) {
                bookingDTO.setUser(mapUserEntityToUserDTO(booking.getUser()));
            }

            if (booking.getRoom() != null) {
                bookingDTO.setRoom(mapRoomEntityToRoomDTO(booking.getRoom()));
            }
        }

        return bookingDTO;
    }

    public static UserDTO mapUserEntityToUserDTOPlusUserBookingsAndRoom(User user) {
        UserDTO userDTO = mapUserEntityToUserDTO(user);

        if (user != null && user.getBookings() != null && !user.getBookings().isEmpty()) {
            userDTO.setBookings(
                user.getBookings()
                    .stream()
                    .map(booking -> mapBookingEntityToBookingDTOPlusBookedRooms(booking, false))
                    .collect(Collectors.toList())
            );
        }

        return userDTO;
    }

    public static List<UserDTO> mapUserListEntityToUserListDTO(List<User> userList) {
        if (userList == null) {
            return null;
        }

        return userList.stream()
                       .map(Utils::mapUserEntityToUserDTO)
                       .collect(Collectors.toList());
    }

    public static List<RoomDTO> mapRoomListEntityToRoomListDTO(List<Room> roomList) {
        if (roomList == null) {
            return null;
        }

        return roomList.stream()
                       .map(Utils::mapRoomEntityToRoomDTO)
                       .collect(Collectors.toList());
    }

    public static List<BookingDTO> mapBookingListEntityToBookingListDTO(List<Booking> bookingList) {
        if (bookingList == null) {
            return null;
        }

        return bookingList.stream()
                          .map(Utils::mapBookingEntityToBookingDTO)
                          .collect(Collectors.toList());
    }
}
