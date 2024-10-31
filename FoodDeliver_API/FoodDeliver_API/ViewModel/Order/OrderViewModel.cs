namespace FoodDeliver_API.ViewModel.Order
{
    public class OrderViewModel
    {
        public Guid Id { get; set; }
        public Guid UserId { get; set; }
        public Guid ShopId { get; set; }
        public string CustomerName { get; set; }
        public string CustomerPhone { get; set; }
        public string CustomerAddress { get; set; }
        public string OrderDate { get; set; }
        public decimal TotalAmount { get; set; }
        public string Status { get; set; }
        public string? Username { get; set; }
        public string? Shopname { get; set; }
		public string? ShopImg { get; set; }

	}
}
