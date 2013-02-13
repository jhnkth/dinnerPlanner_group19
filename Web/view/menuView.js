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

		/*for (var key in obj) {
			domDivSpan = $("<div>");
			domDivSpan.addClass("span2");
			domDivSpan.addClass("id" + obj[key]['id']);
			alert(key);
			
		}*/

		//var obj = dishModel.getDishes(generalController.stage);
		//alert(obj[0]["id"]);
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
 
