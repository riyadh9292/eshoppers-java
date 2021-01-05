package com.bazlur.shoppingcart.repository;

import java.util.Optional;

import com.bazlur.shoppingcart.domain.User.User;

public interface UserRepository {
	void save(User user);
	Optional<User> findByUsername(String username);
	Optional<User> findByEmail(String email);
	//Optional<User> getPassword(String password);
	

}
