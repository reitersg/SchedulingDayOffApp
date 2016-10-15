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



@Controller
public class HelloWorldController {
	private Query query;

	@RequestMapping(value = "/addTimePeriod1", method = RequestMethod.GET)
	public ModelAndView daysoffperiod1(){
		Time time = new Time();
		return new ModelAndView("daysoffperiod1", "Time", time);
	}
	@RequestMapping(value = "/vacation", method = RequestMethod.GET)
	public ModelAndView vacation(){
		return new ModelAndView("vacation", "Vacation", new Vacation());

	}
	@RequestMapping(value="/deletevacation", method = RequestMethod.GET)
	public ModelAndView deletevacation() throws IOException{
		Query query = new Query();
		StringBuilder sb = DaysOffParser.parseVacation(query.selectVacation());
		ModelAndView model = new ModelAndView("deletevacation", "Vacation", new Vacation());
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
		ModelAndView model = new ModelAndView("deletedaysoff", "Date", new Date());
		model.addObject("Dates", sb.toString());
		return model;
	}

	@RequestMapping(value = "/deletedaysoffperiod", method = RequestMethod.GET)
	public ModelAndView deletedaysoffperiod() throws IOException{
		Query query = new Query();
		StringBuilder time = DaysOffParser.parseTime(query.selectTime());
		ModelAndView model = new ModelAndView("deletedaysoffperiod", "Time", new Time());
		model.addObject("Times", time.toString());
		return model;
	}
	@RequestMapping(value ="/addDayOff", method = RequestMethod.POST)
	public String daysOffSubmit(@ModelAttribute("Date") Date date, Model model) throws IOException{

		if (DateCheck.checkHoliday(date)) {
			return "dateerror";
		}
		else {
				if (DateCheck.checkAlreadyExists(date.toString())){
					return "alreadyexists";
				} else {
					System.out.println(date.toString());
					System.out.println(date.getDay());
					System.out.println(date.getMonth());
					System.out.println(date.getYear());
					Query query = new Query();
					query.insertDate(date);
					String dates = query.selectDate();
					System.out.println(dates);
					StringBuilder sb = DaysOffParser.parseDate(dates);
					model.addAttribute("dates", sb.toString());
					return "result";
				}

		}
	}
	@RequestMapping(value = "/addTimePeriod2", method = RequestMethod.POST)
	public String timePeriodSubmit(@ModelAttribute("Time") Time time, Model model) throws IOException{

		if (DateCheck.checkHoliday(new Date(time.getDay(), time.getMonth(), time.getYear()))) {
			return "dateerror";
		}
		else {
			if (DateCheck.checkAlreadyExists(time.toString())){
				return "alreadyexists";
			} else {
				Query query = new Query();
				query.insertTime(time);
				String times = query.selectTime();
				System.out.println(times);
				StringBuilder tm = DaysOffParser.parseTime(times);
				model.addAttribute("times", tm.toString());
				return "result";
			}
		}
	}
	@RequestMapping(value ="/addVacation", method = RequestMethod.POST)
	public String vacationSubmit(@ModelAttribute("Vacation") Vacation vacation, Model model) throws IOException {
	 if (DateCheck.checkSameDate(vacation)){
			return "vacationerror";
		} else {
			if (DateCheck.checkVacationAlreadyExists(vacation.toString() + " - " + vacation.toStringEnd())) {
				return "alreadyexists";
			} else {
				Query query = new Query();
				query.insertVacation(vacation);
				String vacations = query.selectVacation();
				StringBuilder vc = DaysOffParser.parseVacation(vacations);
				model.addAttribute("vacations", vc.toString());
				return "result";
			}
		}
	}
	@RequestMapping(value = "/deleteDayOff", method = RequestMethod.POST)
	public String dayOffDelete(@ModelAttribute("Date") Date date, Model model) throws IOException{
		Query query = new Query();
		query.deleteDate(date);
			String dates = query.selectDate();
			StringBuilder sb = DaysOffParser.parseDate(dates);
			model.addAttribute("dates", sb.toString());
			return "result";

	}
	@RequestMapping(value = "/deleteTimePeriod", method = RequestMethod.POST)
	public String timePeriodDelete(@ModelAttribute("Time") Time time, Model model) throws IOException{

		Query query = new Query();
			query.deleteTime(time);
			String times = query.selectTime();
			StringBuilder tm = DaysOffParser.parseTime(times);
			model.addAttribute("times", tm.toString());
			return "result";

	}
	@RequestMapping(value = "/deleteVacation", method = RequestMethod.POST)
	public String vacationDelete(@ModelAttribute("Vacation") Vacation vacation, Model model) throws IOException{
		Query query = new Query();

			query.deleteVacation(vacation);
			String vacations = query.selectVacation();
			StringBuilder sb = DaysOffParser.parseVacation(vacations);
			model.addAttribute("vacations", sb.toString());
			return "result";

	}
	@RequestMapping(value="/daysOffList")
	public String daysOffList(Model model) throws IOException{
		Query query = new Query();
		String dates = query.selectDate();
		String times = query.selectTime();
		System.out.println(times);
		String vacations = query.selectVacation();
		StringBuilder sb = DaysOffParser.parseDate(dates);
//		Calendar cal = Calendar.getInstance(TimeZone.getDefault());
//		System.out.print(cal.get(Calendar.DATE));
//		int day = DaysOffParser.daysLeft(sb);
		StringBuilder time = DaysOffParser.parseTime(times);
		StringBuilder vacation = DaysOffParser.parseVacation(vacations);
		model.addAttribute("dates", sb.toString());
		model.addAttribute("times", time.toString());
		model.addAttribute("vacations", vacation.toString());
//		model.addAttribute("dateTime", day);
		return "result";
	}

}