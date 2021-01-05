package com.bazlur.shoppingcart.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import com.bazlur.shoppingcart.domain.User.User;
import com.bazlur.shoppingcart.dto.LoginDTO;
import com.bazlur.shoppingcart.dto.UserDTO;
import com.bazlur.shoppingcart.exceptions.UserNotFoundException;
import com.bazlur.shoppingcart.repository.UserRepository;


public class UserServiceImpl implements UserService {
	public UserRepository userRepository;
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository=userRepository;
	}

	@Override
	public void saveUser(UserDTO userDTO) {
		// TODO Auto-generated method stub
		String encrypted=encryptPassword(userDTO.getPassword());
		User user=new User();
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setEmail(userDTO.getEmail());
		user.setPassword(encrypted);
		user.setUsername(userDTO.getUsername());
		userRepository.save(user);
		
	}
	private String encryptPassword(String password) {
		try {
			var digest=MessageDigest.getInstance("SHA-256");
			var bytes=digest.digest(password.getBytes(StandardCharsets.UTF_8));
			return bytesToHex(bytes);
		}catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Unable to encrypt password ",e);
			
		}
	}
	private String bytesToHex(byte[] hash) {
		StringBuilder hexString=new StringBuilder();
		for(byte b:hash) {
			String hex=Integer.toHexString(0xff & b);
			if(hex.length()==1) {
				hexString.append('0');
			}hexString.append(hex);
		}
		return hexString.toString();
	}

	@Override
	public boolean isNotUniqueUsername(UserDTO user) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(user.getUsername()).isPresent();
	}

	@Override
	public boolean isNotUniqueEmail(UserDTO user) {
		// TODO Auto-generated method stub
		return userRepository.findByEmail(user.getEmail()).isPresent();
	}

	@Override
	public User verifyUser(LoginDTO loginDTO) {
		// TODO Auto-generated method stub
		var user=userRepository.findByUsername(loginDTO.getUsername())
								.orElseThrow(() -> new UserNotFoundException("User not found by "+loginDTO.getUsername()));
		var encrypted=encryptPassword(loginDTO.getPassword());
		if(user.getPassword().equals(encrypted)) {
			return user;
		}
		else {
			throw new UserNotFoundException("incorrect username password");
		}
		
	}

}
