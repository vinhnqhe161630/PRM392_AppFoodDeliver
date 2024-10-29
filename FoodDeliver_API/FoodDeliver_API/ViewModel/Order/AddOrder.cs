namespace FoodDeliver_API.ViewModel.Order
{
    public class AddOrder
    {
       
        public Guid UserId { get; set; }
        public Guid ShopId { get; set; }
        public string CustomerName { get; set; }
        public string CustomerPhone { get; set; }
        public string CustomerAddress { get; set; }
        public DateTime OrderDate { get; set; }
        public decimal TotalAmount { get; set; }
        public string Status { get; set; }
    }
}
