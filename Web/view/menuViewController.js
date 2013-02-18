//MenuViewController Object constructor
function MenuViewController(view, dishModel, dinnerModel, generalController) {
	
	view.plusButton.click(function(){
		dinnerModel.setNumberOfGuests(dinnerModel.getNumberOfGuests() + 1);
	});
	
	view.minusButton.click(function(){
		dinnerModel.setNumberOfGuests(dinnerModel.getNumberOfGuests() - 1);
	});
	
	this.updateView = function () {
		view.update();
		
	};
}