package com.example.consumingrest.Controller;

import com.example.consumingrest.Service.QuoteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/example")
public class ExampleController {
    @Autowired
    private QuoteService quoteService;

    // @RequestMapping(method = RequestMethod.GET)
    // public String index(ModelMap model) {
    // Quote quote = quoteService.getQuote();
    // model.addAttribute("id", quote.getValue().getId());
    // model.addAttribute("content", quote.getValue().getQuote());
    // return "example";
    // }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("example");
        mav.addObject("quote", quoteService.getQuote().getValue());
        return mav;
    }

}
