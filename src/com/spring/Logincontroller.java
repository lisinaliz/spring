package com.spring;


import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.portlet.ModelAndView;

import com.bean.*;
import com.dbconnection.Com;

@Controller
public class Logincontroller {
private String saveDirectory="";


@RequestMapping(value="/HELLO", method = RequestMethod.GET)
public String submit(Model model, @ModelAttribute("loginBean") Loginbean loginBean)

{
	return "HELLO";
}
@RequestMapping(value = "/login",method = RequestMethod.POST)
public String facebook(@Validated @ModelAttribute("logbean") Loginbean logbean ,BindingResult result,ModelMap model) throws SQLException {
    

       Com obj =new Com();
       System.out.println("uname="+logbean.getEma());
       ResultSet x=obj.store(logbean.getEma(), logbean.getWord());
       System.out.println("x="+x);
       if(x.next())
       {
       
        //model.addAttribute("email", logbean.getEma());
       // model.addAttribute("pswd", logbean.getWord());
    	   model.addAttribute("fname", x.getString("firstname"));
       	return "project";
       }
       else
       {
        model.addAttribute("error", "REgistration Failed");
       	return "HELLO";
       }
}
@RequestMapping(value="/savefile",method=RequestMethod.POST)
public ModelAndView upload(@RequestParam CommonsMultipartFile file,HttpSession session)
{
	String filename=file.getOriginalFilename();
	System.out.println(saveDirectory+""+filename);
	try{
		byte barr[]=file.getBytes();
		BufferedOutputStream bout=new BufferedOutputStream(new FileOutputStream(saveDirectory+"/"+filename));
	    bout.write(barr);
	    bout.flush();bout.close();
	}
	catch(Exception e){System.out.println(e);}
	return new ModelAndView("upload-success","filename",saveDirectory+"/"+filename);
	}
	
	
	
}
//System.out.println(loginBean.getEma());

/*if (loginBean != null && loginBean.getEma() != null & loginBean.getWord() != null)
	
{
if (loginBean.getEma().equals("abc@gmail.com") && loginBean.getWord().equals("abc123")) 

{
model.addAttribute("msg", loginBean.getEma());

return "HELLO";
} 

else {
	
model.addAttribute("error", "Invalid Details");
return "project";
}
} 

else {
model.addAttribute("error", "Please enter Details");
return "HELLO";
}*/


