package com.bazlur.shoppingcart.repository;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import com.bazlur.shoppingcart.domain.User.User;
import com.bazlur.shoppingcart.repository.UserRepository;

public class UserRepositoryImpl implements UserRepository{
	private static final Set<User> USERS=new CopyOnWriteArraySet<>();

	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		USERS.add(user);
		
	}

	@Override
	public Optional<User> findByUsername(String username) {
		// TODO Auto-generated method stub
		return USERS.stream().filter(user -> Objects.equals(user.getUsername(),username)).findFirst();
	}

	public Optional<User> findByEmail(String email) {
		// TODO Auto-generated method stub
		return USERS.stream().filter(user -> Objects.equals(user.getEmail(), email)).findFirst();
	}

	
	

}
