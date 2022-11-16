package com.example.restcountries.countries;

import com.example.restcountries.domain.Country;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestCountriesController {

    private RestCountriesService restCountriesService;

    public RestCountriesController(RestCountriesService restCountriesService) {
        this.restCountriesService = restCountriesService;
    }

    @GetMapping("/countries/top10/population")
    public ResponseEntity<List<Country>> getTopTenCountriesWithBiggestPopulation() {
        return new ResponseEntity<>(restCountriesService.topTenCountriesWithBiggestPopulation(),
                HttpStatus.OK);
    }
    @GetMapping("/countries/top10/area")
    public ResponseEntity<List<Country>> getTopTenCountriesWithBiggestArea() {
        return new ResponseEntity<>(restCountriesService.topTenCountriesWithBiggestArea(),
                HttpStatus.OK);
    }
    @GetMapping("/countries/top10/population-density")
    public ResponseEntity<List<Country>> getTopTenCountriesWithBiggestPopulationDensity() {
        return new ResponseEntity<>(restCountriesService.topTenCountriesWithBiggestPopulationDensity(),
                HttpStatus.OK);
    }


}
