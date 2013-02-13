//ExampleViewController Object constructor
function ExampleViewController(view, dishModel,dinnerModel ) {
	
	view.plusButton.click(function(){
		dinnerModel.setNumberOfGuests(dinnerModel.getNumberOfGuests() + 1);
	});
	
	view.minusButton.click(function(){
		dinnerModel.setNumberOfGuests(dinnerModel.getNumberOfGuests() - 1);
	});
}
