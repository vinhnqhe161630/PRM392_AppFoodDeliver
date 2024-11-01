
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
			cart.ShopId=await _cartService.getShopByFoodId(cart.FoodId);
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
        
        [HttpDelete("{cartId}")]
		public async Task<IActionResult> deleteCart(Guid cartId)
		{

            try
            {
                var cart = await _cartService.getCartByCartId(cartId);
                if(cart != null)
                {
                    _cartService.deleteCart(cart);
                    return Ok(cart);
                }
                else
                {
                    return BadRequest("delete null");
                }
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
		}
        [HttpPost("increase/{cartId}")]
        public async Task<IActionResult> increaseCart(Guid cartId)
        {
            try
            {
                var cart = await _cartService.getCartByCartId(cartId);
                if (cart != null)
                {
                    _cartService.increaseCart(cart);
                    return Ok(cart);
                }
                else
                {
                    return BadRequest("increase null");
                }
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }
        [HttpPost("decrease/{cartId}")]
        public async Task<IActionResult> decreaseCart(Guid cartId)
        {
            try
            {
                var cart = await _cartService.getCartByCartId(cartId);
                if (cart != null)
                {
                    if (cart.Quantity > 0)
                    {
                        _cartService.decreaseCart(cart);
                        return Ok(cart);
                    }
                    else
                    {
                        return BadRequest("decrease null");
                    }
                }
                else
                {
                    return BadRequest("decrease null");
                }
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }
    }
}
