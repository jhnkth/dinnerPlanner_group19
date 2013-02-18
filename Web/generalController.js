//GeneralModelController Object constructor
function GeneralController(dishModel,dinnerModel) {
	this.stage = "starter";	//	This variable represents which dish type ("starter", "main dish", "dessert") is selected.
	this.show = "menuView";	//	This variable represents which view ("menuView", "...", "...") is displayed.
	
	//	The Views
	var menuView = new MenuView($("#menuView"),dishModel,dinnerModel,this);
	var menuViewController = new MenuViewController(menuView,dishModel,dinnerModel);
	
	

	menuView.getDishList($("#menuViewDishList"));
	
	var finishView = new FinishView($("#finishView"),dishModel,dinnerModel,this);
	var finishViewController = new FinishViewController(menuView,dishModel,dinnerModel);
	
	var popupView = new PopupView($("#popupView"),dishModel,dinnerModel,this,1);
	var popupViewController = new PopupViewController(menuView,dishModel,dinnerModel);
	
	$( "#popupView" ).dialog();
}

