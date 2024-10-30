namespace FoodDeliver_API.ViewModel.Order
{
    public class OrderDetailsViewModel
    {
        public Guid Id { get; set; }

        // Foreign Keys
        public Guid OrderID { get; set; }
        public Guid FoodID { get; set; }

        public int Quantity { get; set; }
        public decimal Price { get; set; }
        public decimal Total { get; set; }

        public string? FoodName { get; set; }   
        public string? FoodImg { get; set; }
    }
}
