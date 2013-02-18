//DinnerModel Object constructor
function DinnerModel(dishModel) {
 
	this._menu = [];
	this._numberOfGuests = 3; //set default number of guests
	this._menu['starter'] = 1; //set a starter to the menu, to use for testing
	this._menu['main course'] = 100;
	this._menu['dessert'] = 201;


	this.setNumberOfGuests = function(num) {
		if(num>0) {
			this._numberOfGuests = num;
			this.notifyObservers();
		}
	}

	this.getNumberOfGuests = function() {
		return parseInt(this._numberOfGuests);
	}

	//Returns the dish that is on the menu for selected type 
	this.getSelectedDish = function(type) {
		return this._menu[type];
	}

	//Returns all the dishes on the menu.
	this.getFullMenu = function() {
		var dishes = [];
		for(key in this._menu) {
			dishes.push(dishModel.getDish(this._menu[key]));
		}
		return dishes;
	}

	//Returns all ingredients for all the dishes on the menu.
	this.getAllIngredients = function() {
		var ingredients = [];
		for(key in this._menu) {
			var dish = dishModel.getDish(this._menu[key]);
			ingredients = ingredients.concat(dish.ingredients);
		}
		return ingredients;
	}

	//Returns the total price of the menu (all the ingredients multiplied by number of guests).
	this.getTotalMenuPrice = function() {
		var ingredients = this.getAllIngredients();
		var sum = 0.;
		for(key in ingredients) {
			sum += parseFloat(ingredients[key].price) * this.getNumberOfGuests();
		}
		return sum;
	}

	//Adds the passed dish to the menu. If the dish of that type already exists on the menu
	//it is removed from the menu and the new one added.
	this.addDishToMenu = function(id, dishModel) {
		var type = dishModel.getDish(id).type;
		if (type == "main dish") {
			type = "main course";
			
		}
		if(this._menu[type]) {
			delete this._menu[type];
		}
		this._menu[type] = id;
		this.notifyObservers();
	}

	//Removes dish from menu
	this.removeDishFromMenu = function(id) {
		var type = dishModel.getDish(id).type;
		if (type == "main dish") {
			type = "main course";
			
		}
		if(this._menu[type] == id) {
			delete this._menu[type];
		}
	}

	/*****************************************  
	      Observable implementation    
	*****************************************/

	this._observers = [];

	this.addObserver = function(observer) 
	{
		this._observers.push(observer);
	}

	this.notifyObservers = function(arg) 
	{
		for(var i=0; i<this._observers.length; i++) 
		{
			this._observers[i].update(arg);
		}	
	}
}
