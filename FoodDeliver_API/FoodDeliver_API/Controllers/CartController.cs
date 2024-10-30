
﻿using AutoMapper;
using FoodDeliver_API.Entities;
using FoodDeliver_API.Services;
using FoodDeliver_API.ViewModel.Cart;
using FoodDeliver_API.ViewModel.Cart;
using Microsoft.AspNetCore.Mvc;

namespace FoodDeliver_API.Controllers
{

	[Route("api/[controller]")]
	[ApiController]
	public class CartController : Controller
	{
		private readonly IMapper _mapper;
		private readonly CartService _cartService;
		public CartController(IMapper mapper, CartService cartService)
		{
			_mapper = mapper;
			_cartService = cartService;
		}
		[HttpPost]
		public async Task<IActionResult> createCart(AddCart addcart)
		{
			var cart = _mapper.Map<Cart>(addcart);
            await _cartService.createCart(cart);

            return Ok("Add ok");
		}
        [HttpGet("{userid}")]
        public async Task<IActionResult> getCartbyUserId(Guid userid)
        {
            try
            {
                var cart = await _cartService.getCartByUserId(userid);
                var cartVm = _mapper.Map<List<CartModel>>(cart);
                return Ok(cartVm);
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }

        }
        [HttpDelete]
		public async Task<IActionResult> deleteCart(AddCart addcart)
		{
			var cart = _mapper.Map<Cart>(addcart);


			return Ok("Add ok");
		}
	}

}
