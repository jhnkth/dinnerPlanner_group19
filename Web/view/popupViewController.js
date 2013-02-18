//PopupViewController Object constructor
function PopupViewController(view, dishModel, dinnerModel, generalController ) {
	this.updateView = function () {
		view.update();
		
	};
	
	view.backButton.click(function() {
		generalController.displayPopupView(false, null);
		
	});
}
