using AutoMapper;
using FoodDeliver_API.Services;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace FoodDeliver_API.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class OrderController : ControllerBase
    {
        private readonly IMapper _mapper;
        private readonly OrderService _orderService;
        public OrderController(IMapper mapper, OrderService orderService)
        {
            _mapper = mapper;
            _orderService = orderService;
        }

        [HttpGet("{userid}")]
        public async Task<IActionResult> getOrderbyUserId(Guid userid)
        {
            var order = await _orderService.getOrderByUserId(userid);
            return Ok(order);
        }

       

    }
}
