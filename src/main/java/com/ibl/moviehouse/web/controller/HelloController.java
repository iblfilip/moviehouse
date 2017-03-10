package com.ibl.moviehouse.web.controller;

import com.google.identitytoolkit.GitkitUser;
import com.ibl.moviehouse.dataobjects.Movie;
import com.ibl.moviehouse.service.ProcessorService;
import com.ibl.moviehouse.tools.RatingCountTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class HelloController extends WebMvcConfigurerAdapter{

	private static final Logger logger = Logger.getLogger("Hello");
	private int userId;

	@Autowired
	FormValidator formValidator;

	@Autowired
	ProcessorService processor;

	/*@InitBinder
	public void initBinder(final WebDataBinder binder){
		final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}*/

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, "seenDate", new CustomDateEditor(dateFormat, true));
	}


	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String printWelcome() {

		return "welcome";

	}
	@RequestMapping(value="/gitkit", method = RequestMethod.GET)
	public void gitkit(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		StringBuilder builder = new StringBuilder();
		String line;
		try {
			while ((line = request.getReader().readLine()) != null) {
				builder.append(line);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		String postBody = URLEncoder.encode(builder.toString(), "UTF-8");

		try {
			response.getWriter().print(new Scanner(new File(getClass().getClassLoader().getResource("gitkit-widget.jsp").getFile()), "UTF-8")
					.useDelimiter("\\A").next()
					.replaceAll("JAVASCRIPT_ESCAPED_POST_BODY", postBody)
					.toString());
			response.setStatus(HttpServletResponse.SC_OK);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			response.getWriter().print(e.toString() + "chyba");
		}
	}
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request) throws ServletException, IOException{
		HttpSession session = request.getSession();
		Integer count = (Integer) session.getAttribute("tracker.count");
		if (count == null)
			count = new Integer(1);
		else
			count = new Integer(count.intValue() + 1);
		session.setAttribute("tracker.count", count);
		logger.log(Level.INFO, ">>> index page visited " + count + ". time");

		GitkitUser gitkitUser = processor.getGitKitUser(request);

		if (gitkitUser == null) {
			logger.log(Level.INFO, "User is not signed in, redirected to welcome servlet");
			return new ModelAndView("welcome");
		}
		//TODO oddelit useri kteri maji count vetsi nez 1, udelat jim jednodussi
		else {
			logger.log(Level.INFO, "User signed in, token is valid, info: " + gitkitUser.getEmail() + ", " + gitkitUser.getName());

			if (count >= 1) {
				logger.log(Level.INFO, "User is on page, page visited more than once.");
				userId = processor.getUserId(gitkitUser.getEmail());
			} else
				userId = processor.doUserLogic(gitkitUser);

			List<Movie> movieList = processor.selectAllMovies(userId);
			ModelAndView modelAndView = new ModelAndView("index");
			modelAndView.addObject("movieList", movieList);
			modelAndView.addObject("userId", userId);
			return modelAndView;
	}}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(Model model) throws Exception {
		referenceData(model);
		model.addAttribute("command", new Movie());
		model.addAttribute("control", "blank");
		return "form";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(Model model) throws Exception {
		Movie movie = processor.selectMovieAccId(54);
		referenceData(model);
		model.addAttribute("command", movie);
		return "form";
	}
	/*@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() throws Exception {

		return new ModelAndView("form", "command", new Movie());
	}*/

	@RequestMapping(value = "/createrecord", method = RequestMethod.POST)
	public String createRecord(@ModelAttribute("command") Movie movie, @RequestParam("control") String movieId,
							   ModelMap model, Model modell, HttpServletRequest request, BindingResult bindingResult) throws Exception {
		logger.log(Level.INFO, "bind result "+bindingResult.hasErrors());
		formValidator.validate(movie, bindingResult);

		if(bindingResult.hasErrors()) {
			model.addAttribute("control", movieId);
			referenceData(modell);
			return "form";
		}
		logger.log(Level.INFO, "movie id je"+movieId);
		logger.log(Level.INFO, "novy"+movie.getRatDirector());
		logger.log(Level.INFO, "novy"+movie.getRatActors());
		movie.setUserId(processor.getUserId(request));

		if(movieId.equals("blank")) {
			processor.insertMovie(movie);
		}
		else {
			movie.setMovieId(Integer.parseInt(movieId));
			RatingCountTool ratingCountTool = new RatingCountTool();
			movie = ratingCountTool.updateTotalRating(movie);
			processor.updateMovieDynamically(movie);
		}


		return "redirect:/index";

	}
	@RequestMapping(value = "/detailbuttons", method = RequestMethod.POST)
	public ModelAndView detailButtons(@ModelAttribute("command") Movie movie, @RequestParam(value = "selectedMovie") String movieId, @RequestParam(value = "edit", required = false) String editButton, @RequestParam(value = "erase", required = false) String eraseButton, Model model) throws Exception {
		if(editButton != null) {
			Movie movie1 = processor.selectMovieAccId(Integer.parseInt(movieId));
			ModelAndView modelAndView = new ModelAndView("form");
			modelAndView.addObject("command", movie1);
			modelAndView.addObject("control", movie.getMovieId());
			referenceData(model);
			return modelAndView;
		}
		else if(eraseButton != null) {
			processor.deleteMovie(Integer.parseInt(movieId));
			return new ModelAndView("redirect:/index");
		}
		else
			return new ModelAndView("form");
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView editRecord(@ModelAttribute("command") Movie movie, @RequestParam(value = "edit", required = false) String editStatus, @RequestParam(value = "erase", required = false) String eraseStatus, @RequestParam(value = "detail", required = false) String detailStatus, @RequestParam("userId") String userId, Model model) throws Exception {
		logger.log(Level.INFO, "status param"+movie.getTitle());
		logger.log(Level.INFO, "status param"+editStatus);
		logger.log(Level.INFO, "status param"+eraseStatus);
		logger.log(Level.INFO, "status param"+userId);
		List<Movie> movies = processor.selectAllMovies(Integer.parseInt(userId));
		if(editStatus != null) {
			Movie movieChosen = movies.get(Integer.parseInt(editStatus));
			logger.log(Level.INFO,"movie1"+movieChosen.getTitle());
			ModelAndView modelAndView = new ModelAndView("form");
			modelAndView.addObject("command", movieChosen);
			modelAndView.addObject("control", movieChosen.getMovieId());
			referenceData(model);
			return modelAndView;
		}
		else if (eraseStatus != null) {
			int movieId = movies.get(Integer.parseInt(eraseStatus)).getMovieId();
			processor.deleteMovie(movieId);
			return new ModelAndView("redirect:/index");
		}
		else if (detailStatus != null) {
			Movie movie1 = movies.get(Integer.parseInt(detailStatus));
			logger.log(Level.INFO, "detail of movie "+ movie1.getTitle());
			ModelAndView modelAndView = new ModelAndView("detail");
			modelAndView.addObject("command", movie1);
			return modelAndView;
		}
		else
			return new ModelAndView("form");
	}



	protected void referenceData(Model model) throws Exception {
		List<String> genresList = new ArrayList<String>();
		genresList.add("comedy");
		genresList.add("thriller");
		model.addAttribute("genresList", genresList);

		List<Integer> ratingListValue = new ArrayList<Integer>();
		ratingListValue.add(0);
		ratingListValue.add(10);
		ratingListValue.add(20);
		ratingListValue.add(30);
		ratingListValue.add(40);
		ratingListValue.add(50);
		ratingListValue.add(60);
		ratingListValue.add(70);
		ratingListValue.add(80);
		ratingListValue.add(90);
		ratingListValue.add(100);

		LinkedHashMap<Integer, String> rating = new LinkedHashMap<Integer, String>();
		rating.put(null, "undecided");
		rating.put(0, "0");
		rating.put(10,"10");

		List<String> ratingList = new ArrayList<String>();
		ratingList.add("0");
		ratingList.add("10");
		ratingList.add("20");
		ratingList.add("30");
		ratingList.add("40");
		ratingList.add("50");
		ratingList.add("60");
		ratingList.add("70");
		ratingList.add("80");
		ratingList.add("90");
		ratingList.add("100");
		model.addAttribute("ratingList", ratingList);
	}

	@RequestMapping(value = "/hello/{name:.+}", method = RequestMethod.GET)
	public ModelAndView hello(@PathVariable("name") String name) {

		ModelAndView model = new ModelAndView();
		model.setViewName("hello");
		model.addObject("msg", name);

		return model;

	}

}