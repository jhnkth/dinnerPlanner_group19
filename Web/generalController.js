//GeneralModelController Object constructor
function GeneralController(dishModel,dinnerModel) {
	this.stage = "starter";	//	This variable represents which dish type ("starter", "main dish", "dessert") is selected.
	this.show = "menuView";	//	This variable represents which view ("menuView", "...", "...") is displayed.
	this.popupId = 1;		//	...
	
	//	The Views
	var menuView = new MenuView($("#menuView"),dishModel,dinnerModel,this);
	this.menuViewController = new MenuViewController(menuView, dishModel, dinnerModel, this);
	
	var finishView = new FinishView($("#finishView"),dishModel,dinnerModel,this);
	this.finishViewController = new FinishViewController(finishView,dishModel,dinnerModel, this);
	
	var popupView = new PopupView($("#popupView"), dishModel, dinnerModel, this);
	this.popupViewController = new PopupViewController(popupView,dishModel,dinnerModel, this);
	
	//	Hide all views
	$("#menuView").hide();
	$("#finishView").hide();
	$("#popupViewContainer").hide();
	
	//	Init View
	this.displayMenuView(true);
	
};
GeneralController.prototype.setStage = function (stage) {
	this.stage = stage;
	this.menuViewController.updateView();
	
}
GeneralController.prototype.displayMenuView = function (show) {
	if (show == true) {
		this.menuViewController.updateView();
		$("#menuView").show();
		
	} else {
		$("#menuView").hide();
		
	}
}
GeneralController.prototype.displayFinishView = function (show) {
	if (show == true) {
		this.finishViewController.updateView();
		$("#finishView").show();
		
	} else {
		$("#finishView").hide();
		
	}
}
GeneralController.prototype.displayPopupView = function (show, id) {
	if (show == true) {
		this.popupId = id;
		this.popupViewController.updateView();
		$( "#popupViewContainer" ).dialog({
			width : 660,
			maxWidth : 660
			
		});	
	} else {
		$( "#popupViewContainer" ).dialog("close");
		
	}
}