using AutoMapper;
using FoodDeliver_API.Models;
using FoodDeliver_API.ViewModel.Auth;
using FoodDeliver_API.ViewModel.Order;

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
        }
    }
    
}
