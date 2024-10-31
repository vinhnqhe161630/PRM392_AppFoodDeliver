using FoodDeliver_API.ViewModel.Comments.FoodDeliver_API.Models;
using FoodDeliver_API.ViewModel.Shop;

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

            // In FoodDto class, change this line
            public ShopFoodDto Account { get; set; } // Change from List<ShopFoodDto> to ShopFoodDto

            public List<CommentDto> Comments { get; set; }
        }
    }

}
