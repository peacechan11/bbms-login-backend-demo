package com.bbms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.bbms.model.ConfirmationToken;
import com.bbms.model.Message;
import com.bbms.model.User;
import com.bbms.repository.ConfirmationTokenRepository;
import com.bbms.repository.UserRepository;
import com.bbms.service.EmailService;

@RestController
@CrossOrigin
public class AuthenticationController {
		
	    @Autowired
	    private UserRepository userRepository;
	    @Autowired
	    private ConfirmationTokenRepository confirmationTokenRepository;

	    @Autowired
	    private EmailService emailService;

	    @CrossOrigin(origins = "http://localhost:4200")
	    
	    
	    

	    
	    @PostMapping(value="/register", produces = "application/json")
	    public ResponseEntity<?> addUser(@RequestBody User user) {

	    	User existingUser = userRepository.findByEmailIgnoreCase(user.getEmail());
	        if(existingUser != null)
	        {
	            Message msg=new Message("email already exists");
	            return ResponseEntity.ok(msg);
	        }
	        else
	        {
	            userRepository.save(user);

	            ConfirmationToken confirmationToken = new ConfirmationToken(user);

	            confirmationTokenRepository.save(confirmationToken);

	            SimpleMailMessage mailMessage = new SimpleMailMessage();
	            mailMessage.setTo(user.getEmail());
	            mailMessage.setSubject("Complete Registration!");
	            mailMessage.setFrom("team.zhask.dev@gmail.com");
	            mailMessage.setText("To confirm your account, please click here : "
	            +"http://localhost:8080/confirm-account?token="+confirmationToken.getConfirmationToken());

	            emailService.sendEmail(mailMessage);

	            Message msg=new Message("Successfully registered");
	            return ResponseEntity.ok(msg);
	        }

	       
	    }
//	    
//
//	    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
//	    public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token")String confirmationToken)
//	    {
//	        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
//
//	        if(token != null)
//	        {
//	        	UserEntity user = userRepository.findByEmailIdIgnoreCase(token.getUserEntity().getEmailId());
//	            user.setEnabled(true);
//	            userRepository.save(user);
//	            modelAndView.setViewName("accountVerified");
//	        }
//	        else
//	        {
//	            modelAndView.addObject("message","The link is invalid or broken!");
//	            modelAndView.setViewName("error");
//	        }
//
//	        return modelAndView;
//	    }
	}