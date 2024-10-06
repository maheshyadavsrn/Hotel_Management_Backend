package com.tech.hotel.service.interfaces;

import com.tech.hotel.dto.LoginRequest;
import com.tech.hotel.dto.Response;
import com.tech.hotel.entity.User;

public interface IUserService {
    Response register(User user);

    Response login(LoginRequest loginRequest);

    Response getAllUsers();

    Response getUserBookingHistory(String userId);

    Response deleteUser(String userId);

    Response getUserById(String userId);

    Response getMyInfo(String email);
}
