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
        //public Guid UserId { get; set; }
        //public Guid ShopId { get; set; }
        //public string CustomerName { get; set; }
        //public string CustomerPhone { get; set; }
        //public string CustomerAddress { get; set; }
        //public DateTime OrderDate { get; set; }
        //public decimal TotalAmount { get; set; }
        //public string Status { get; set; }

        [HttpPost("checkOut/{userid}")]
        public async Task<IActionResult> checkOut(Guid userid)
        {
            AddOrder addOrder1=new AddOrder();
            try
            {
                Account a=await _orderService.getUser(userid);
                if (a != null)
                {
                    List<Account> list= await _orderService.GetShopsFromCarts(userid);
                    if(list==null)
                    {
						return BadRequest("cart null");
					}
					foreach (Account acc in list)
                    {
                        List<Cart> carts = await _orderService.getCartByUserId(userid,acc.Id);
                        int price = 0;
                        AddOrder addOrder = new AddOrder();
                        addOrder.OrderDate = DateTime.Now;
                        addOrder.Status = "in process";
                        addOrder.UserId = userid;
                        addOrder.ShopId=acc.Id;
                        addOrder.CustomerName = a.Name==null?"":a.Name;
                        addOrder.CustomerPhone = a.Phone == null ? "" : a.Phone;
                        addOrder.CustomerAddress = a.Address == null ? "" : a.Address;
                        addOrder.TotalAmount=carts.Sum(c=>c.Quantity*c.Food.Price);
                        //create order
                        var order = _mapper.Map<Order>(addOrder);
                        addOrder1 = addOrder;
                        await _orderService.createOrder(order);
                        Order o = await _orderService.getOrder();
                        Guid OrderId=o.Id;
                        //create order detail
                        foreach (Cart cart in carts)
                        {
                            OrderDetail detail = new OrderDetail();
                            detail.OrderID=OrderId;
                            detail.FoodID=cart.FoodId;
                            detail.Price=cart.Food.Price;
                            detail.Quantity = cart.Quantity;
                            detail.Total= cart.Food.Price*cart.Quantity;
                            await _orderService.createOrderDetail(detail);
                        }
                    }
                }
                else
                {
                    return NotFound();
                }
                
                return Ok(addOrder1);
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
