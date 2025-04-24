package com.popwine;

import com.popwine.backend.module.wine.application.WineService;
import com.popwine.backend.module.wine.controller.WineResponseDto;
import com.popwine.backend.module.wine.domain.entity.Wine;
import com.popwine.backend.module.wine.domain.repository.WineRepository;
import com.popwine.backend.module.wine.domain.vo.Price;
import com.popwine.backend.module.wine.domain.vo.TasteProfile;
import com.popwine.backend.module.wine.domain.enums.WineType;
import com.popwine.backend.module.wine.domain.vo.WineName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class WineServiceTest {

    @Mock
    private WineRepository wineRepository;

    @InjectMocks
    private WineService wineService;

    public WineServiceTest() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testGetAllWine() {
        //Given
        Wine wine = Wine.builder()
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
                .region("Napa Valley")
                .grapeVariety("Cabernet Sauvignon")
                .wineType(WineType.RED)
                .build();

        when(wineRepository.findAll()).thenReturn(List.of(wine,wine2));

        //When
        List<WineResponseDto> result = wineService.getAllWines();

        // Then
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(2L, result.get(1).getId());

    }



    //카테고리 와인 조회
    @Test
    void testFindWinesByCategories() {
        // Given
        String country = "France";
        String region = "Bordeaux";
        String wineTypeString = "RED";

        Wine wine = Wine.builder()
                .id(1L)
                .name(new WineName("샤또 마고", "Chateau Margaux"))
                .price(new Price(150000))
                .vintage(2020)
                .country(country)
                .region(region)
                .grapeVariety("Cabernet Sauvignon")
                .wineType(WineType.valueOf(wineTypeString)) // enum으로 변환
                .imageUrl("https://example.com/image.jpg")
                .tasteProfile(new TasteProfile(3,3,3)) // 예시로 TasteProfile 객체 생성
                .build();

        when(wineRepository.findByDynamicFilters(country, region, wineTypeString))
                .thenReturn(List.of(wine));

        // When
        List<WineResponseDto> result = wineService.findWinesByCategories(country, region, wineTypeString);

        // Then
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());

    }

    // 와인 ID통해 상세정보 조회
    @Test
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
                .imageUrl("https://example.com/image.jpg")
                .tasteProfile(new TasteProfile(3,3,3))
                .build();

        when(wineRepository.findById(wineId)).thenReturn(java.util.Optional.of(wine));

        // When
        WineResponseDto result = wineService.getWineById(wineId);

        // Then
        assertEquals(wineId, result.getId());
        assertEquals(150000, result.getPrice());
        assertEquals(2020, result.getVintage());
        assertEquals("France", result.getCountry());
        assertEquals("Bordeaux", result.getRegion());
        assertEquals("Cabernet Sauvignon", result.getGrapeVariety());
        assertEquals("RED", result.getWineType().name());
        assertEquals("https://example.com/image.jpg", result.getImageUrl());
        assertEquals("Dry", result.getTasteProfile().getSweetness());
    }
}
