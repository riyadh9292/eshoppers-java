package com.bazlur.shoppingcart.service;

import com.bazlur.shoppingcart.domain.User.User;
import com.bazlur.shoppingcart.dto.LoginDTO;
import com.bazlur.shoppingcart.dto.UserDTO;

public interface UserService {

	public void saveUser(UserDTO userDTO);
	boolean isNotUniqueUsername(UserDTO user);
	boolean isNotUniqueEmail(UserDTO user);
	User verifyUser(LoginDTO loginDTO);
}
