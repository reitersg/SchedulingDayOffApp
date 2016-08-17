package org.cubrc.example;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.io.IOException;

import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.cubrc.example.DaysOffParser;

import javax.validation.Valid;


@Controller
public class HelloWorldController {
	private Query query;

	@RequestMapping(value = "/addTimePeriod1", method = RequestMethod.GET)
	public ModelAndView daysoffperiod1(){
		Date date = new Date();
		return new ModelAndView("daysoffperiod1", "Date", date);
	}
	@RequestMapping(value = "/vacation", method = RequestMethod.GET)
	public ModelAndView vacation(){
		return new ModelAndView("vacation", "Date", new Date());
	}
	@RequestMapping(value="/deletevacation", method = RequestMethod.GET)
	public ModelAndView deletevacation(){
		return new ModelAndView("deletevacation", "Date", new Date());
	}

	@RequestMapping(value = "/daysoff", method = RequestMethod.GET)
	public ModelAndView daysoff() {
		Date date = new Date();
		return new ModelAndView("daysoff", "Date", date);
	}

	@RequestMapping(value = "/deletedaysoff", method = RequestMethod.GET)
	public ModelAndView deletedaysoff() {return new ModelAndView("deletedaysoff", "Date", new Date());}

	@RequestMapping(value = "/deletedaysoffperiod", method = RequestMethod.GET)
	public ModelAndView deletedaysoffperiod(){return new ModelAndView("deletedaysoffperiod", "Date", new Date());}
	@RequestMapping(value ="/addDayOff", method = RequestMethod.POST)
	public String daysOffSubmit(@ModelAttribute("Date") Date date, Model model) throws IOException{

		if (DateCheck.checkDayOffEmpty(date)){
			return "daysoff";
		} else if (DateCheck.checkHoliday(date) == true) {
			return "dateerror";
		}
		else {
				if (DateCheck.checkAlreadyExists(date.toString()) == true){
					return "alreadyexists";
				} else {
					String insertDate = "\"" + date.toString() + "\"";
					Query query = new Query();
					query.insertDate(insertDate);
					query.insertPurpose(date.getPurpose());
					String dates = query.selectDate();
					String purposes = query.selectPurpose();
					StringBuilder sb = DaysOffParser.parseDate(dates);
					StringBuilder purpose = DaysOffParser.parsePurpose(purposes);
					model.addAttribute("dates", sb.toString());
					model.addAttribute("purposes", purpose.toString());
					return "result";
				}

		}
	}
	@RequestMapping(value = "/addTimePeriod2", method = RequestMethod.POST)
	public String timePeriodSubmit(@ModelAttribute("Date") Date date, Model model) throws IOException{
		if (DateCheck.checkTimePeriodEmpty(date)){
			return "daysoffperiod1";
		}
		else if (DateCheck.checkHoliday(date) == true) {
			return "dateerror";
		}
		else {
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
	@RequestMapping(value ="/addVacation", method = RequestMethod.POST)
	public String vacationSubmit(@ModelAttribute("Date") Date date, Model model) throws IOException {
		if (DateCheck.checkVacationEmpty(date)) {
			return "vacation";
		} else if (DateCheck.checkSameDate(date) == true) {
			return "vacationerror";
		} else {
			if (DateCheck.checkAlreadyExists(date.toString()) == true) {
				return "alreadyexists";
			} else {
				Query query = new Query();
				query.insertVacation(date);
				String vacations = query.selectVacation();
				StringBuilder sb = DaysOffParser.parseVacation(vacations);
				model.addAttribute("vacations", sb.toString());
				return "result";
			}
		}
	}
	@RequestMapping(value = "/deleteDayOff", method = RequestMethod.POST)
	public String dayOffDelete(@ModelAttribute("Date") Date date, Model model) throws IOException{
		if (DateCheck.checkDayOffEmpty(date)){
			return "deletedaysoff";
		} else {
			Query query = new Query();
			query.deleteDate(date);
			query.deletePurpose(date);
			String dates = query.selectDate();
			String purposes = query.selectPurpose();
			StringBuilder sb = DaysOffParser.parseDate(dates);
			StringBuilder purpose = DaysOffParser.parsePurpose(purposes);
			model.addAttribute("dates", sb.toString());
			model.addAttribute("purposes", purpose.toString());
			return "result";
		}
	}
	@RequestMapping(value = "/deleteTimePeriod", method = RequestMethod.POST)
	public String timePeriodDelete(@ModelAttribute("Date") Date date, Model model) throws IOException{
		if (DateCheck.checkTimePeriodEmpty(date)){
			return "deletedaysoffperiod";
		} else {

			Query query = new Query();
			query.deleteTime(date);
			query.deleteDate(date);
			query.deletePurpose(date);
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
	@RequestMapping(value = "/deleteVacation", method = RequestMethod.POST)
	public String vacationDelete(@ModelAttribute("Date") Date date, Model model) throws IOException{
		if (DateCheck.checkVacationEmpty(date)){
			return "deletevacation";
		} else {
			Query query = new Query();
			query.deleteVacation(date);
			String vacation = query.selectVacation();
			StringBuilder sb = DaysOffParser.parseVacation(vacation);
			model.addAttribute("vacations", sb.toString());
			return "result";
		}
	}
	@RequestMapping(value="/daysOffList")
	public String daysOffList(Model model) throws IOException{
		Query query = new Query();
		String dates = query.selectDate();
		String purposes = query.selectPurpose();
		String times = query.selectTime();
		String vacations = query.selectVacation();
		StringBuilder sb = DaysOffParser.parseDate(dates);
		StringBuilder purpose = DaysOffParser.parsePurpose(purposes);
		StringBuilder time = DaysOffParser.parseTime(times);
		StringBuilder vacation = DaysOffParser.parseVacation(vacations);
		model.addAttribute("dates", sb.toString());
		model.addAttribute("purposes", purpose.toString());
		model.addAttribute("times", time.toString());
		model.addAttribute("vacations", vacation.toString());
		return "result";
	}

}