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
	private static EmployeeService empServ = new EmployeeServiceImpl();
	
	public static void main(String[] args) {
		Javalin app = Javalin.create();
		
		app.start();
		
		app.routes(() -> {
			path("/bikes", () -> {
				get(ctx -> {
				
					String brandsSearch = ctx.queryParam("brand");
					String ModelsSearch = ctx.queryParam("style");
					String ColorsSearch = ctx.queryParam("color");
					String BrakessSearch = ctx.queryParam("brakes");	
					String WheelsSearch = ctx.queryParam("wheels");
					String ElecSearch = ctx.queryParam("electric");
					String FrameSizesSearch = ctx.queryParam("frameSize");
					String SpeedsSearch = ctx.queryParam("speeds");
					String PriceSearch = ctx.queryParam("price");
//					String PricesSearch = ctx.queryParam("price", "price");
				
				if (brandsSearch != null && !"".equals(brandsSearch)) {
					Set<Bike> bikesFound = userServ.searchAvailablebikesByBrand(brandsSearch);
					ctx.status(HttpStatus.ACCEPTED_202);
					ctx.json(bikesFound);
				}
				
				else if (ModelsSearch != null && !"".equals(ModelsSearch)) {
					Set<Bike> bikesFound = userServ.searchAvailablebikesByModel(ModelsSearch);
					ctx.status(HttpStatus.ACCEPTED_202);
					ctx.json(bikesFound);
				}
				
				else if (ColorsSearch != null && !"".equals(ColorsSearch)) {
					Set<Bike> bikesFound = userServ.searchAvailablebikesByColor(ColorsSearch);
					ctx.status(HttpStatus.ACCEPTED_202);
					ctx.json(bikesFound);
				}
				
				else if (BrakessSearch != null && !"".equals(BrakessSearch)) {
					Set<Bike> bikesFound = userServ.searchAvailablebikesByBrakes(BrakessSearch);
					ctx.status(HttpStatus.ACCEPTED_202);
					ctx.json(bikesFound);
				}
				
				else if (WheelsSearch != null && !"".equals(WheelsSearch)) {
					Set<Bike> bikesFound = userServ.searchAvailablebikesByWheels(WheelsSearch);
					ctx.status(HttpStatus.ACCEPTED_202);
					ctx.json(bikesFound);
				}
				
				else if (ElecSearch != null && !"".equals(ElecSearch)) {
					boolean ElectricSearch = Boolean.parseBoolean(ElecSearch);
					Set<Bike> bikesFound = userServ.searchAvailablebikesByElec(ElectricSearch);
					ctx.status(HttpStatus.ACCEPTED_202);
					ctx.json(bikesFound);
				}
				
				else if (FrameSizesSearch != null && !"".equals(FrameSizesSearch)) {
					int FrameSize = Integer.parseInt(FrameSizesSearch);
					Set<Bike> bikesFound = userServ.searchAvailablebikesByFrame(FrameSize);
					ctx.status(HttpStatus.ACCEPTED_202);
					ctx.json(bikesFound);
				}
				
				else if (SpeedsSearch != null && !"".equals(SpeedsSearch)) {
					int Speed = Integer.parseInt(SpeedsSearch);
					Set<Bike> bikesFound = userServ.searchAvailablebikesBySpeeds(Speed);
					ctx.status(HttpStatus.ACCEPTED_202);
					ctx.json(bikesFound);
				}
				
				else if (PriceSearch != null && !"".equals(PriceSearch)) {
					double Price = Double.parseDouble(PriceSearch);
					Set<Bike> bikesFound = userServ.searchAvailablebikesByPrice(Price);
					ctx.status(HttpStatus.ACCEPTED_202);
					ctx.json(bikesFound);
				}
				
//				else if (PricesSearch != null && !"".equals(PricesSearch)) {
//					double Price = Double.parseDouble(PricesSearch);
//					Set<Bike> bikesFound = userServ.searchAvailablebikesByPrice(Price);
//					ctx.status(HttpStatus.ACCEPTED_202);
//					ctx.json(bikesFound);
//				}
		
				else {
					Set<Bike> availableBikes = userServ.viewAvailableBikes();
					ctx.status(HttpStatus.ACCEPTED_202);
					ctx.json(availableBikes);
				}
			});
				post(ctx -> {
					Bike newBike = ctx.bodyAsClass(Bike.class);
					if (newBike !=null) {
						empServ.addNewBike(newBike);
						ctx.status(HttpStatus.CREATED_201);
						ctx.json(newBike);
					} else {
						ctx.status(HttpStatus.BAD_REQUEST_400);
					}
				});
		});
			path("/bikes/{id}", () -> {
				get(ctx -> {
						try {
							int bikeId = Integer.parseInt(ctx.pathParam("id"));
							Bike bike = empServ.getBikeById(bikeId);
							if (bike != null)
								ctx.json(bike);
							else
								ctx.status(404);
						} catch (NumberFormatException e) {
							ctx.status(400);
							ctx.result("Bike ID must be a numeric value");
						}
					});
					
				put(ctx -> {
						try {
							int bikeId = Integer.parseInt(ctx.pathParam("id"));
							Bike bikeToEdit = ctx.bodyAsClass(Bike.class);
							if (bikeToEdit != null && bikeToEdit.getId() == bikeId) {
								bikeToEdit = empServ.editBike(bikeToEdit);
								if (bikeToEdit != null)
									ctx.json(bikeToEdit);
								else
									ctx.status(404);
							} else {
								
								ctx.status(HttpCode.CONFLICT);
							}
						} catch (NumberFormatException e) {
							ctx.status(400);
							ctx.result("Bike ID must be a numeric value");
						}

			});
		});
	});
}
}
