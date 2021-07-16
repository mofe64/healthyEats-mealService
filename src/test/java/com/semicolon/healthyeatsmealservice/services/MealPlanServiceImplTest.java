package com.semicolon.healthyeatsmealservice.services;

import com.semicolon.healthyeatsmealservice.data.models.MealPlan;
import com.semicolon.healthyeatsmealservice.data.repository.MealPlanRepository;
import com.semicolon.healthyeatsmealservice.exceptions.MealPlanException;
import com.semicolon.healthyeatsmealservice.services.dtos.MealPlanDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.assertj.core.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class MealPlanServiceImplTest {
    @Mock
    MealPlanRepository mealPlanRepository;
    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    MealPlanServiceImpl mealPlanService;

    Pageable page;
    MealPlan mealPlan1;
    MealPlan mealPlan2;
    MealPlanDTO mealPlanDTO;

    @BeforeEach
    void setUp() {
        page = PageRequest.of(0, 20);
        mealPlan1 = new MealPlan();
        mealPlan2 = new MealPlan();
        mealPlanDTO = new MealPlanDTO();
    }

    @Test
    void getMealPlanById() throws MealPlanException {
        //given
        when(mealPlanRepository.findById(anyString())).thenReturn(Optional.of(mealPlan1));
        //when
        mealPlanService.getMealPlanById("someId");
        //then
        verify(mealPlanRepository).findById("someId");
    }

    @Test
    void  getMealPlanByIdThrowsMealPlanExceptionWhenWrongIdGiven() {
        //given
        String wrongId = "wrongId";
        //when
        //then
        assertThatThrownBy(()-> mealPlanService.getMealPlanById(wrongId))
                .isInstanceOf(MealPlanException.class);
    }

    @Test
    void getMealPlanByName() throws MealPlanException {
        //given
        when(mealPlanRepository.findMealPlanByName(anyString())).thenReturn(Optional.of(mealPlan1));
        //when
        mealPlanService.getMealPlanByName("someName");
        //then
        verify(mealPlanRepository).findMealPlanByName("someName");
    }
    @Test
    void getMealPlanByNameThrowsExceptionWhenWrongNameSupplied(){
        //given
        String wrongName = "wrongId";
        //when
        //then
        assertThatThrownBy(()-> mealPlanService.getMealPlanByName(wrongName))
                .isInstanceOf(MealPlanException.class);
    }

    @Test
    void getAllMealPlans() {
        //given
        when(mealPlanRepository.findAll(page)).thenReturn(Page.empty());
        //when
        mealPlanService.getAllMealPlans(0);
        //then
        verify(mealPlanRepository).findAll(page);

    }

    @Test
    void createMealPlan() {
        //when
        mealPlanService.createMealPlan(mealPlanDTO);
        //then
        verify(mealPlanRepository).save(any());
    }

    @Test
    void updateMealPlan() throws MealPlanException {
        //given
        when(mealPlanRepository.findById(anyString())).thenReturn(Optional.of(mealPlan1));
        //when
        mealPlanService.updateMealPlan(anyString(), mealPlanDTO);
        //then
        verify(mealPlanRepository).findById(anyString());
        verify(mealPlanRepository).save(any());
    }

    @Test
    void  updateMealPlanThrowsMealPlanExceptionWhenWrongIdGiven() {
        //given
        String wrongId = "wrongId";
        //when
        //then
        assertThatThrownBy(()-> mealPlanService.updateMealPlan(wrongId, any()))
                .isInstanceOf(MealPlanException.class);
    }

    @Test
    void deleteMealPlan() throws MealPlanException {
        //given
        when(mealPlanRepository.findById(anyString())).thenReturn(Optional.of(mealPlan1));
        //when
        mealPlanService.deleteMealPlan(anyString());
        //then
        verify(mealPlanRepository).findById(anyString());
        verify(mealPlanRepository).delete(any());
    }

    @Test
    void  deleteMealPlanThrowsMealPlanExceptionWhenWrongIdGiven() {
        //given
        String wrongId = "wrongId";
        //when
        //then
        assertThatThrownBy(()-> mealPlanService.deleteMealPlan(wrongId))
                .isInstanceOf(MealPlanException.class);
    }

}