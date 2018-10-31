package com.codeup.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;
;

@Controller
public class RollDiceController {
    static Integer dice;
   @GetMapping("/roll-dice")
//    @ResponseBody
    public String showDice(){
       return "rolldice";
   }
   @GetMapping("/roll-dice/{n}")
    @ResponseBody
    public String checkGuess(@PathVariable Integer n){
            Integer diceRoll =  new Random().nextInt(6)+1;
            String response ="<h2>The number rolled was " + diceRoll.toString()+"</h2>";
            if( n == diceRoll)
                response +="<h2>You got it buddy</h2>";
            else
                response += "<h6> Sorry try again</h6>";
            return response;
            }
}
