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

	@RequestMapping(value = "/daysoff", method = RequestMethod.GET)
	public ModelAndView daysoff() {
		return new ModelAndView("daysoff", "command", new Date());
	}
	@RequestMapping(value = "/deletedaysoff", method = RequestMethod.GET)
	public ModelAndView deletedaysoff() {return new ModelAndView("deletedaysoff", "command", new Date());}
	@RequestMapping(value ="/addDayOff", method = RequestMethod.POST)
	public String daysOffSubmit(@ModelAttribute("SpringWeb")Date date, Model model) throws IOException{
			String insertDate = "\"" + date.toString() + "\"";
			Query query = new Query();
			query.insertDate(insertDate);
			String dates = query.selectDate();
			StringBuilder sb = DaysOffParser.parseDate(dates);
			model.addAttribute("dates", sb.toString());
			return "result";
	}
	@RequestMapping(value = "/deleteDayOff", method = RequestMethod.POST)
	public String dayOffDelete(@ModelAttribute("SpringWeb")Date date, Model model) throws IOException{
		String deleteDate = "\"" + date.toString() + "\"";
		Query query = new Query();
		query.deleteDate(deleteDate);
		String dates = query.selectDate();
		StringBuilder sb = DaysOffParser.parseDate(dates);
		model.addAttribute("dates", sb.toString());
		return "result";
	}
	@RequestMapping(value="/daysOffList")
	public String daysOffList(Model model) throws IOException{
		Query query = new Query();
		String dates = query.selectDate();
		StringBuilder sb = DaysOffParser.parseDate(dates);
		model.addAttribute("dates", sb.toString());
		return "result";
	}
}