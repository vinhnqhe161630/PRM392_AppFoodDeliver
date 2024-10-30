using AutoMapper;
using FoodDeliver_API.Entities;
using FoodDeliver_API.Services;
using FoodDeliver_API.ViewModel.Order;
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
            try
            {
                var order = await _orderService.getOrderByUserId(userid);

                var orderVm = _mapper.Map<List<OrderViewModel>>(order);

                return Ok(orderVm);
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }

        }

        [HttpPost("addOrder")]
        public async Task<IActionResult> createOrder(AddOrder addorder)
        {
            try
            {
                var order = _mapper.Map<Order>(addorder);

                await _orderService.createOrder(order);

                return Ok("Add ok");
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }
        [HttpPost("addOrderDetail")]
        public async Task<IActionResult> createOrderDetail(OrderDetail orderDetail)
        {
            try
            {
                await _orderService.createOrderDetail(orderDetail);

                return Ok("Add ok");
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }

        [HttpGet("getOrderDetail/{orderid}")]
        public async Task<IActionResult> getOrderDetail(Guid orderid)
        {
            try
            {
                var orderDetail = await _orderService.getOrderDetail(orderid);
                var orderDetailVm = _mapper.Map<List<OrderDetailsViewModel>>(orderDetail);
                return Ok(orderDetailVm);
            }

            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }


    }
}
