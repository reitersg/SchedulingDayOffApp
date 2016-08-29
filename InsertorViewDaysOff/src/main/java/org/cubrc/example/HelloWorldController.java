package org.cubrc.example;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

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
	public ModelAndView deletevacation() throws IOException{
		Query query = new Query();
		StringBuilder sb = DaysOffParser.parseVacation(query.selectVacation());
		ModelAndView model = new ModelAndView("deletevacation", "Date", new Date());
		model.addObject("vacations", sb.toString());
		return model;
	}

	@RequestMapping(value = "/daysoff", method = RequestMethod.GET)
	public ModelAndView daysoff() {
		Date date = new Date();
		return new ModelAndView("daysoff", "Date", date);
	}

	@RequestMapping(value = "/deletedaysoff", method = RequestMethod.GET)
	public ModelAndView deletedaysoff() throws IOException {
		Query query = new Query();
		StringBuilder sb = DaysOffParser.parseDate(query.selectDate());
		StringBuilder purpose = DaysOffParser.parsePurpose(query.selectPurpose());
		StringBuilder time = DaysOffParser.parseTime(query.selectTime());
		ModelAndView model = new ModelAndView("deletedaysoff", "Date", new Date());
		model.addObject("Dates", sb.toString());
		model.addObject("Purposes", purpose.toString());
		model.addObject("Times", time.toString());
		return model;
	}

	@RequestMapping(value = "/deletedaysoffperiod", method = RequestMethod.GET)
	public ModelAndView deletedaysoffperiod() throws IOException{
		Query query = new Query();
		StringBuilder sb = DaysOffParser.parseDate(query.selectDate());
		StringBuilder purpose = DaysOffParser.parsePurpose(query.selectPurpose());
		StringBuilder time = DaysOffParser.parseTime(query.selectTime());
		ModelAndView model = new ModelAndView("deletedaysoffperiod", "Date", new Date());
		model.addObject("Dates", sb.toString());
		model.addObject("Purposes", purpose.toString());
		model.addObject("Times", time.toString());
		return model;
	}
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
					query.insertPurpose("("+date.toString()+") "+date.getPurpose());
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
		else if (DateCheck.checkHoliday(date)) {
			return "dateerror";
		}
		else {
			String insertDate = "\"" + date.toString() + "\"";
			Query query = new Query();
			if (DateCheck.checkAlreadyExists(date.toString())){
				return "alreadyexists";
			} else {
				String insertTime = "\"" + "(" + date.toString() + ") " + date.getTime() + "\"";
				query.insertDate(insertDate);
				query.insertTime(insertTime);
				query.insertPurpose("("+date.toString()+") "+date.getPurpose());
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
		} else if (DateCheck.checkSameDate(date)) {
			return "vacationerror";
		} else {
			if (DateCheck.checkVacationAlreadyExists(date.toString() + " - " + date.toStringEnd())) {
				return "alreadyexists";
			} else {
				Query query = new Query();
				query.insertVacation(date);
				String dates = query.selectDate();
				String purposes = query.selectPurpose();
				String times = query.selectTime();
				StringBuilder sb = DaysOffParser.parseDate(dates);
				StringBuilder purpose = DaysOffParser.parsePurpose(purposes);
				StringBuilder time = DaysOffParser.parseTime(times);
				model.addAttribute("dates", sb.toString());
				model.addAttribute("purposes", purpose.toString());
				model.addAttribute("times", time.toString());
				String vacations = query.selectVacation();
				StringBuilder vacation = DaysOffParser.parseVacation(vacations);
				model.addAttribute("vacations", vacation.toString());
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
		Calendar cal = Calendar.getInstance(TimeZone.getDefault());
		System.out.print(cal.get(Calendar.DATE));
//		int day = DaysOffParser.daysLeft(sb);
		StringBuilder purpose = DaysOffParser.parsePurpose(purposes);
		StringBuilder time = DaysOffParser.parseTime(times);
		StringBuilder vacation = DaysOffParser.parseVacation(vacations);
		model.addAttribute("dates", sb.toString());
		model.addAttribute("purposes", purpose.toString());
		model.addAttribute("times", time.toString());
		model.addAttribute("vacations", vacation.toString());
//		model.addAttribute("dateTime", day);
		return "result";
	}

}