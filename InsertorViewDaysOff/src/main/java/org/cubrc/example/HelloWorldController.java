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
	@RequestMapping(value ="/addDayOff", method = RequestMethod.POST)
	public String daysOffSubmit(@ModelAttribute("SpringWeb")Date date, Model model) throws IOException{
			String day = date.getDay();
			String month = date.getMonth();
			String year = date.getYear();
			String insertDate = "\"" + year + "-" + month + "-" + day + "\"";
			Query query = new Query();
			query.insertDate(insertDate);
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