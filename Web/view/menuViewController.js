//MenuViewController Object constructor
function MenuViewController(view, dishModel, dinnerModel, generalController) {
	
	view.plusButton.click(function(){
		dinnerModel.setNumberOfGuests(dinnerModel.getNumberOfGuests() + 1);
	});
	
	view.minusButton.click(function(){
		dinnerModel.setNumberOfGuests(dinnerModel.getNumberOfGuests() - 1);
	});
	
	view.finishButton.click(function() {
		generalController.displayMenuView(false);
		generalController.displayFinishView(true);
		
	});
	
	view.searchBar.keypress(function() {
		view.update();
		
	});
	
	this.removeDish = function(id) {
		dinnerModel.removeDishFromMenu(id);
		view.update();
		
	};
	this.updateView = function () {
		view.update();
		
	};
}