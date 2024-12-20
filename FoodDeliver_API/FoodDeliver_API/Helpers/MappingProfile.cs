﻿using AutoMapper;
using FoodDeliver_API.Entities;
using FoodDeliver_API.ViewModel.Auth;

using FoodDeliver_API.ViewModel.Food;

using FoodDeliver_API.ViewModel.Cart;

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
            //Cart
            CreateMap<AddCart, Cart>();
            CreateMap<Cart, CartModel>()
            .ForMember(dest => dest.FoodName, opt => opt.MapFrom(src => src.Food.Name))
            .ForMember(dest => dest.Price, opt => opt.MapFrom(src => src.Food.Price))
			.ForMember(dest => dest.FoodImage, opt => opt.MapFrom(src => src.Food.Img))
            .ForMember(dest => dest.ShopName, opt => opt.MapFrom(src => src.Shop.Name));
            //Order
            CreateMap<AddOrder, Order>();
            CreateMap<AddOrderDetails, OrderDetail>();
            CreateMap<Order, OrderViewModel>()
				.ForMember(dest => dest.OrderDate, opt => opt.MapFrom(src => src.OrderDate.ToString()))
				  .ForMember(dest => dest.Username, opt => opt.MapFrom(src => src.Account.Name))
                    .ForMember(dest => dest.Shopname, opt => opt.MapFrom(src => src.Shop.Name))
						.ForMember(dest => dest.ShopImg, opt => opt.MapFrom(src => src.Shop.Img))

					;

			CreateMap<OrderDetail, OrderDetailsViewModel>()
                  .ForMember(dest => dest.FoodName, opt => opt.MapFrom(src => src.Food.Name))
                    .ForMember(dest => dest.FoodImg, opt => opt.MapFrom(src => src.Food.Img));

            CreateMap<Food, FoodViewModel>()
                  .ForMember(dest => dest.ShopName, opt => opt.MapFrom(src => src.Account.Name));
            
        }
    }
    
}
