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
//					Boolean ElecSearch = ctx.queryParam("electric");
//					int FrameSizesSearch = ctx.queryParam("frameSize").parseInt;
//					int SpeedsSearch = ctx.queryParam(Integer.parseInt("speeds"));
//					float PriceSearch = ctx.queryParam("price");
//					float PricesSearch = ctx.queryParam("price", "price");
				
				if (brandsSearch != null && !"".equals(brandsSearch)) {
					Set<Bike> bikesFound = userServ.searchAvailablebikesByBrand(brandsSearch);
					ctx.json(bikesFound);
				}
				
				else if (ModelsSearch != null && !"".equals(ModelsSearch)) {
					Set<Bike> bikesFound = userServ.searchAvailablebikesByModel(ModelsSearch);
					ctx.json(bikesFound);
				}
				
				else if (ColorsSearch != null && !"".equals(ColorsSearch)) {
					Set<Bike> bikesFound = userServ.searchAvailablebikesByColor(ColorsSearch);
					ctx.json(bikesFound);
				}
				
				else if (BrakessSearch != null && !"".equals(BrakessSearch)) {
					Set<Bike> bikesFound = userServ.searchAvailablebikesByBrakes(BrakessSearch);
					ctx.json(bikesFound);
				}
				
				else if (WheelsSearch != null && !"".equals(WheelsSearch)) {
					Set<Bike> bikesFound = userServ.searchAvailablebikesByWheels(WheelsSearch);
					ctx.json(bikesFound);
				}
				
//				else if (ElecSearch != null && !"".equals(ElecSearch)) {
//					Set<Bike> bikesFound = userServ.searchAvailablebikesByElec(ElecSearch);
//					ctx.json(bikesFound);
//				}
//				
//				else if (FrameSizesSearch != null && !"".equals(FrameSizesSearch)) {
//					Set<Bike> bikesFound = userServ.searchAvailablebikesByFrame(FrameSizesSearch);
//					ctx.json(bikesFound);
//				}
//				
//				else if (SpeedsSearch != null && !"".equals(SpeedsSearch)) {
//					Set<Bike> bikesFound = userServ.searchAvailablebikesBySpeeds(SpeedsSearch);
//					ctx.json(bikesFound);
//				}
//				
//				else if (PriceSearch != null && !"".equals(PriceSearch)) {
//					Set<Bike> bikesFound = userServ.searchAvailablebikesByPrice(PriceSearch);
//					ctx.json(bikesFound);
//				}
//				
//				else if (PricesSearch != null && !"".equals(PricesSearch)) {
//					Set<Bike> bikesFound = userServ.searchAvailablebikesByPriceRange(PricesSearch);
//					ctx.json(bikesFound);
//				}
		
				else {
					Set<Bike> availableBikes = userServ.viewAvailableBikes();
					ctx.json(availableBikes);
				}
			});
			
		});
	});
}
}
