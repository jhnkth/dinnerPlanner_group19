//PopupView Object constructor
function PopupView(parent,dishModel,dinnerModel,generalController,dishID) {
	// Get all the relevant elements of the view (ones that show data
	// and/or ones that responed to interaction)
	// -- Buttons :
	this.confirmButton 	= $("#popupViewConfirmButton");
	this.backButton 	= $("#popupViewBackButton");
	
	// -- Infos fields :
	this.dishName 			= $("#popupViewDishName");
	this.dishPrice 			= $("#popupViewDishPrice");
	this.numberOfGuests 	= $("#popupViewNumberOfGuests");
	this.ingredientsList	= $("#popupViewIngredientsList");
	this.dishDescription	= $("#popupViewDishDescription");
	this.dishImage			= $("#popupViewImage");
	
	// -- Dish object
	this.dish = dishModel.getDish(1);
	this.ingredients = this.dish["ingredients"];
	
	//Set the inital values of the components
	this.dishName.html(this.dish.name);	
	this.dishPrice.html(dishModel.getPrice(dishID) * dinnerModel.getNumberOfGuests());
	this.numberOfGuests.html(dinnerModel.getNumberOfGuests());
	this.dishDescription.html(this.dish.description);
	this.dishImage.attr("src","images/" + this.dish.image)
	
	for (var i in this.ingredients) {
		ing = this.ingredients[i];
		this.ingredientsList.append("<li>" + ing['name'] + " : " + ing['quantity'] + " " + ing['unit'] + "($" + ing['price'] + ")" + "</li>");
	}

	/*****************************************  
	      Observer implementation    
	*****************************************/
	
	//Register an observer to the model
	dinnerModel.addObserver(this);
	
	//This function gets called when there is a change at the model
	this.update = function(arg){
		//Only thing that needs to be updated : the total cost ?
		this.dishPrice.html(dishModel.getPrice(dishID) * dinnerModel.getNumberOfGuests());

	}
}
 
