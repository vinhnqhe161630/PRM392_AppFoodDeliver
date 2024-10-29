namespace FoodDeliver_API.ViewModel.Cart
{
	public class AddCart
	{
		public Guid UserId { get; set; }
		public Guid ShopId { get; set; }
		public Guid FoodId { get; set; }
		public int Quantity { get; set; }
	}
}
