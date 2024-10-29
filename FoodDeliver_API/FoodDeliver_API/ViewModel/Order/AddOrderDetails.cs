namespace FoodDeliver_API.ViewModel.Order
{
    public class AddOrderDetails
    {
   
        // Foreign Keys
        public Guid OrderID { get; set; }
        public Guid FoodID { get; set; }

        public int Quantity { get; set; }
        public decimal Price { get; set; }
        public decimal Total { get; set; }
    }
}
