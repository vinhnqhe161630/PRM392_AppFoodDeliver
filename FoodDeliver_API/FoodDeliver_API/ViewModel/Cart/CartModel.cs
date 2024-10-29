using FoodDeliver_API.Models;

namespace FoodDeliver_API.ViewModel.Cart
{
    public class CartModel
    {
        public Guid Id { get; set; }
        public Account Shop { get; set; }
        public Account User { get; set; }
        public int Quantity { get; set; }

        public List<Food> foods { get; set; }
    }
}
