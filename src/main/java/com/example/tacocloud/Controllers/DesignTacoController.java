package com.example.tacocloud.Controllers;



import com.example.tacocloud.Models.Order;
import com.example.tacocloud.Models.Taco;
import com.example.tacocloud.Repositories.IngredientRepository;
import com.example.tacocloud.Repositories.TacoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;
import com.example.tacocloud.Models.Ingredient.Type;
import com.example.tacocloud.Models.Ingredient;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private IngredientRepository ingredientRepository;

    private TacoRepository tacoRepository;

    @Autowired
    public DesignTacoController(
            IngredientRepository ingredientRepo,
            TacoRepository tacoRepo) {
        this.ingredientRepository = ingredientRepo;
        this.tacoRepository = tacoRepo;
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public String showDesignForm(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(i -> ingredients.add(i));

        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }

        return "design";
    }

    @PostMapping
    public String processDesign(@Valid Taco design, Errors errors, @ModelAttribute Order order){
        if(errors.hasErrors()){
            return "design";
        }
        //Save the taco design....
        Taco saved = tacoRepository.save(design);
        order.addDesign(saved);
        log.info("Processing design: " + design);
        return "redirect:/orders/current";
    }

    /*private Ingredient filterByType(List<Ingredient> ingredients, Type type) {
        for(Ingredient ingredient : ingredients){
            if(ingredient.getType().equals(type)){
                return ingredient;
            }
        }
        return null;
    }*/

    private List<Ingredient> filterByType(
            List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }


}
