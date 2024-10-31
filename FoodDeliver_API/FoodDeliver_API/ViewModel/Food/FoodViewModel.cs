namespace FoodDeliver_API.ViewModel.Food
{
    public class FoodViewModel
    {
        public Guid Id { get; set; }
        public string Name { get; set; }
        public decimal Price { get; set; }
        public string Description { get; set; }
        public string Img { get; set; }
        public bool Status { get; set; }

        // Foreign Key
        public Guid AccountID { get; set; }

        public string? ShopName { get; set; }

    }
}
