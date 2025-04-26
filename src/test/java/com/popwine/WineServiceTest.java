package com.popwine;

import com.popwine.backend.module.wine.application.WineService;
import com.popwine.backend.module.wine.controller.dto.WineRequestDto;
import com.popwine.backend.module.wine.controller.dto.WineResponseDto;
import com.popwine.backend.module.wine.domain.entity.Wine;
import com.popwine.backend.module.wine.domain.repository.WineRepository;
import com.popwine.backend.module.wine.domain.vo.Price;
import com.popwine.backend.module.wine.domain.vo.TasteProfile;
import com.popwine.backend.module.wine.domain.enums.WineType;
import com.popwine.backend.module.wine.domain.vo.WineName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Description;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class WineServiceTest {

    @Mock
    private WineRepository wineRepository;

    @InjectMocks
    private WineService wineService;

    @Test
    @Description("모든 와인 조회")
    void testGetAllWine() {
        // Given
        Wine wine1 = Wine.builder()
                .id(1L)
                .name(new WineName("샤또 마고", "Chateau Margaux"))
                .price(new Price(150000))
                .vintage(2020)
                .country("France")
                .region("Bordeaux")
                .grapeVariety("Cabernet Sauvignon")
                .wineType(WineType.RED)
                .build();

        Wine wine2 = Wine.builder()
                .id(2L)
                .name(new WineName("오푸스 원", "Opus One"))
                .price(new Price(200000))
                .vintage(2018)
                .country("USA")
                .region("Napa Valley")
                .grapeVariety("Cabernet Sauvignon")
                .wineType(WineType.RED)
                .build();

        when(wineRepository.findAll()).thenReturn(List.of(wine1, wine2));

        // When
        List<WineResponseDto> result = wineService.getAllWines();

        // Then
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(2L, result.get(1).getId());
    }

    @Test
    @Description("카테고리 와인 조회")
    void testGetWinesByCategory() {
        // Given
        List<Long> categoryIds = List.of(1L, 2L);

        Wine wine = Wine.builder()
                .id(1L)
                .name(new WineName("샤또 마고", "Chateau Margaux"))
                .price(new Price(150000))
                .vintage(2020)
                .country("France")
                .region("Bordeaux")
                .grapeVariety("Cabernet Sauvignon")
                .wineType(WineType.RED)
                .tasteProfile(new TasteProfile(3, 3, 3))
                .imageUrl("https://example.com/image.jpg")
                .build();

        when(wineRepository.findByCategoryFilters(categoryIds)).thenReturn(List.of(wine));

        // When
        List<WineResponseDto> result = wineService.getWinesByCategory(categoryIds);

        // Then
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
    }

    @Test
    @Description("와인 ID통해 상세정보 조회")
    void testGetWineById() {
        // Given
        Long wineId = 1L;

        Wine wine = Wine.builder()
                .id(wineId)
                .name(new WineName("샤또 마고", "Chateau Margaux"))
                .price(new Price(150000))
                .vintage(2020)
                .country("France")
                .region("Bordeaux")
                .grapeVariety("Cabernet Sauvignon")
                .wineType(WineType.RED)
                .tasteProfile(new TasteProfile(3, 3, 3))
                .imageUrl("https://example.com/image.jpg")
                .build();

        when(wineRepository.findById(wineId)).thenReturn(Optional.of(wine));

        // When
        WineResponseDto result = wineService.getWineById(wineId);

        // Then
        assertEquals(wineId, result.getId());
        assertEquals(150000, result.getPrice());
        assertEquals("France", result.getCountry());
        assertEquals("Bordeaux", result.getRegion());
        assertEquals("Cabernet Sauvignon", result.getGrapeVariety());
        assertEquals("RED", result.getWineType().name());
        assertEquals("https://example.com/image.jpg", result.getImageUrl());
    }

    @Test
    @Description("와인 등록")
    void testCreateWine() {

        // Given
        WineRequestDto wineRequestDto = WineRequestDto.builder()
                .wineName(new WineName("샤또 마고", "Chateau Margaux"))
                .price(new Price(150000))
                .vintage(2020)
                .country("France")
                .region("Bordeaux")
                .grapeVariety("Cabernet Sauvignon")
                .wineType(WineType.RED)
                .tasteProfile(new TasteProfile(3, 3, 3))
                .imageUrl("https://example.com/image.jpg")
                .build();

        Wine wine = Wine.builder()
                .id(1L)
                .name(new WineName("샤또 마고", "Chateau Margaux"))
                .price(new Price(150000))
                .vintage(2020)
                .country("France")
                .region("Bordeaux")
                .grapeVariety("Cabernet Sauvignon")
                .wineType(WineType.RED)
                .tasteProfile(new TasteProfile(3, 3, 3))
                .imageUrl("https://example.com/image.jpg")
                .build();

        when(wineRepository.save(any(Wine.class))).thenReturn(wine);


        // When
        WineResponseDto result = wineService.createWine(wineRequestDto);

        // Then
        assertEquals(wine.getId(), result.getId());
        assertEquals(wine.getPrice().getValue(), result.getPrice());
    }
}
