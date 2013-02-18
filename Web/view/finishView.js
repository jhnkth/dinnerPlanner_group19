//FinishView Object constructor
function FinishView(parent,dishModel,dinnerModel,generalController) {
	this.backButton 					= $("#finishViewBackButton");
	
	this.populateFinishView = function () {
		// Get all the relevant elements of the view (ones that show data
		// and/or ones that responed to interaction)
		
		// -- Back button and ingredient table :
		this.finishViewIngredients 			= $("#finishViewIngredients");
		
		
		// -- Recipes panel :
		this.finishViewStarterName 			= $("#finishViewStarterName");
		this.finishViewStarterDescription 	= $("#finishViewStarterDescription");
		this.finishViewMainName 			= $("#finishViewMainName");
		this.finishViewMainDescription 		= $("#finishViewMainDescription");
		this.finishViewDessertName 			= $("#finishViewDessertName");
		this.finishViewDessertDescription 	= $("#finishViewDessertDescription");
	
		// -- Dishes and ingredients objects, numberOfGuests
		this.starter = dishModel.getDish(dinnerModel.getSelectedDish('starter'));
		this.main = dishModel.getDish(dinnerModel.getSelectedDish('main course'));
		this.dessert = dishModel.getDish(dinnerModel.getSelectedDish('dessert'));
		this.ingredients = dinnerModel.getAllIngredients();
		this.numberOfGuests = dinnerModel.getNumberOfGuests();
		
		//Set the inital values of the components
		if (this.starter) {
			this.finishViewStarterName.html(this.starter.name);
			this.finishViewStarterDescription.html(this.starter.description);
			
		}
		if (this.main) {
			this.finishViewMainName.html(this.main.name);
			this.finishViewMainDescription.html(this.main.description);
						
		}
		if (this.dessert) {
			this.finishViewDessertName.html(this.dessert.name);
			this.finishViewDessertDescription.html(this.dessert.description);
					
		}
		
		for (var i in this.ingredients) {
			ing = this.ingredients[i];
			this.finishViewIngredients.append("<tr><td>" + ing['name'] + "</td><td>" + Math.round(ing['quantity']*this.numberOfGuests * 10)/10 + " " + ing['unit'] + "</td><td>" + ing['price']*this.numberOfGuests + "$" + "</td></tr>");
		}
	}

	/*****************************************  
	      Observer implementation    
	*****************************************/
	
	//Register an observer to the model
	dinnerModel.addObserver(this);
	
	//This function gets called when there is a change at the model
	this.update = function(arg){
		this.populateFinishView();
		
		/*this.finishViewIngredients.html("");
		
		this.finishViewIngredients.append("<tr><th>Ingredient</th><th>Quantity</th><th>Cost</th></tr>");
		
		
		this.numberOfGuests = dinnerModel.getNumberOfGuests();
		for (var i in this.ingredients) {
			ing = this.ingredients[i];
			this.finishViewIngredients.append("<tr><td>" + ing['name'] + "</td><td>" + Math.round(ing['quantity']*this.numberOfGuests * 10)/10+ " " + ing['unit'] + "</td><td>" + ing['price']*this.numberOfGuests + "$" + "</td></tr>");
		}*/
	}
}
 
