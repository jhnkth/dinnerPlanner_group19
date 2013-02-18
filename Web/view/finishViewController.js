//FinishViewController Object constructor
function FinishViewController(view, dishModel, dinnerModel, generalController) {
	view.backButton.click(function() {
		generalController.displayFinishView(false);
		generalController.displayMenuView(true);
		
	});

	this.updateView = function () {
		view.update();
		
	};
}
