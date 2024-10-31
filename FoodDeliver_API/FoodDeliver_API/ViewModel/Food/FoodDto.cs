namespace FoodDeliver_API.ViewModel.Food
{
    namespace FoodDeliver_API.Dtos
    {
        public class FoodDto
        {
            public Guid Id { get; set; }
            public string Name { get; set; }
            public decimal Price { get; set; }
            public string Description { get; set; }
            public string Img { get; set; }
            public bool Status { get; set; }
        }
    }

}
