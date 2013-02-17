//FinishView Object constructor
function FinishView(parent,dishModel,dinnerModel,generalController) {
	// Get all the relevant elements of the view (ones that show data
	// and/or ones that responed to interaction)
	
	// -- Back button and ingredient table :
	this.backButton 					= $("#finishViewBackButton");
	this.finishViewIngredients 			= $("#finishViewIngredients");
	
	
	// -- Recipes panel :
	this.finishViewStarterName 			= $("#finishViewStarterName");
	this.finishViewStarterDescription 	= $("#finishViewStarterDescription");
	this.finishViewMainName 			= $("#finishViewMainName");
	this.finishViewMainDescription 		= $("#finishViewMainDescription");
	this.finishViewDessertName 			= $("#finishViewDessertName");
	this.finishViewDessertDescription 	= $("#finishViewDessertDescription");

	// -- Dishes and ingredients objects
	this.starter = dishModel.getDish(dinnerModel.getSelectedDish('starter'));
	this.main = dishModel.getDish(dinnerModel.getSelectedDish('main course'));
	this.dessert = dishModel.getDish(dinnerModel.getSelectedDish('dessert'));
	this.ingredients = dinnerModel.getAllIngredients();
	
	//Set the inital values of the components
	this.finishViewStarterName.html(this.starter.name);
	this.finishViewStarterDescription.html(this.starter.description);
	this.finishViewMainName.html(this.main.name);
	this.finishViewMainDescription.html(this.main.description);
	this.finishViewDessertName.html(this.dessert.name);
	this.finishViewDessertDescription.html(this.dessert.description);
	
	for (var i in this.ingredients) {
		ing = this.ingredients[i];
		this.finishViewIngredients.append("<tr><td>" + ing['name'] + "</td><td>" + ing['quantity'] + " " + ing['unit'] + "</td><td>" + ing['price'] + "$" + "</td></tr>");
	}

	/*****************************************  
	      Observer implementation    
	*****************************************/
	
	//Register an observer to the model
	dinnerModel.addObserver(this);
	
	//This function gets called when there is a change at the model
	this.update = function(arg){
		//Nothing gets updated here...

	}
}
 
