package com.curahealthyme.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.curahealthyme.model.MedicalStaff;
import com.curahealthyme.model.Patient;
import com.curahealthyme.model.Patient_OHIP;
import com.curahealthyme.model.UserAccess;
import com.curahealthyme.model.User_Logon;
import com.curahealthyme.repo.MedicalStaffRepository;
import com.curahealthyme.repo.PatientRepository;
import com.curahealthyme.repo.Patient_OHIPRepository;
import com.curahealthyme.repo.UserAccessRepository;
import com.curahealthyme.repo.User_LogonRepository;

@Controller
public class UserController {
	@Autowired
	private PatientRepository patientRepo;

	@Autowired
	private User_LogonRepository userlogonRepo;

	@Autowired
	private UserAccessRepository userAccessRepo;
	
	@Autowired
	private MedicalStaffRepository medicalStaffRepo;
	@Autowired
	private Patient_OHIPRepository ohipRepo;

	@RequestMapping(value = "/success")
	public String success() {
		return "success";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registeruser(Patient patient, @RequestParam("username") String username,
			@RequestParam("useraccessid") String accessId, @RequestParam("password") String pwd,
			@RequestParam("confirmpwd") String confirmpwd, Model model, @RequestParam("cardNumber") String cardNumber) {
		User_Logon existuser =userlogonRepo.isUserNameExist(username);
		if (existuser != null) {
			model.addAttribute("errorMsg", "Invalid Username!");
			return "register";
		} else if (!pwd.equals(confirmpwd)) {
			model.addAttribute("errorMsg", "Your password and confirmation password do not match.");
			return "register";
		} else {
			UserAccess userrole = userAccessRepo.findById(Long.parseLong(accessId));				
			User_Logon userlogon = new User_Logon();
			userlogon.Username = username;
			userlogon.Password = pwd;
			userlogon.setUserAccessId(Long.parseLong(accessId));
			userlogonRepo.save(userlogon);
			patient.setLoginId(userlogon.getId());
			if (userrole.getUserRole().equals("Patient")) {
				patientRepo.save(patient);
				Patient_OHIP ohip = new Patient_OHIP();
				ohip.setPatientId(patient.getPatientId());
				ohip.setOhip(cardNumber);
				ohipRepo.save(ohip);
			} else {
				MedicalStaff medicalStaff = new MedicalStaff();
				medicalStaff.setEmployeeName(patient.getName());
				medicalStaff.setDob(patient.getDob());
				medicalStaff.setGender(patient.getGender());
				medicalStaff.setStreet(patient.getStreet());
				medicalStaff.setCity(patient.getCity());
				medicalStaff.setProvince(patient.getProvince());
				medicalStaff.setCountry(patient.getCountry());
				medicalStaff.setPostal(patient.getPostal());
				medicalStaff.setPhone(patient.getPhone());
				medicalStaff.setEmail(patient.getEmail());
				medicalStaff.setLoginId(patient.getLoginId());
				medicalStaffRepo.save(medicalStaff);
			}

			return "redirect:/success";
		}

	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginuser(@RequestParam("username") String username, @RequestParam("password") String pwd,
			Model model, HttpSession session) {
		User_Logon userlogon = userlogonRepo.findByUsername(username, pwd);
		if (userlogon != null) {
			long accessid = userlogonRepo.findUserAccessId(username);
			String userrole = userAccessRepo.findById(accessid).UserRole;
			session.setAttribute("USERNAME", username.toUpperCase());
			session.setAttribute("userrole", userrole);
			session.setAttribute("LoginId", userlogon.getId());
			
			return "redirect:/";
		} else {
			model.addAttribute("errorMsg", "Invalid username and/or Password!");
			return "login";
		}

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/login";
	}

}
