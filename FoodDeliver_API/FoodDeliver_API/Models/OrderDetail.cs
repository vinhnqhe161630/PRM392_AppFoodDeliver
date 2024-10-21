namespace FoodDeliver_API.Models
{
    public class OrderDetail
    {
        public Guid Id { get; set; }

        // Foreign Keys
        public Guid OrderID { get; set; }
        public Guid FoodID { get; set; }

        public int Quantity { get; set; }
        public decimal Price { get; set; }
        public decimal Total { get; set; }

        // Navigation properties
        public Order Order { get; set; }
        public Food Food { get; set; }
    }

}
