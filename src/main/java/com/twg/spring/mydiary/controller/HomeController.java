package com.twg.spring.mydiary.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;

import java.util.List;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.twg.spring.mydiary.business.EntryBusinessInterface;
import com.twg.spring.mydiary.business.UserBusinessInterface;
import com.twg.spring.mydiary.entities.Entry;
import com.twg.spring.mydiary.entities.User;


@Controller
public class HomeController {

    @Autowired
    private UserBusinessInterface userBusinessInterface;

    @Autowired
    private EntryBusinessInterface entryBusinessInterface;
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @RequestMapping("/")
    public ModelAndView showLandingPage() {
        return new ModelAndView("index"); // Matches the index.jsp file name
    }
    
    @RequestMapping("/login")
    public ModelAndView loginPage() {
        ModelAndView model = new ModelAndView("login");
        model.addObject("user", new User()); 
        return model; 
    }

    @RequestMapping("/register")
    public ModelAndView registerPage() {
        ModelAndView model = new ModelAndView("register");
        model.addObject("user", new User());
        return model; 
    }
    
    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    public ModelAndView processRegistration(@ModelAttribute("user") User user) {
        User existingUser = userBusinessInterface.findByUsername(user.getUsername());
        
        if (existingUser != null) {
            ModelAndView model = new ModelAndView("register");
            model.addObject("error", "Username already exists."); 
            return model; 
        }

        userBusinessInterface.save(user);
        return new ModelAndView("redirect:/registersuccess"); 
    }

    @RequestMapping("/registersuccess")
    public ModelAndView registerSuccessPage() {
        return new ModelAndView("registersuccess"); 
    }

    @RequestMapping(value = "/processLogin", method = RequestMethod.POST)
    public ModelAndView authenticate(@ModelAttribute("user") User user, HttpSession session) {
        User loggedInUser = userBusinessInterface.findByUsername(user.getUsername());
        
        if(loggedInUser != null && loggedInUser.getPassword().equals(user.getPassword())) { 
            session.setAttribute("user", loggedInUser); 
            return new ModelAndView("redirect:/home"); 
        } else {
            // Replaced generic login return with an explicit error message
            ModelAndView model = new ModelAndView("login");
            model.addObject("error", "Invalid username or password.");
            return model;
        }
    }

    @RequestMapping("/signout")
    public ModelAndView signout(HttpSession session) {
        session.invalidate(); 
        ModelAndView model = new ModelAndView("login");
        model.addObject("message", "You have successfully signed out.");
        return model;
    }

    @RequestMapping("/addentry")
    public ModelAndView addEntryPage(HttpSession session) {
        if (session.getAttribute("user") == null) {
            ModelAndView model = new ModelAndView("login");
            model.addObject("error", "Session expired. Please log in to add an entry.");
            return model;
        }
        return new ModelAndView("addentry");
    }

    @RequestMapping(value = "/saveEntry", method = RequestMethod.POST)
    public ModelAndView saveEntry(@ModelAttribute("entry") Entry entry, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("user");
        
        if (loggedInUser == null) {
            ModelAndView model = new ModelAndView("login");
            model.addObject("error", "Session expired. Please log in to save your entry.");
            return model;
        }
        
        entry.setUserid(loggedInUser.getId());
        entryBusinessInterface.save(entry);
        
        return new ModelAndView("redirect:/home");
    }
    
    @RequestMapping("/viewentry")
    public ModelAndView viewentry(@RequestParam("id") int id, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("user");
        if (loggedInUser == null) {
            ModelAndView model = new ModelAndView("login");
            model.addObject("error", "Session expired. Please log in to view entries.");
            return model;
        }
        
        Entry entry = entryBusinessInterface.findById(id);
        
        // Handles scenario where the entry ID does not exist in the database
        if (entry == null) {
            ModelAndView model = new ModelAndView("userhomepage");
            model.addObject("error", "The requested diary entry could not be found.");
            model.addObject("entrieslist", entryBusinessInterface.findByUserId(loggedInUser.getId()));
            return model;
        }
        
        ModelAndView model = new ModelAndView("displayentry");
        model.addObject("entry", entry);
        return model;
    }
    
 // 1. Handles the initial click/navigation to load the form (GET request)
    @RequestMapping(value = "/updateentry", method = RequestMethod.GET)
    public ModelAndView showUpdateForm(@RequestParam("id") int id, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("user");
        
        if (loggedInUser == null) {
            ModelAndView model = new ModelAndView("login");
            model.addObject("error", "Session expired. Please log in.");
            return model;
        }
        
        // Fetch the existing entry so the JSP can display its details
        Entry entry = entryBusinessInterface.findById(id);
        
        ModelAndView model = new ModelAndView("updateentry"); // Replace with the actual name of your JSP file
        model.addObject("entry", entry);
        return model;
    }

    // 2. Handles the form submission when you click "UPDATE ENTRY" (POST request)
    @RequestMapping(value = "/updateentry", method = RequestMethod.POST)
    public ModelAndView updateEntry(@ModelAttribute("entry") Entry entry, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("user");
        
        if (loggedInUser == null) {
            ModelAndView model = new ModelAndView("login");
            model.addObject("error", "Session expired. Please log in to update your entry.");
            return model;
        }
        
        // Re-link the entry to the currently logged-in user
        entry.setUserid(loggedInUser.getId());
        
        // Execute the database update via the business layer
        entryBusinessInterface.update(entry);
        
        // Refresh the dashboard to show the new data
        return new ModelAndView("redirect:/home");
    }
    
    @RequestMapping("/deleteentry")
    public ModelAndView deleteentry(@RequestParam("id") int id, HttpSession session) {
        // 1. Verify the user is logged in
        User loggedInUser = (User) session.getAttribute("user");
        if (loggedInUser == null) {
            ModelAndView model = new ModelAndView("login");
            model.addObject("error", "Session expired. Please log in.");
            return model;
        }
        
        // 2. Find the exact diary entry using the ID passed from the URL
        Entry entry = entryBusinessInterface.findById(id);
        
        // 3. Delete the entry (with a security check to ensure it belongs to the logged-in user)
        if (entry != null && entry.getUserid() == loggedInUser.getId()) {
            entryBusinessInterface.delete(entry); 
        }
        
        // 4. Redirect back to the dashboard to show the updated list
        return new ModelAndView("redirect:/home");
    }
	
	
	
    
    
    
    
    
    
    
    @RequestMapping("/home")
    public ModelAndView homepage(HttpSession session) {
        User loggedInUser = (User) session.getAttribute("user");
        
        // Replaced redirect with direct login view + message
        if (loggedInUser == null) {
            ModelAndView model = new ModelAndView("login");
            model.addObject("error", "Please log in to view your dashboard.");
            return model;
        }
        
        ModelAndView model = new ModelAndView("userhomepage");
        List<Entry> entries = entryBusinessInterface.findByUserId(loggedInUser.getId());
        
        // Checks for null or empty list and passes the specific message
        if (entries == null || entries.isEmpty()) {
            model.addObject("message", "User not added any Diary entries till now");
        }
        
        model.addObject("entrieslist", entries);
        return model; 
    }
    

	
    
}