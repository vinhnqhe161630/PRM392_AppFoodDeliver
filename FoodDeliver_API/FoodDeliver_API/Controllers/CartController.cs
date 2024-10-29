
﻿using AutoMapper;
using FoodDeliver_API.Entities;
using FoodDeliver_API.Services;
using FoodDeliver_API.ViewModel.Cart;
using FoodDeliver_API.ViewModel.Order;

using Microsoft.AspNetCore.Mvc;

namespace FoodDeliver_API.Controllers
{

	[Route("api/[controller]")]
	[ApiController]
	public class CartController : Controller
	{
		private readonly IMapper _mapper;
		private readonly OrderService _orderService;
		public CartController(IMapper mapper, OrderService orderService)
		{
			_mapper = mapper;
			_orderService = orderService;
		}
		[HttpPost]
		public async Task<IActionResult> createCart(AddCart addcart)
		{
			var order = _mapper.Map<Order>(addcart);


			return Ok("Add ok");
		}
		[HttpDelete]
		public async Task<IActionResult> deleteCart(AddOrder addorder)
		{
			var order = _mapper.Map<Order>(addorder);


			return Ok("Add ok");
		}
	}

}
