using FoodDeliver_API.Entities;
using FoodDeliver_API.ViewModel.Food.FoodDeliver_API.Dtos;
namespace FoodDeliver_API.ViewModel.Shop
{
    public class ShopDTO
    {
        public Guid Id { get; set; }
        public string Name { get; set; }
        public string Img { get; set; }
        public string Email { get; set; }
        public string Phone { get; set; }

        public string Address { get; set; }
        public List<FoodDto> Foods { get; set; }
     
    }
}
