package com.example.springtest.controller;

import com.example.springtest.SpringTestApplication;
import com.example.springtest.controllers.RecipeController;
import com.example.springtest.exceptions.NoSuchRecipeException;
import com.example.springtest.models.Ingredient;
import com.example.springtest.models.Recipe;
import com.example.springtest.models.Step;
import com.example.springtest.security.SecurityConfig;
import com.example.springtest.services.RecipeService;
import com.example.springtest.utils.TestUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RecipeController.class)
@ContextConfiguration(classes = SpringTestApplication.class)
@ActiveProfiles("test")
@Import(SecurityConfig.class)
public class RecipeControllerUnitTest {

    @MockBean
    RecipeService recipeService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testGetRecipeByIdNormalBehavior() throws Exception {
        Recipe recipe = Recipe.builder()
                .name("test recipe")
                .difficultyRating(1)
                .ingredients(Set.of(
                        Ingredient.builder().amount("1 jar").name("pickles").build()
                )).steps(Set.of(
                        Step.builder().description("eat pickles").stepNumber(1).build()
                )).build();

        when(recipeService.getRecipeById(anyLong()))
                .thenReturn(recipe);

        mockMvc.perform(get("/recipes/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(TestUtil.convertObjectToJsonString(recipe)));
    }

    @Test
    public void testGetRecipeByIdFailureBehavior() throws Exception {
        when(recipeService.getRecipeById(any())).thenThrow(new NoSuchRecipeException("test this is in body"));

        mockMvc.perform(get("/recipes/" + 1L))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("test this is in body")));
    }

