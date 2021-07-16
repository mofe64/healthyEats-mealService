package com.semicolon.healthyeatsmealservice.services;

import com.semicolon.healthyeatsmealservice.data.models.Meal;
import com.semicolon.healthyeatsmealservice.data.repository.MealRepository;
import com.semicolon.healthyeatsmealservice.exceptions.MealException;
import com.semicolon.healthyeatsmealservice.services.dtos.MealDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@Slf4j
class MealServiceImplTest {
    @Mock
    private MealRepository mealRepository;
    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private MealServiceImpl mealService;

    Meal meal;
    MealDTO mealDTO;

    @BeforeEach
    void setUp() {
        meal = new Meal();
        mealDTO = new MealDTO();
    }


    @Test
    void createMeal() {
        //given
        mealDTO.setName("some meal");
        //when
        mealService.createMeal(mealDTO);
        //then
        verify(mealRepository).save(any());
    }

    @Test
    void updateMeal() throws MealException {
        //given
        when(mealRepository.findById(anyString())).thenReturn(Optional.of(meal));
        mealDTO.setName("update");
        //when
        mealService.updateMeal("someId", mealDTO);
        //then
        verify(mealRepository).save(meal);
    }

    @Test
    void updateMealThrowsAnExceptionWhenWrongIdPassedIn() {
        //given
        String wrongId = "wrongId";
        //when
        //then
        assertThatThrownBy(() -> mealService.updateMeal(wrongId, mealDTO))
                .isInstanceOf(MealException.class)
                .hasMessageContaining("No meal found with Id: " + wrongId);
    }

    @Test
    void deleteMeal() throws MealException {
        //given
        when(mealRepository.findById(anyString())).thenReturn(Optional.of(meal));
        //when
        mealService.deleteMeal("someId");
        //then
        verify(mealRepository).delete(meal);
    }

    @Test
    void deleteMealThrowsAnExceptionWhenWrongIdPassedIn(){
        //given
        String wrongId = "wrongId";
        //when
        //then
        assertThatThrownBy(() -> mealService.deleteMeal(wrongId))
                .isInstanceOf(MealException.class)
                .hasMessageContaining("No meal found with Id: " + wrongId);
    }

    @Test
    void findMealById() throws MealException {
        //given
        when(mealRepository.findById(anyString())).thenReturn(Optional.of(meal));
        //when
        mealService.findMealById("someId");
        //then
        verify(mealRepository).findById("someId");
    }

    @Test
    void fineMealByIdThrowsAnExceptionWhenWrongIdPassedIn(){
        //given
        String wrongId = "wrongId";
        //when
        //then
        assertThatThrownBy(() -> mealService.findMealById(wrongId))
                .isInstanceOf(MealException.class)
                .hasMessageContaining("No meal found with Id: " + wrongId);
    }

    @Test
    void findMealByName() throws MealException {
        //given
        when(mealRepository.findMealByName(anyString())).thenReturn(Optional.of(meal));
        //when
        mealService.findMealByName("someName");
        //then
        verify(mealRepository).findMealByName("someName");
    }

    @Test
    void findMealByNameThrowsAnExceptionWhenWrongNamePassedIn(){
        //given
        String wrongName = "wrongName";
        //when
        //then
        assertThatThrownBy(() -> mealService.findMealByName(wrongName))
                .isInstanceOf(MealException.class)
                .hasMessageContaining("No Meal found with name: " + wrongName);
    }
}