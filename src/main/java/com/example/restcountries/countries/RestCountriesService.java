package com.example.restcountries.countries;

import com.example.restcountries.countries.RestCountiesRepository;
import com.example.restcountries.domain.Country;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class RestCountriesService {

    private static RestCountiesRepository restCountiesRepository;

    public RestCountriesService(RestCountiesRepository restCountiesRepository) {
        this.restCountiesRepository = restCountiesRepository;
    }

    public List<Country> getCountryList() {
        //String inputData = readFile();
        String inputData = callApi();
        JSONArray rawResults = new JSONArray(inputData);
        List<Country> countryList = new ArrayList<>();
        for (int i = 0; i < rawResults.length(); i++) {
            JSONObject obj = rawResults.getJSONObject(i);
            String name = obj.getString("name");
            String capital = obj.getString("capital");
            String currencies = obj.getJSONArray("currencies").getJSONObject(0).getString("code");
            int population = obj.getInt("population");
            Double area = name.equals("French Guiana") ? 83534.0 : obj.getDouble("area");
            countryList.add(new Country(name, capital, currencies, population, area));
        }
        return countryList;
    }

    public String callApi() {
        try {
            URL url = new URL("https://restcountries.com/v2/regionalbloc/" +
                    "eu?fields=name,capital,currencies,population,area");
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuilder inputData = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                inputData.append(inputLine);
            }
            return inputData.toString();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            ;
        }
        return "";
    }

    // Use this if you want to read countries from countries.json!

//    public String readFile() {
//        try {
//            BufferedReader in = new BufferedReader(new FileReader(new File(restCountiesRepository.FILE).getAbsolutePath()));
//            StringBuilder inputData = new StringBuilder();
//            String inputLine;
//            while ((inputLine = in.readLine()) != null) {
//                inputData.append(inputLine);
//            }
//            return inputData.toString();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public List<Country> topTenCountriesWithBiggestPopulation() {
        List<Country> countryList = getCountryList();
        return countryList.stream()
                .sorted((c1, c2) -> Integer.compare(c2.getPopulation(), c1.getPopulation()))
                .limit(10).toList();
    }

    public List<Country> topTenCountriesWithBiggestArea() {
        List<Country> countryList = getCountryList();
        return countryList.stream()
                .sorted((c1, c2) -> Double.compare(c2.getArea(), c1.getArea()))
                .limit(10).toList();
    }

    public List<Country> topTenCountriesWithBiggestPopulationDensity() {
        List<Country> countryList = getCountryList();
        return countryList.stream()
                .sorted((c1, c2) -> Double.compare(c2.getPopulation() / c2.getArea(),
                        c1.getPopulation() / c1.getArea()))
                .limit(10).toList();
    }

}
