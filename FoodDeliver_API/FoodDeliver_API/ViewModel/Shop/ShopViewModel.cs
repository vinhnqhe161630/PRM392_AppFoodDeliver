namespace FoodDeliver_API.ViewModel.Shop
{
    public class ShopViewModel
    {
        public Guid Id { get; set; }
        public string Name { get; set; }
        public string Email { get; set; }
        public string Pass { get; set; }
        public string? Img { get; set; }
        public string? Phone { get; set; }
        public string? Address { get; set; }
        public double Vote { get; set; }
        public string Role { get; set; }
        public bool Status { get; set; }

        public int TotalOrder { get; set; }


    }
}
