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

            CreateMap<Account, ShopViewModel>();

            //Order
            CreateMap<AddOrder, Order>();
            CreateMap<AddOrderDetails, OrderDetail>();
            CreateMap<Order, OrderViewModel>()
                  .ForMember(dest => dest.Username, opt => opt.MapFrom(src => src.Account.Name))
                    .ForMember(dest => dest.Shopname, opt => opt.MapFrom(src => src.Shop.Name));

            CreateMap<OrderDetail, OrderDetailsViewModel>()
                  .ForMember(dest => dest.FoodName, opt => opt.MapFrom(src => src.Food.Name))
                    .ForMember(dest => dest.FoodImg, opt => opt.MapFrom(src => src.Food.Img)); 
        }
    }
    
}