    @Test
    public void testGetRecipeByNameSuccessBehavior() throws Exception {
        when(recipeService.getRecipesByName(anyString())).thenReturn(
                List.of(
                        Recipe.builder()
                                .id(1L)
                                .name("searched recipe 1")
                                .difficultyRating(1)
                                .minutesToMake(5)
                                .ingredients(Set.of(
                                        Ingredient.builder()
                                                .amount("1 jar")
                                                .name("pickles")
                                                .state("jarred")
                                                .build()
                                )).steps(Set.of(
                                        Step.builder()
                                                .description("eat pickles")
                                                .stepNumber(1)
                                                .build()
                                )).build(),
                        Recipe.builder()
                                .id(2L)
                                .name("searched recipe 2")
                                .difficultyRating(10)
                                .minutesToMake(10)
                                .build()
                )
        );

        mockMvc.perform(get("/recipes/search/searched"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value(containsString("searched")))
                .andExpect(jsonPath("$[1].name").value(containsString("searched")));

    }

    @Test
    public void testGetRecipeByNameFailureBehavior() throws Exception {
        when(recipeService.getRecipesByName(anyString()))
                .thenThrow(new NoSuchRecipeException("could not find recipes"));

        mockMvc.perform(get("/recipes/search/gibberish"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andExpect(content().string("could not find recipes"));
    }

    @Test
    public void testGetAllRecipesSuccessBehavior() throws Exception {
        when(recipeService.getAllRecipes()).thenReturn(
                List.of(
                        Recipe.builder()
                                .id(1L).name("mocked name")
                                .minutesToMake(2000)
                                .difficultyRating(2).build(),
                        Recipe.builder().id(2L)
                                .name("another mocked name")
                                .minutesToMake(1)
                                .difficultyRating(10)
                                .ingredients(Set.of(
                                        Ingredient.builder()
                                                .id(1L)
                                                .name("mock sauce")
                                                .state("saucy")
                                                .amount("lots").build()
                                )).steps((Set.of(
                                        Step.builder()
                                                .id(1L)
                                                .stepNumber(1)
                                                .description("mock the mock sauce")
                                                .build()
                                ))).build()
                )
        );

        mockMvc.perform(get("/recipes"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("mocked name"))
                .andExpect(jsonPath("$[0].ingredients").isEmpty())
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].minutesToMake").value(1))
                .andExpect(jsonPath("$[1].ingredients[0].name").value("mock sauce"))
                .andExpect(jsonPath("$[1].steps[0].description").value("mock the mock sauce"));
    }

    @Test
    public void testGetAllRecipesFailureBehavior() throws Exception {
        when(recipeService.getAllRecipes()).thenThrow(new NoSuchRecipeException("this should be in the body"));

        mockMvc.perform(get("/recipes"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("this should be in the body")));
    }

    @Test
    public void testCreateNewRecipeSuccessBehavior() throws Exception {
        Recipe recipe = Recipe.builder()
                .id(1L).name("water in a glass")
                .difficultyRating(1)
                .minutesToMake(1)
                .ingredients(Set.of(
                        Ingredient.builder()
                                .id(1L)
                                .name("water")
                                .state("liquid")
                                .build()
                )).steps(Set.of(
                        Step.builder()
                                .id(1L)
                                .stepNumber(1)
                                .description("turn on faucet")
                                .build(),
                        Step.builder()
                                .id(2L)
                                .stepNumber(2)
                                .description("fill glass")
                                .build(),
                        Step.builder()
                                .id(3L)
                                .stepNumber(3)
                                .description("turn off faucet")
                                .build()
                )).locationURI(new URI("http://thisIsALink.com/1")).build();

        when(recipeService.createNewRecipe(any(Recipe.class))).thenReturn(recipe);

        mockMvc.perform(post("/recipes")
                    .contentType("application/json")
                    .content(TestUtil.convertObjectToJsonBytes(recipe))
                )
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "http://thisIsALink.com/1"))
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.minutesToMake").value(1))
                .andExpect(jsonPath("$.ingredients",hasSize(1)))
                .andExpect(jsonPath("$.ingredients[0].name").value("water"))
                .andExpect(jsonPath("$.steps", hasSize(3)))
                .andExpect(jsonPath("$.steps[0].id").isNumber())
                .andExpect(jsonPath("$.steps[0].stepNumber").isNotEmpty())
                .andExpect(jsonPath("$.steps[1].description").isNotEmpty());
    }

    @Test
    public void testCreateNewRecipeFailureBehavior() throws Exception {
        when(recipeService.createNewRecipe(any(Recipe.class)))
                .thenThrow(new IllegalStateException("does it match?"));

        mockMvc.perform(post("/recipes").content(TestUtil.convertObjectToJsonBytes(
                    Recipe.builder().build()))
                    .contentType("application/json")
                )
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("does it match?")));
    }

    @Test
    public void testDeleteRecipeByIdSuccessBehavior() throws Exception {
        when(recipeService.deleteRecipeById(anyLong()))
                .thenReturn(Recipe.builder()
                        .id(1L)
                        .name("deleted!")
                        .minutesToMake(1)
                        .difficultyRating(5)
                        .build());

        mockMvc.perform(delete("/recipes/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_PLAIN_VALUE + ";charset=UTF-8"))
                .andExpect(content().string("The recipe with ID 1 and name deleted! was deleted"));
    }

    @Test
    public void testDeleteRecipeByIdFailureBehavior() throws Exception {
        when(recipeService.deleteRecipeById(anyLong()))
                .thenThrow(new NoSuchRecipeException("this should be in the body!"));

        mockMvc.perform(delete("/recipes/1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andExpect(content().string("this should be in the body!"));
    }

    @Test
    public void testUpdateRecipeSuccessBehavior() throws Exception {
        Recipe recipe = Recipe.builder().name("update recipe").build();

        when(recipeService.updateRecipe(any(Recipe.class), eq(true))).thenReturn(recipe);

        mockMvc.perform(patch("/recipes")
                    .contentType("application/json")
                    .content(TestUtil.convertObjectToJsonBytes(recipe)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(TestUtil.convertObjectToJsonString(recipe)));
    }

    @Test
    public void testUpdateRecipeFailureBehavior() throws Exception {
        when(recipeService.updateRecipe(any(Recipe.class), eq(true)))
                .thenThrow(new NoSuchRecipeException("found in body!"));

        mockMvc.perform(patch("/recipes")
                    .contentType("application/json")
                    .content(TestUtil.convertObjectToJsonBytes(Recipe.builder().build())))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andExpect(content().string("found in body!"));

        when(recipeService.updateRecipe(any(Recipe.class), eq(true)))
                .thenThrow(new IllegalStateException("found in body?"));

        mockMvc.perform(patch("/recipes")
                    .contentType("application/json")
                    .content(TestUtil.convertObjectToJsonBytes(Recipe.builder().build())))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andExpect(content().string("found in body?"));
    }
}
