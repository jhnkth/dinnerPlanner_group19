//GeneralModelController Object constructor
function GeneralController(dishModel,dinnerModel) {
	this.stage = "starter";	//	This variable represents which dish type ("starter", "main dish", "dessert") is selected.
	this.show = "menuView";	//	This variable represents which view ("menuView", "...", "...") is displayed.
	
	//	The Views
	var menuView = new MenuView($("#menuView"),dishModel,dinnerModel,this);
	this.menuViewController = new MenuViewController(menuView,dishModel,dinnerModel, this);
	
	menuView.getChangedStage();
	menuView.getDishList($("#menuViewDishList"));
	menuView.populateMenuList();
	
	var finishView = new FinishView($("#finishView"),dishModel,dinnerModel,this);
	var finishViewController = new FinishViewController(menuView,dishModel,dinnerModel);
	
	var popupView = new PopupView($("#popupView"),dishModel,dinnerModel,this,1);
	var popupViewController = new PopupViewController(menuView,dishModel,dinnerModel);
	
	//	Methods
	/*this.setStage = function (stage) {
		this.stage = stage;
		menuView.update(true);
		
	}*/
};
GeneralController.prototype.setStage = function (stage) {
	this.stage = stage;
	this.menuViewController.updateView();
	
}
