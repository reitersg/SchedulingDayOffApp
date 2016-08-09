package org.cubrc.example;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.io.IOException;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.cubrc.example.DaysOffParser;

@Controller
public class HelloWorldController {

	@RequestMapping(value = "/daysoffperiod1", method = RequestMethod.GET)
	public ModelAndView daysoffperiod1(){return new ModelAndView("daysoffperiod1", "command", new Date());}

	@RequestMapping(value = "/daysoffperiod2", method = RequestMethod.GET)
	public ModelAndView daysoffperiod2(){return new ModelAndView("daysoffperiod2", "command", new Date());}

	@RequestMapping(value = "/daysoff", method = RequestMethod.GET)
	public ModelAndView daysoff() {
		return new ModelAndView("daysoff", "command", new Date());
	}

	@RequestMapping(value = "/deletedaysoff", method = RequestMethod.GET)
	public ModelAndView deletedaysoff() {return new ModelAndView("deletedaysoff", "command", new Date());}

	@RequestMapping(value ="/addDayOff", method = RequestMethod.POST)
	public String daysOffSubmit(@ModelAttribute("SpringWeb")Date date, Model model) throws IOException{
		if (DateCheck.checkHoliday(date) == true) {
			return "dateerror";
		} else {
			String insertDate = "\"" + date.toString() + "\"";
			Query query = new Query();
			if (DateCheck.checkAlreadyExists(date.toString()) == true){
				return "alreadyexists";
			} else {
				String insertTime = "\"" + "(" + date.toString() + ") " + date.getTime() + "\"";
				query.insertDate(insertDate);
				query.insertTime(insertTime);
				query.insertPurpose(date.getPurpose());
				String dates = query.selectDate();
				String purposes = query.selectPurpose();
				String times = query.selectTime();
				StringBuilder sb = DaysOffParser.parseDate(dates);
				StringBuilder purpose = DaysOffParser.parsePurpose(purposes);
				StringBuilder time = DaysOffParser.parseTime(times);
				model.addAttribute("dates", sb.toString());
				model.addAttribute("purposes", purpose.toString());
				model.addAttribute("times", time.toString());
				return "result";
			}
		}
	}
	@RequestMapping(value = "/deleteDayOff", method = RequestMethod.POST)
	public String dayOffDelete(@ModelAttribute("SpringWeb")Date date, Model model) throws IOException{
			Query query = new Query();
			query.deleteDate(date);
			String dates = query.selectDate();
		String purposes = query.selectPurpose();
			StringBuilder sb = DaysOffParser.parseDate(dates);
		StringBuilder purpose = DaysOffParser.parsePurpose(purposes);
			model.addAttribute("dates", sb.toString());
			model.addAttribute("purposes", purpose.toString());
			return "result";

	}
	@RequestMapping(value="/daysOffList")
	public String daysOffList(Model model) throws IOException{
		Query query = new Query();
		String dates = query.selectDate();
		String purposes = query.selectPurpose();
		String times = query.selectTime();
		StringBuilder sb = DaysOffParser.parseDate(dates);
		StringBuilder purpose = DaysOffParser.parsePurpose(purposes);
		StringBuilder time = DaysOffParser.parseTime(times);

		model.addAttribute("dates", sb.toString());
		model.addAttribute("purposes", purpose.toString());
		model.addAttribute("times", time.toString());
		return "result";
	}

}