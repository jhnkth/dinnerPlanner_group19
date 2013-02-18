//MenuView Object constructor
function MenuView(parent,dishModel,dinnerModel,generalController) {
	// Get all the relevant elements of the view (ones that show data
	// and/or ones that responed to interaction)
	this.numberOfGuests = $("#numberOfGuests");
	this.totalPrice = $("#totalPrice");
	this.plusButton = $("#plusGuest");
	this.minusButton = $("#minusGuest");
	this.finishButton = $("#finishBtn");
	this.menuViewDishList = $("#menuViewDishList");

	//Set the inital values of the components
	this.numberOfGuests.html(dinnerModel.getNumberOfGuests());
	this.totalPrice.html(dinnerModel.getTotalMenuPrice());
	
	
	//	Methods
	//	----------------------------------------
	//	Get changed stage
	this.getChangedStage = function() {
		//	Defining variables
		var stage,
			obj;
		
		//	Fetching current stage
		stage = generalController.stage;
		
		//	Fetching right object
		if (stage == "starter") {
			obj = {
				menuViewTitle : "Step 1: Starter",
				menuViewStepMenu : {
					1 : {
						title : "step 2: Main",
						id : "setStageMain",
						newStage : "main dish"
						
					},
					2 : {
						title : "step 3: Dessert",
						id : "setStageDessert",
						newStage : "dessert"
						
					}
				} 
			};
		} else if (stage == "main dish") {
			obj = {
				menuViewTitle : "Step 2: Main Course",
				menuViewStepMenu : {
					1 : {
						title : "step 1: Starter",
						id : "setStageStarter",
						newStage : "starter"
						
					},
					2 : {
						title : "step 3: Dessert",
						id : "setStageDessert",
						newStage : "dessert"
						
					}
				} 				
			};
		} else {
			obj = {
				menuViewTitle : "Step 3: Dessert",
				menuViewStepMenu : {
					1 : {
						title : "step 1: Starter",
						id : "setStageStarter",
						newStage : "starter"
						
					},
					2 : {
						title : "step 2: Main",
						id : "setStageMain",
						newStage : "main dish"
						
					}
				} 	
			};
		}
		
		//	Building DOM
		domP_first = $("<p>");
		domA_first = $("<a>");
		domA_first.attr("href", "#");
		domA_first.attr("id", obj["menuViewStepMenu"][1]["id"]);
		domA_first.html(obj["menuViewStepMenu"][1]["title"]);
		domA_first.click(function () {generalController.setStage(obj["menuViewStepMenu"][1]["newStage"])});
		
		domP_second = $("<p>");
		domA_second = $("<a>");
		domA_second.attr("href", "#");
		domA_second.attr("id", obj["menuViewStepMenu"][2]["id"]);
		domA_second.html(obj["menuViewStepMenu"][2]["title"]);
		domA_second.click(function () {generalController.setStage(obj["menuViewStepMenu"][2]["newStage"])});
		
		//	Populating menu view
		$("#menuViewTitle").html(obj["menuViewTitle"]);
		
		domP_first.append(domA_first);
		domP_second.append(domA_second);
		
		$("#menuViewStepMenu").html("");
		$("#menuViewStepMenu").append("<br/>");
		$("#menuViewStepMenu").append(domP_first);
		$("#menuViewStepMenu").append(domP_second);
		
	}

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
			domImg.click(function(){generalController.displayPopupView(true, obj[i]["id"])});

			domP = $("<p>");
			domP.addClass("id" + obj[i]['id']);
			domP.html(obj[i]["name"]);

			//	Appending
			domDivSpan.append(domImg);
			domDivSpan.append(domP);
			domDivR.append(domDivSpan);

		});
		menuViewDishList.html("");
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
	
		} else {
			$("#menuViewStarterDish").html("");
			
		}
		if (obj.main) {
			this.populateDish("main course", obj.main);

		} else {
			$("#menuViewMainDish").html("");
			
		}
		if (obj.dessert) {
			this.populateDish("dessert", obj.dessert);

		} else {
			$("#menuViewDessertDish").html("");
			
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
		domBtn.click(function(){generalController.menuViewController.removeDish(obj["id"])});
		
		domIcon = $("<i>");
		domIcon.addClass("icon-remove");
		
		//	Appending
		domDiv_img.append(domImg);
		domDiv_info.append(domP_name);
		domDiv_info.append(domP_cost);
		domBtn.append(domIcon);
		domDiv_remove.append(domBtn);
		
		domDiv_menu.html("");
		domDiv_menu.append("<br/>");
		domDiv_menu.append(domDiv_img);
		domDiv_menu.append(domDiv_info);
		domDiv_menu.append(domDiv_remove);
		
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
		this.getChangedStage();
		this.getDishList($("#menuViewDishList"));
		this.populateMenuList();

	}
}
 
