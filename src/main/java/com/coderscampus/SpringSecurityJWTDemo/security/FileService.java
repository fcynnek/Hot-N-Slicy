package com.coderscampus.SpringSecurityJWTDemo.security;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.coderscampus.SpringSecurityJWTDemo.domain.Restaurant;
import com.coderscampus.SpringSecurityJWTDemo.repository.RestaurantRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

@Service
public class FileService {

	private RestaurantRepository restaurantRepo;

	public FileService(RestaurantRepository restaurantRepo) {
		super();
		this.restaurantRepo = restaurantRepo;
	}

	public void readFile() {
		try (CSVReader csvReader = new CSVReader(new FileReader("DataUniqueEntries.csv"))) {
			List<String[]> records = csvReader.readAll();

			if (!records.isEmpty()) {
				System.out.println("Skipping header: " + String.join(", ", records.get(0)));
				records.remove(0);
			}

			for (String[] record : records) {
				String name = record[0];
				Restaurant restaurant = new Restaurant();
				restaurant.setName(name);
				restaurantRepo.save(restaurant);
			}

		} catch (IOException | CsvException e) {
			e.printStackTrace();
		}
	}

}
