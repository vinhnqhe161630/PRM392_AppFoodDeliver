using AutoMapper;
using FoodDeliver_API.Entities;
using FoodDeliver_API.ViewModel.Auth;
using FoodDeliver_API.ViewModel.Order;
using FoodDeliver_API.ViewModel.Shop;

namespace FoodDeliver_API.Helpers
{
    public class MappingProfile : Profile
    {
        public MappingProfile()
        {
            //Auth
            CreateMap<LoginModel, Account>();
       
            CreateMap<SignUpModel, Account>();

            CreateMap<AddOrder, Order>();
            CreateMap<AddOrderDetails, OrderDetail>();

            CreateMap<Account, ShopViewModel>();
        }
    }
    
}
