//MenuView Object constructor
function MenuView(parent,dishModel,dinnerModel,generalController) {
	// Get all the relevant elements of the view (ones that show data
	// and/or ones that responed to interaction)
	this.numberOfGuests = $("#numberOfGuests");
	this.totalPrice = $("#totalPrice");
	this.plusButton = $("#plusGuest");
	this.minusButton = $("#minusGuest");
	this.menuViewDishList = $("#menuViewDishList");

	//Set the inital values of the components
	this.numberOfGuests.html(dinnerModel.getNumberOfGuests());
	this.totalPrice.html(dinnerModel.getTotalMenuPrice());
	
	
	//	Methods
	//	----------------------------------------

	//	Creating dish list
	this.getDishList = function(menuViewDishList) {
		//	Defining variables
		var	obj, 		// Object with dishes of type .stage
			k,		//
			domArr = [],	// counter i
			domBr,
			domDivR,
			domDivSpan,
			domImg,
			domP;
		
		//	Fetching object
		obj = dishModel.getDishes(generalController.stage);

		//	Building DOM
		domBr = $("<br/>");
		domDivR = $("<div>");
		domDivR.addClass("row");

		jQuery.each(obj, function(i, val) {
			domDivSpan = $("<div>");
			domDivSpan.addClass("span2");
			domDivSpan.addClass("id" + obj[i]['id']);

			domImg = $("<img>");
			domImg.addClass("id" + obj[i]['id']);
			domImg.attr("src", "images/" + obj[i]["image"]);

			domP = $("<p>");
			domP.addClass("id" + obj[i]['id']);
			domP.html(obj[i]["name"]);

			//	Appending
			domDivSpan.append(domImg);
			domDivSpan.append(domP);
			domDivR.append(domDivSpan);

		});
		menuViewDishList.append(domBr);
		menuViewDishList.append(domDivR);
		
	}
	//	Populating menu list
	this.populateMenuList = function() {
		//	Defining variables
		var obj = {};
		
		//	Fetching dishes from menu
		obj.starter = dishModel.getDish(dinnerModel.getSelectedDish("starter"));
		obj.main = dishModel.getDish(dinnerModel.getSelectedDish("main course"));
		obj.dessert = dishModel.getDish(dinnerModel.getSelectedDish("dessert"));
		
		//	IF
		if (obj.starter) {
			this.populateDish("starter", obj.starter);
	
		}
		if (obj.main) {
			this.populateDish("main course", obj.main);

		}
		if (obj.dessert) {
			this.populateDish("dessert", obj.dessert);

		}	
	}
	//	Populate Dish
	this.populateDish = function(type, obj) {
		//	Defining variables
		var domDiv_menu,
			domDiv_img,
			domDiv_info,
			domDiv_remove,
			domImg,
			domP_name,
			domP_cost,
			domP_costID,
			domBtn,
			domIcon;
		
		//	Building DOM
		if (type == "starter") {
			domDiv_menu = $("#menuViewStarterDish");
			domP_costID = "selectedStarterCost";
			
		} else if (type == "main course" ) {
			domDiv_menu = $("#menuViewMainDish");
			domP_costID = "selectedMainCost";
			
		} else {
			domDiv_menu = $("#menuViewDessertDish");
			domP_costID = "selectedDessertCost";
			
		}
		domDiv_img = $("<div>");
		domDiv_img.addClass("span1");
		
		domDiv_info = $("<div>");
		domDiv_info.addClass("span2");
		
		domDiv_remove = $("<div>");
		domDiv_remove.addClass("span1");
		
		domImg = $("<img>");
		domImg.attr("src", "images/" + obj["image"]);
		
		domP_name = $("<p>");
		domP_name.html(type + ": " + obj["name"]);
		
		domP_cost = $("<p>");
		domP_cost.attr("id", domP_costID);
		domP_cost.html("Cost: " + dishModel.getPrice(obj["id"]));
		
		domBtn = $("<button>");
		domBtn.addClass("btn btn-danger");
		
		domIcon = $("<i>");
		domIcon.addClass("icon-remove");
		
		//	Appending
		domDiv_img.append(domImg);
		domDiv_info.append(domP_name);
		domDiv_info.append(domP_cost);
		domBtn.append(domIcon);
		domDiv_remove.append(domBtn);
		
		domDiv_menu.append("<br/>");
		domDiv_menu.append(domDiv_img);
		domDiv_menu.append(domDiv_info);
		domDiv_menu.append(domDiv_remove);
		
		/*
		 * 				<div class="span1">
							<img src="http://placehold.it/150x150"/>

						</div>
						<div class="span2">
							<p>Starter: Dish</p>
							<p>Cost: $0.0</p>

						</div>
						<div class="span1">
							<button class="btn btn-danger"><i class="icon-remove"></i></button>

						</div>
		 */
		
	}

	/*****************************************  
	      Observer implementation    
	*****************************************/
	
	//Register an observer to the model
	dinnerModel.addObserver(this);
	
	//This function gets called when there is a change at the model
	this.update = function(arg){
		this.numberOfGuests.html(dinnerModel.getNumberOfGuests());
		this.totalPrice.html(dinnerModel.getTotalMenuPrice());

	}
}
 
