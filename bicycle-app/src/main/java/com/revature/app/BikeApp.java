package com.revature.app;

import io.javalin.Javalin;
import io.javalin.http.HttpCode;

import static io.javalin.apibuilder.ApiBuilder.*;

import java.util.Set;

import org.eclipse.jetty.http.HttpStatus;

import com.revature.services.EmployeeService;
import com.revature.services.EmployeeServiceImpl;
import com.revature.services.UserService;
import com.revature.services.UserServiceImpl;
import com.revature.beans.Person;
import com.revature.beans.Bike;


public class BikeApp {
	private static UserService userServ = new UserServiceImpl();
	
	public static void main(String[] args) {
		Javalin app = Javalin.create();
		
		app.start();
		
		app.routes(() -> {
			path("/bikes", () -> {
				get(ctx -> {
				
					String brandsSearch = ctx.queryParam("brand");
				
				if (brandsSearch != null && !"".equals(brandsSearch)) {
					Set<Bike> bikesFound = userServ.searchAvailablebikesByBrand(brandsSearch);
					ctx.json(bikesFound);
				} else {
					Set<Bike> availablePets = userServ.viewAvailableBikes();
					ctx.json(availablePets);
				}
			});
			
		});
	});
}
}
