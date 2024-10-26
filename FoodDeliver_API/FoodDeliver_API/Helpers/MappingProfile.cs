using AutoMapper;
using FoodDeliver_API.Models;
using FoodDeliver_API.ViewModel.Auth;

namespace FoodDeliver_API.Helpers
{
    public class MappingProfile : Profile
    {
        public MappingProfile()
        {
            //Auth
            CreateMap<LoginModel, Account>();
       
            CreateMap<SignUpModel, Account>();
        }
    }
    
}
