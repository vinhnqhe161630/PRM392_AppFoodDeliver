

namespace FoodDeliver_API.ViewModel.Cart
{
    public class CartModel
    {
        public Guid Id { get; set; }
        public Guid UserId { get; set; }
        public Guid ShopId { get; set; }
        public Guid FoodId { get; set; }
        public int Quantity { get; set; }
        public int Price { get; set; }

        public string FoodName { get; set; }
        public string ShopName { get; set; }
    }
}