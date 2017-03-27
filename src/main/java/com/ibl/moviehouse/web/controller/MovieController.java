package com.ibl.moviehouse.web.controller;

import com.google.identitytoolkit.GitkitUser;
import com.ibl.moviehouse.dataobjects.Movie;
import com.ibl.moviehouse.enums.MovieGenresEnum;
import com.ibl.moviehouse.enums.MovieRatingEnum;
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
public class MovieController extends WebMvcConfigurerAdapter {

    private static final Logger logger = Logger.getLogger(MovieController.class.getName());
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
    public String printWelcome(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "welcome";

    }

    @RequestMapping(value = "/gitkit", method = RequestMethod.GET)
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
            response.getWriter().print(e.toString() + "error");
        }
    }

    /**
     * Checks if user is signed in through GitKit
     * sets session tracker.count with number how much times user found index page, increase value with every entrance
     * checks gitKitUser, if gitKitUser is null, user is not signed in and return to welcome page
     * if GitKitUser is not null, method doUserLogic() checks if user is already in database, this job done only if user is first time at index page
     * userId is saved to session, used in other processes
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Integer count = (Integer) session.getAttribute("tracker.count");
        if (count == null)
            count = new Integer(1);
        else
            count = new Integer(count.intValue() + 1);
        session.setAttribute("tracker.count", count);
        logger.log(Level.INFO, ">>> index page visited " + count + ". time");

        GitkitUser gitkitUser = processor.getGitKitUser(request);
        if (gitkitUser == null) {
            logger.log(Level.INFO, "User is not signed in, redirected to welcome page..");
            return new ModelAndView("redirect:/");
        } else {
            logger.log(Level.INFO, "User signed in, info: " + gitkitUser.getName());

            if (count <= 1) {
                logger.log(Level.INFO, "User is first time on index page.");
                userId = processor.doUserLogic(gitkitUser);
                session.setAttribute("userId", userId);
            } else if (count > 1)
                logger.log(Level.INFO, "User is on index page, page visited " + count + " times. User id" + session.getAttribute("userId"));

            List<Movie> movieList = processor.selectAllMovies((Integer) session.getAttribute("userId"));
            ModelAndView modelAndView = new ModelAndView("index");
            modelAndView.addObject("movieList", movieList);
            modelAndView.addObject("userId", session.getAttribute("userId"));
            return modelAndView;
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model) throws Exception {
        referenceData(model);
        model.addAttribute("command", new Movie());
        model.addAttribute("control", "blank");
        return "form";
    }

    /**
     * form is used for both update record and create new record
     * validate fields in form, if bindingResult has error, form is returned
     */
    @RequestMapping(value = "/createrecord", method = RequestMethod.POST)
    public String createRecord(@ModelAttribute("command") Movie movie, @RequestParam("control") String movieId,
                               ModelMap model, Model modell, HttpServletRequest request, BindingResult bindingResult) throws Exception {
        HttpSession session = request.getSession();
        formValidator.validate(movie, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("control", movieId);
            referenceData(modell);
            return "form";
        }
        movie.setUserId((Integer) (session.getAttribute("userId")));

        if (movieId.equals("blank")) {
            processor.insertMovie(movie);
        } else {
            movie.setMovieId(Integer.parseInt(movieId));
            RatingCountTool ratingCountTool = new RatingCountTool();
            movie = ratingCountTool.updateTotalRating(movie);
            processor.updateMovieDynamically(movie);
        }
        return "redirect:/index";
    }

    /**
     * handles erase, edit buttons on detail page
     */
    @RequestMapping(value = "/detailbuttons", method = RequestMethod.POST)
    public ModelAndView detailButtons(@ModelAttribute("command") Movie movie, @RequestParam(value = "selectedMovie") String movieId, @RequestParam(value = "edit", required = false) String editButton, @RequestParam(value = "erase", required = false) String eraseButton, Model model) throws Exception {
        if (editButton != null) {
            Movie movie1 = processor.selectMovieAccId(Integer.parseInt(movieId));
            ModelAndView modelAndView = new ModelAndView("form");
            modelAndView.addObject("command", movie1);
            modelAndView.addObject("control", movie.getMovieId());
            referenceData(model);
            return modelAndView;
        } else if (eraseButton != null) {
            processor.deleteMovie(Integer.parseInt(movieId));
            return new ModelAndView("redirect:/index");
        } else
            return new ModelAndView("form");
    }

    @RequestMapping(value = "/listbuttons", method = RequestMethod.POST)
    public ModelAndView listButtons(@ModelAttribute("command") Movie movie, @RequestParam(value = "edit", required = false) String editStatus, @RequestParam(value = "erase", required = false) String eraseStatus, @RequestParam(value = "detail", required = false) String detailStatus, @RequestParam("userId") String userId, Model model) throws Exception {
        List<Movie> movies = processor.selectAllMovies(Integer.parseInt(userId));
        if (editStatus != null) { // when button edit is clicked
            Movie movieChosen = movies.get(Integer.parseInt(editStatus));
            ModelAndView modelAndView = new ModelAndView("form");
            modelAndView.addObject("command", movieChosen);
            modelAndView.addObject("control", movieChosen.getMovieId());
            referenceData(model);
            return modelAndView;
        } else if (eraseStatus != null) { //when erase button is clicked
            int movieId = movies.get(Integer.parseInt(eraseStatus)).getMovieId();
            processor.deleteMovie(movieId);
            return new ModelAndView("redirect:/index");
        } else if (detailStatus != null) { //if name of movie is clicked, detail page is generated
            Movie movie1 = movies.get(Integer.parseInt(detailStatus));
            logger.log(Level.INFO, "detail of movie " + movie1.getTitle());
            ModelAndView modelAndView = new ModelAndView("detail");
            modelAndView.addObject("command", movie1);
            return modelAndView;
        } else
            return new ModelAndView("form");
    }

    // TODO implement signout url in gitkit, when it start working
    @RequestMapping(value = "/signout", method = RequestMethod.GET)
    public String signOut(HttpServletRequest request) {
        logger.log(Level.INFO, "sign out, invalidationg of sessions");
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/";
    }

    /**
     * Send attributes to model with genres and rating options
     */
    protected void referenceData(Model model) throws Exception {
        List<MovieGenresEnum> genresList = Arrays.asList(MovieGenresEnum.values());
        model.addAttribute("genresList", genresList);
        model.addAttribute("ratingList", MovieRatingEnum.all);
    }
}