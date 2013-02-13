//GeneralModelController Object constructor
function GeneralController(dishModel,dinnerModel) {
	this.stage = "starter";

	//var exampleView = new ExampleView($("#exampleView"),dishModel,dinnerModel);
	//var exampleViewController = new ExampleViewController(exampleView,dishModel,dinnerModel);

	var menuView = new MenuView($("#menuView"),dishModel,dinnerModel,this);
	var menuViewController = new MenuViewController(menuView,dishModel,dinnerModel);

	menuView.getDishList($("#menuViewDishList"));
}

