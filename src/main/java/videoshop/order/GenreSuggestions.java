package videoshop.order;

import org.springframework.web.bind.annotation.*;
import videoshop.catalog.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.salespointframework.catalog.Product;
import org.salespointframework.core.AbstractEntity;
import org.salespointframework.order.Cart;
import org.salespointframework.order.Order;
import org.salespointframework.order.OrderManager;
import org.salespointframework.order.OrderStatus;
import org.salespointframework.payment.Cash;
import org.salespointframework.quantity.Quantity;
import org.salespointframework.useraccount.UserAccount;
import org.salespointframework.useraccount.web.LoggedIn;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import videoshop.order.OrderController;


@Controller
class GenreSuggestion {

    private final VideoCatalog catalog;
    private String discGenre;

    //Constructor
    GenreSuggestion(VideoCatalog videoCatalog) {
        this.catalog = videoCatalog;

    }

    @GetMapping("/suggestions")
    String genreSuggestion(Model model){

        discGenre = OrderController.genre;

        /*The genre "Sci-Fi" is used as an example because the findByGenre() mehtod only accepts one String.
        To match every genre, regex should be used (e.g. ".*Sci-Fi.*") to match movies that are categorized under multiple genres.
        However, findbyGenre() does not accept regex expressions.
        If it did, I would exchange the statement at line 52 with the ones in line 50 & 51 and include the regex expressions in single_genres for every genre.
         */


        //String[] single_genres = genre.split("/");
        //Iterable<Disc> movies = catalog.findByGenre(single_genres[0]);
        Iterable<Disc> movies = catalog.findByGenre("Sci-Fi");
        List<Disc> movie_discs = new ArrayList<>();
        movies.forEach(movie_discs::add);
        Disc new_movie = movie_discs.get(0);
        //to show that extracting the genre still works
        System.out.print(discGenre);

        // I decided to only choose one movie of each genre and only one genre per movie (if the latter worked) but multiple would of course be possible as well.



        model.addAttribute("suggestions", new_movie);
        model.addAttribute("title", "Diese Filme könnten Sie auch interessieren");

        return "suggestions";

    }

}